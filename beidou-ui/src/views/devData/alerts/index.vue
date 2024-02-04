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
      <el-form-item label="设备编码" prop="devCode">
        <el-input
          v-model="queryParams.devCode"
          placeholder="请输入设备编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="devName">
        <el-input
          v-model="queryParams.devName"
          placeholder="请输入设备名称"
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
      <el-form-item label="公司名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="温度状况" prop="temAlert">
        <el-input
          v-model="queryParams.temAlert"
          placeholder="请选择温度状况"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="湿度状况" prop="humAlert">
        <el-input
          v-model="queryParams.humAlert"
          placeholder="请选择湿度状况"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交时间" prop="curtime">
        <el-date-picker
          clearable
          v-model="queryParams.curtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择提交时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker
          clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="刷新间隔" prop="interval">
        <el-input-number
          v-model="interval"
          @change="handleChange"
          :min="1"
          controls-position="right"
          placeholder="请输入刷新间隔（秒）"
        />
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
          v-hasPermi="['devData:alert:add']"
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
          v-hasPermi="['devData:alert:edit']"
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
          v-hasPermi="['devData:alert:remove']"
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
          v-hasPermi="['devData:alert:export']"
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
      :data="alertList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备编码" align="center" prop="devCode" />
      <el-table-column label="设备名称" align="center" prop="devName" />
      <el-table-column label="仓库编码" align="center" prop="houseCode" />
      <el-table-column label="仓库名称" align="center" prop="houseName" />
      <el-table-column label="公司Id" align="center" prop="companyId" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="温度" align="center" prop="tem" />
      <el-table-column label="湿度" align="center" prop="hum" />
      <el-table-column label="温度状况" align="center" prop="temAlert" />
      <el-table-column label="湿度状况" align="center" prop="humAlert" />
      <el-table-column
        label="提交时间"
        align="center"
        prop="curtime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.curtime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d}") }}</span>
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
            v-hasPermi="['devData:alert:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['devData:alert:remove']"
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

    <!-- 添加或修改告警信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备编码" prop="devCode">
          <el-input v-model="form.devCode" placeholder="请输入设备编码" />
        </el-form-item>
        <el-form-item label="设备名称" prop="devName">
          <el-input v-model="form.devName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="仓库编码" prop="houseCode">
          <el-input v-model="form.houseCode" placeholder="请输入仓库编码" />
        </el-form-item>
        <el-form-item label="仓库名称" prop="houseName">
          <el-input v-model="form.houseName" placeholder="请输入仓库名称" />
        </el-form-item>
        <el-form-item label="公司Id" prop="companyId">
          <el-input v-model="form.companyId" placeholder="请输入公司Id" />
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="温度" prop="tem">
          <el-input v-model="form.tem" placeholder="请输入温度" />
        </el-form-item>
        <el-form-item label="湿度" prop="hum">
          <el-input v-model="form.hum" placeholder="请输入湿度" />
        </el-form-item>
        <el-form-item label="温度状况： 1：高温， 0：" prop="temAlert">
          <el-input
            v-model="form.temAlert"
            placeholder="请输入温度状况： 1：高温， 0："
          />
        </el-form-item>
        <el-form-item label="湿度状况： 1：高湿， 0：" prop="humAlert">
          <el-input
            v-model="form.humAlert"
            placeholder="请输入湿度状况： 1：高湿， 0："
          />
        </el-form-item>
        <el-form-item label="提交时间" prop="curtime">
          <el-date-picker
            clearable
            v-model="form.curtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择提交时间"
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
  listAlert,
  getAlert,
  delAlert,
  addAlert,
  updateAlert,
} from "@/api/devData/alert";

export default {
  name: "Alert",
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
      // 告警信息表格数据
      alertList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        devCode: null,
        devName: null,
        houseCode: null,
        houseName: null,
        companyName: null,
        tem: null,
        hum: null,
        temAlert: null,
        humAlert: null,
        curtime: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      timer: null,
      interval: 5,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询告警信息列表 */
    getList() {
      this.loading = true;
      listAlert(this.queryParams).then((response) => {
        this.alertList = response.rows;
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
        devCode: null,
        devName: null,
        houseCode: null,
        houseName: null,
        companyId: null,
        companyName: null,
        tem: null,
        hum: null,
        temAlert: null,
        humAlert: null,
        curtime: null,
        createTime: null,
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
      this.title = "添加告警信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getAlert(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改告警信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateAlert(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAlert(this.form).then((response) => {
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
        .confirm('是否确认删除告警信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return delAlert(ids);
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
        "devData/alert/export",
        {
          ...this.queryParams,
        },
        `alert_${new Date().getTime()}.xlsx`
      );
    },
    /**修改刷新时间触发 */
    handleChange(value) {
      this.interval = value;
      this.startTimer();
    },
    /**开启定时器 */
    startTimer() {
      // 清除已存在的定时器
      if (this.timer) {
        clearInterval(this.timer);
        this.timer = null;
      }
      // 创建新的定时器
      this.timer = setInterval(() => {
        console.log("time");
        this.getList();
      }, this.interval * 1000); // 把间隔转换为毫秒
    },
  },
  mounted() {
    this.startTimer(); // 在组件挂载后开始定时器
  },
  beforeDestroy() {
    // 在组件销毁前清除定时器
    if (this.timer) {
      clearInterval(this.timer);
      this.timer = null;
    }
  },
};
</script>
