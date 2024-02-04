<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="区域编码" prop="areaCode">
        <el-input
          v-model="queryParams.areaCode"
          placeholder="请输入区域编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库编码" prop="houseCode">
        <el-input
          v-model="queryParams.houseCode"
          placeholder="请输入仓库编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库名称" prop="houseName">
        <el-input
          v-model="queryParams.houseName"
          placeholder="请输入仓库名称"
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
          v-hasPermi="['devData:wareHouseArea:add']"
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
          v-hasPermi="['devData:wareHouseArea:edit']"
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
          v-hasPermi="['devData:wareHouseArea:remove']"
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
          v-hasPermi="['devData:wareHouseArea:export']"
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
      :data="wareHouseAreaList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="区域编码" align="center" prop="areaCode" />
      <el-table-column label="仓库编码" align="center" prop="houseCode" />
      <el-table-column label="仓库名称" align="center" prop="houseName" />
      <el-table-column
        label="区域类型：1-货架，0-设备区"
        align="center"
        prop="areaType"
      />
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
            v-hasPermi="['devData:wareHouseArea:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['devData:wareHouseArea:remove']"
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

    <!-- 添加或修改仓库区域对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="区域编码" prop="areaCode">
          <el-input v-model="form.areaCode" placeholder="请输入区域编码" />
        </el-form-item>
        <el-form-item label="仓库编码" prop="houseCode">
          <el-input v-model="form.houseCode" placeholder="请输入仓库编码" />
        </el-form-item>
        <el-form-item label="仓库名称" prop="houseName">
          <el-input v-model="form.houseName" placeholder="请输入仓库名称" />
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
  listWareHouseArea,
  getWareHouseArea,
  delWareHouseArea,
  addWareHouseArea,
  updateWareHouseArea,
} from "@/api/devData/wareHouseArea";

export default {
  name: "WareHouseArea",
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
      // 仓库区域表格数据
      wareHouseAreaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        areaCode: null,
        houseCode: null,
        houseName: null,
        areaType: null,
        createdTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        areaCode: [
          { required: true, message: "区域编码不能为空", trigger: "blur" },
        ],
        houseCode: [
          { required: true, message: "仓库编码不能为空", trigger: "blur" },
        ],
        houseName: [
          { required: true, message: "仓库名称不能为空", trigger: "blur" },
        ],
        areaType: [
          {
            required: true,
            message: "区域类型：1-货架，0-设备区不能为空",
            trigger: "change",
          },
        ],
        createdTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询仓库区域列表 */
    getList() {
      this.loading = true;
      listWareHouseArea(this.queryParams).then((response) => {
        this.wareHouseAreaList = response.rows;
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
        areaCode: null,
        houseCode: null,
        houseName: null,
        areaType: null,
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
      this.title = "添加仓库区域";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getWareHouseArea(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改仓库区域";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateWareHouseArea(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWareHouseArea(this.form).then((response) => {
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
        .confirm('是否确认删除仓库区域编号为"' + ids + '"的数据项？')
        .then(function () {
          return delWareHouseArea(ids);
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
        "devData/wareHouseArea/export",
        {
          ...this.queryParams,
        },
        `wareHouseArea_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
