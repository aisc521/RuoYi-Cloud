<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="jdbc连接" prop="jdbcUrl">
        <el-input
          v-model="queryParams.jdbcUrl"
          placeholder="请输入jdbc连接"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账号" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="密码" prop="pwd">
        <el-input
          v-model="queryParams.pwd"
          placeholder="请输入密码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['maintenance:database:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['maintenance:database:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['maintenance:database:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['maintenance:database:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          :disabled="!selectIndex"
          icon="el-icon-upload"
          size="mini"
          @click="execute"
          v-hasPermi="['maintenance:database:sql']"
        >执行sql脚本</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="databaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="dbId" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="jdbc连接" align="center" prop="jdbcUrl" />
      <el-table-column label="账号" align="center" prop="userName" />
      <el-table-column label="密码" align="center" prop="pwd" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['maintenance:database:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['maintenance:database:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改数据库管理对话框 -->

    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="jdbc连接" prop="jdbcUrl">
          <el-input v-model="form.jdbcUrl" placeholder="请输入jdbc连接" />
        </el-form-item>
        <el-form-item label="账号" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
          <el-input v-model="form.pwd" placeholder="请输入密码" />
          <el-button :loading="loading" type="success" @click="testConnectDatabase">测试</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


      <el-dialog :title="title" :visible.sync="openFile" width="500px" append-to-body>
      <el-form ref="form" :rules="rules" size="small">
        <el-upload
          :action="databaseUploadApi + '?db_id=' + this.ids"
          :data="databaseInfo"
          :headers="headers"
          class="upload-demo"
          :on-success="handleSuccess"
          :on-error="handleError"
          drag
        >
          <i class="el-icon-upload" />
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>点击上传</em>
          </div>
          <div slot="tip" class="el-upload__tip">上传后，系统会自动执行SQL脚本</div>
        </el-upload>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel11">关闭</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listDatabase, getDatabase, delDatabase, addDatabase, updateDatabase, exportDatabase,testDbConnect } from "@/api/maintenance/database/database";
import { getToken } from '@/utils/auth'
import {mapGetters} from "vuex";

export default {
  props: {
    databaseInfo: {
      type: Object,
      id:'1111',
      default() {
        return {}
      }
    }
  },
  name: "Database",
  data() {
    return {
      indexId:'',
      currentRow: {},
      selectIndex: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 数据库管理表格数据
      databaseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openFile:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        jdbcUrl: '',
        userName: undefined,
        pwd: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        jdbcUrl: [
          { required: true, message: "jdbc连接不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "账号不能为空", trigger: "blur" }
        ],
        pwd: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
      },
      dialog: false,
      headers: {
        Authorization: getToken(),
        projectId: '1212'
      }

    };
  },
  computed: {
    ...mapGetters(['databaseUploadApi'])
  },
  created() {
    this.getList();
  },
  methods: {
    cancel() {
      this.dialog = false
    },
    handleSuccess(response, file, fileList) {
      console.log(JSON.stringify(response))
      if (response === 'success') {
        this.$notify({
          title: '执行成功',
          type: 'success',
          duration: 2500
        })
      } else {
        this.$notify({
          title: response,
          type: 'error',
          duration: 0
        })
      }
    },
    handleError(e, file, fileList) {
      const msg = JSON.parse(e.message)
      this.$notify({
        title: msg.message,
        type: 'error',
        duration: 0
      })
    },
    testConnectDatabase() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.loading = true
          testDbConnect(this.form).then((res) => {
            this.loading = false
            this.$notify({
              title: res ? '连接成功' : '连接失败',
              type: res ? 'success' : 'error',
              duration: 2500
            })
          }).catch(() => {
            this.loading = false
          })
        }
      })
    },
    /** 查询数据库管理列表 */
    getList() {
      this.loading = true;
      listDatabase(this.queryParams).then(response => {
        this.databaseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel11() {
      this.openFile = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        dbId: undefined,
        name: undefined,
        jdbcUrl: undefined,
        userName: undefined,
        pwd: undefined,
        createBy: undefined,
        updateBy: undefined,
        createTime: undefined,
        updateTime: undefined
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
      this.ids = selection.map(item => item.dbId)
      this.single = selection.length!=1
      this.multiple = !selection.length
      if (selection.length === 1) {
        const row = selection[0]
        this.selectIndex = row.dbId
        this.currentRow = row
      } else {
        this.currentRow = {}
        this.selectIndex = ''
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加数据库管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dbId = row.dbId || this.ids
      getDatabase(dbId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改数据库管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dbId != undefined) {
            updateDatabase(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addDatabase(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const dbIds = row.dbId || this.ids;
      console.log("dbIds111:" + dbIds)
      console.log("row111:" + row)
      this.$confirm('是否确认删除数据库管理编号为"' + dbIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDatabase(dbIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有数据库管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDatabase(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
  execute() {
    this.reset();
    this.openFile = true;
    this.title = "执行sql脚本";
    }
  }
};
</script>
