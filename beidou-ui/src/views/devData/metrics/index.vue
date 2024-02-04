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
      <el-form-item label="指标编码" prop="mCode">
        <el-input
          v-model="queryParams.mCode"
          placeholder="请输入指标编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="指标名称" prop="mName">
        <el-input
          v-model="queryParams.mName"
          placeholder="请输入指标名称"
          clearable
          @keyup.enter.native="handleQuery"
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
          v-hasPermi="['devData:metrics:add']"
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
          v-hasPermi="['devData:metrics:edit']"
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
          v-hasPermi="['devData:metrics:remove']"
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
          v-hasPermi="['devData:metrics:export']"
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
      :data="metricsList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="指标编码" align="center" prop="mCode" />
      <el-table-column label="指标名称" align="center" prop="mName" />
      <el-table-column label="温度上限℃" align="center" prop="maxTem" />
      <el-table-column label="温度下限℃" align="center" prop="minTem" />
      <el-table-column label="湿度上限%" align="center" prop="maxHum" />
      <el-table-column label="湿度下限%" align="center" prop="minHum" />
      <el-table-column label="通风次数" align="center" prop="ventTimes" />
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
            v-hasPermi="['devData:metrics:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['devData:metrics:remove']"
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

    <!-- 添加或修改指标管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="指标编码" prop="mCode">
          <el-input v-model="form.mCode" placeholder="请输入指标编码" />
        </el-form-item>
        <el-form-item label="指标名称" prop="mName">
          <el-input v-model="form.mName" placeholder="请输入指标名称" />
        </el-form-item>
        <el-form-item label="温度上限" prop="maxTem">
          <el-input v-model="form.maxTem" placeholder="请输入温度上限" />
        </el-form-item>
        <el-form-item label="温度下限" prop="minTem">
          <el-input v-model="form.minTem" placeholder="请输入温度下限" />
        </el-form-item>
        <el-form-item label="湿度上线" prop="maxHum">
          <el-input v-model="form.maxHum" placeholder="请输入湿度上线" />
        </el-form-item>
        <el-form-item label="湿度下线" prop="minHum">
          <el-input v-model="form.minHum" placeholder="请输入湿度下线" />
        </el-form-item>
        <el-form-item label="通风次数" prop="ventTimes">
          <el-input v-model="form.ventTimes" placeholder="请输入通风次数" />
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
  listMetrics,
  getMetrics,
  delMetrics,
  addMetrics,
  updateMetrics,
} from "@/api/devData/metrics";

export default {
  name: "Metrics",
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
      // 指标管理表格数据
      metricsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mCode: [
          { required: true, message: "指标编码不能为空", trigger: "blur" },
        ],
        mName: [
          { required: true, message: "指标名称不能为空", trigger: "blur" },
        ],
        maxTem: [
          { required: true, message: "温度上限不能为空", trigger: "blur" },
        ],
        minTem: [
          { required: true, message: "温度下限不能为空", trigger: "blur" },
        ],
        maxHum: [
          { required: true, message: "湿度上线不能为空", trigger: "blur" },
        ],
        minHum: [
          { required: true, message: "湿度下线不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询指标管理列表 */
    getList() {
      this.loading = true;
      listMetrics(this.queryParams).then((response) => {
        this.metricsList = response.rows;
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
        mCode: null,
        mName: null,
        maxTem: null,
        minTem: null,
        maxHum: null,
        minHum: null,
        ventTimes: null,
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
      this.title = "添加指标管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getMetrics(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改指标管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateMetrics(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMetrics(this.form).then((response) => {
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
        .confirm('是否确认删除指标管理编号为"' + ids + '"的数据项？')
        .then(function () {
          return delMetrics(ids);
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
        "devData/metrics/export",
        {
          ...this.queryParams,
        },
        `metrics_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
