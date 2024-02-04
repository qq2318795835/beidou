<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="auto"
    >
      <el-form-item label="仓库名称" prop="houseName">
        <el-input
          v-model="queryParams.houseName"
          placeholder="请输入仓库名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="principalName">
        <el-input
          v-model="queryParams.principalName"
          placeholder="请输入负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人电话" prop="principalTel">
        <el-input
          v-model="queryParams.principalTel"
          placeholder="请输入负责人电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker
          clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['devData:warehouse:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['devData:warehouse:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['devData:warehouse:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['devData:warehouse:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="warehouseList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="仓库编码" align="center" prop="houseCode" />
      <el-table-column label="仓库名称" align="center" prop="houseName" />
      <el-table-column label="仓库地址" align="center" prop="houseAddress" />
      <el-table-column label="库房类型" align="center" prop="houseType" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="负责人" align="center" prop="principalName" />
      <el-table-column label="负责人电话" align="center" prop="principalTel" />
      <el-table-column label="经度" align="center" prop="longitude" />
      <el-table-column label="维度" align="center" prop="latitude" />
      <el-table-column label="库房面积" align="center" prop="areaSize" />
      <el-table-column label="状态" align="center" prop="houseStatus">
        <template slot-scope="scope">
          {{ houseStatusText[scope.row.houseStatus] }}
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createdTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['devData:warehouse:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['devData:warehouse:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改仓库对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1200px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="仓库编码" prop="houseCode">
          <el-input v-model="form.houseCode" placeholder="请输入仓库编码" />
        </el-form-item>
        <el-form-item label="仓库名称" prop="houseName">
          <el-input v-model="form.houseName" placeholder="请输入仓库名称" />
        </el-form-item>
        <el-form-item label="仓库地址" prop="houseAddress">
          <el-input v-model="form.houseAddress" placeholder="请输入仓库地址" />
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="负责人" prop="principalName">
          <el-input v-model="form.principalName" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="负责人电话" prop="principalTel" class="item">
          <el-input
            v-model="form.principalTel"
            placeholder="请输入负责人电话"
          />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="维度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入维度" />
        </el-form-item>
        <el-form-item label="库房面积" prop="areaSize">
          <el-input v-model="form.areaSize" placeholder="请输入库房面积" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker
            clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listWarehouse,
  getWarehouse,
  delWarehouse,
  addWarehouse,
  updateWarehouse,
} from "@/api/devData/warehouse";

export default {
  name: "Warehouse",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 仓库表格数据
      warehouseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        houseName: null,
        houseType: null,
        companyName: null,
        principalName: null,
        principalTel: null,
        houseStatus: null,
        createdTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        houseCode: [
          { required: true, message: "仓库编码不能为空", trigger: "blur" },
        ],
        houseName: [
          { required: true, message: "仓库名称不能为空", trigger: "blur" },
        ],
        houseAddress: [
          { required: true, message: "仓库地址不能为空", trigger: "blur" },
        ],
        houseType: [
          {
            required: true,
            message:
              "库房类型：1-高温库， 2-中温库，3-低温库，4-超低温库，5-速冻库，6-气调库温度标准不能为空",
            trigger: "change",
          },
        ],
        principalName: [
          { required: true, message: "负责人不能为空", trigger: "blur" },
        ],
        principalTel: [
          { required: true, message: "负责人电话不能为空", trigger: "blur" },
        ],
        longitude: [
          { required: true, message: "经度不能为空", trigger: "blur" },
        ],
        latitude: [
          { required: true, message: "维度不能为空", trigger: "blur" },
        ],
        areaSize: [
          { required: true, message: "库房面积不能为空", trigger: "blur" },
        ],
        houseStatus: [
          {
            required: true,
            message: "状态不能为空",
            trigger: "change",
          },
        ],
        createdTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
      },
      houseStatusText: {
        0: "空库",
        1: "使用中",
        // 可以添加更多的状态对应的文本信息
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询仓库列表 */
    getList() {
      this.loading = true;
      listWarehouse(this.queryParams).then((response) => {
        this.warehouseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        houseCode: null,
        houseName: null,
        houseAddress: null,
        houseType: null,
        companyId: null,
        companyName: null,
        principalName: null,
        principalTel: null,
        longitude: null,
        latitude: null,
        areaSize: null,
        houseStatus: null,
        createdTime: null,
        updateTime: null,
        isDelete: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加仓库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getWarehouse(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改仓库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateWarehouse(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWarehouse(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除仓库编号为"' + ids + '"的数据项？')
        .then(function () {
          return delWarehouse(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "devData/warehouse/export",
        {
          ...this.queryParams,
        },
        `warehouse_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
