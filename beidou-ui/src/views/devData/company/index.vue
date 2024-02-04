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
      <el-form-item label="公司名称" prop="company">
        <el-input
          v-model="queryParams.company"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业编号" prop="companyNumber">
        <el-input
          v-model="queryParams.companyNumber"
          placeholder="请输入企业编号"
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
          v-hasPermi="['devDate:company:add']"
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
          v-hasPermi="['devDate:company:edit']"
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
          v-hasPermi="['devDate:company:remove']"
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
          v-hasPermi="['devDate:company:export']"
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
      :data="companyList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公司名称" align="center" prop="company" />
      <el-table-column label="公司简称" align="center" prop="abbreviation" />
      <el-table-column label="企业编号" align="center" prop="companyNumber" />
      <el-table-column label="企业地址" align="center" prop="companyAddress" />
      <el-table-column label="公司电话" align="center" prop="companyPhone" />
      <el-table-column
        label="第二负责人"
        align="center"
        prop="managementName"
      />
      <el-table-column
        label="第二负责人联系电话"
        align="center"
        prop="managementPhone"
      />
      <el-table-column label="第一负责人姓名" align="center" prop="leader" />
      <el-table-column
        label="第一负责人电话"
        align="center"
        prop="leaderPhone"
      />
      <el-table-column label="备注" align="center" prop="message" />
      <el-table-column label="网站" align="center" prop="webAddress" />
      <el-table-column label="企业类型" align="center" prop="companyType" />
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
            v-hasPermi="['devDate:company:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['devDate:company:remove']"
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

    <!-- 添加或修改企业信息对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1200px"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="company">
          <el-input v-model="form.company" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="公司简称" prop="abbreviation">
          <el-input v-model="form.abbreviation" placeholder="请输入公司简称" />
        </el-form-item>
        <el-form-item label="企业编号" prop="companyNumber">
          <el-input v-model="form.companyNumber" placeholder="请输入企业编号" />
        </el-form-item>
        <el-form-item label="企业地址" prop="companyAddress">
          <el-input
            v-model="form.companyAddress"
            placeholder="请输入企业地址"
          />
        </el-form-item>
        <el-form-item label="公司电话" prop="companyPhone">
          <el-input v-model="form.companyPhone" placeholder="请输入公司电话" />
        </el-form-item>
        <el-form-item label="第一负责人姓名" prop="leader">
          <el-input v-model="form.leader" placeholder="请输入第一负责人姓名" />
        </el-form-item>
        <el-form-item label="第一负责人电话" prop="leaderPhone">
          <el-input
            v-model="form.leaderPhone"
            placeholder="请输入第一负责人电话"
          />
        </el-form-item>
        <el-form-item label="第二负责人" prop="managementName">
          <el-input
            v-model="form.managementName"
            placeholder="请输入第二负责人"
          />
        </el-form-item>
        <el-form-item label="第二负责人联系电话" prop="managementPhone">
          <el-input
            v-model="form.managementPhone"
            placeholder="请输入第二负责人联系电话"
          />
        </el-form-item>

        <el-form-item label="备注" prop="message">
          <el-input v-model="form.message" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="网站" prop="webAddress">
          <el-input v-model="form.webAddress" placeholder="请输入网站" />
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
  listCompany,
  getCompany,
  delCompany,
  addCompany,
  updateCompany,
} from "@/api/devData/company";

export default {
  name: "Company",
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
      // 企业信息表格数据
      companyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        company: null,
        companyNumber: null,
        companyType: null,
        createdTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        company: [
          { required: true, message: "公司名称不能为空", trigger: "blur" },
        ],
        companyNumber: [
          { required: true, message: "企业编号不能为空", trigger: "blur" },
        ],
        companyAddress: [
          { required: true, message: "企业地址不能为空", trigger: "blur" },
        ],
        companyPhone: [
          { required: true, message: "公司电话不能为空", trigger: "blur" },
        ],
        leader: [
          {
            required: true,
            message: "第一负责人姓名不能为空",
            trigger: "blur",
          },
        ],
        leaderPhone: [
          {
            required: true,
            message: "第一负责人电话不能为空",
            trigger: "blur",
          },
        ],
        companyType: [
          { required: true, message: "企业类型不能为空", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询企业信息列表 */
    getList() {
      this.loading = true;
      listCompany(this.queryParams).then((response) => {
        this.companyList = response.rows;
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
        company: null,
        abbreviation: null,
        companyNumber: null,
        companyAddress: null,
        companyPhone: null,
        managementName: null,
        managementPhone: null,
        leader: null,
        leaderPhone: null,
        message: null,
        webAddress: null,
        companyType: null,
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
      this.title = "添加企业信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getCompany(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改企业信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateCompany(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompany(this.form).then((response) => {
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
        .confirm('是否确认删除企业信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return delCompany(ids);
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
        "devDate/company/export",
        {
          ...this.queryParams,
        },
        `company_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
