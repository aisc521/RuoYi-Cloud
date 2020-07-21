<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="应用编号" prop="appId">
        <el-input
          v-model="queryParams.appId"
          placeholder="请输入应用编号"
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
          v-hasPermi="['maintenance:deploy:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['maintenance:deploy:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['maintenance:deploy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['maintenance:deploy:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          :disabled="!selectIndex"
          icon="el-icon-upload"
          size="mini"
          @click="serverStatus"
          v-hasPermi="['maintenance:deploy:serverStatus']"
        >状态查询</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          :disabled="!selectIndex"
          icon="el-icon-upload"
          size="mini"
          @click="startServer"
          v-hasPermi="['maintenance:deploy:startServer']"
        >启动</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          :disabled="!selectIndex"
          icon="el-icon-upload"
          size="mini"
          @click="stopServer"
          v-hasPermi="['maintenance:deploy:stopServer']"
        >停止</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          :disabled="!selectIndex"
          icon="el-icon-upload"
          size="mini"
          @click="onedeploy"
          v-hasPermi="['maintenance:deploy:onedeploy']"
        >一键部署</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="deployList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="deployId" />
      <el-table-column label="应用名称" align="center" prop="appName" />
      <el-table-column label="服务器列表" align="center" prop="serverName" />
      <el-table-column label="部署日期" align="center" prop="createDate" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['maintenance:deploy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['maintenance:deploy:remove']"
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

    <!-- 添加或修改部署管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form1" :rules="rules" label-width="80px">
        <el-form-item label="应用" prop="app.id">
          <el-select v-model.number="form1.app.id" placeholder="请选择" style="width: 370px">
            <el-option v-for="item in apps" :key="item.appId" :label="item.name" :value="item.appId" />
          </el-select>
        </el-form-item>
        <el-form-item label="服务器" prop="deploys">
          <el-select v-model="form1.deploys" multiple placeholder="请选择" style="width: 370px">
            <el-option v-for="item in servers" :key="item.serverId" :label="item.name" :value="item.serverId" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="openUp" title="应用部署" width="400px">
      <el-form ref="form2" :model="form2" :rules="rules" size="small">
        <el-upload
          :action="deployUploadApi + '?id=' + this.ids"
          :data="deployInfo"
          :headers="headers"
          :on-success="handleSuccess"
          :on-error="handleError"
          class="upload-demo"
          drag
        >
          <i class="el-icon-upload" />
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>点击上传</em>
          </div>
          <div slot="tip" class="el-upload__tip">多个应用上传文件名称为all.zip,数据库更新脚本扩展名为.sql,上传成功后系统自动部署系统。</div>
        </el-upload>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel">关闭</el-button>
      </div>
    </el-dialog>
    <fForm ref="sysRestore" :key="times" :app-name="appName" />

  </div>
</template>

<script>
import { listDeploy, getDeploy, delDeploy, addDeploy, updateDeploy, exportDeploy ,queryApp,queryServer,serverStatusquery,serverStart,serverStop} from "@/api/maintenance/deploy/deploy";
import { getToken } from '@/utils/auth'
import {mapGetters} from "vuex";

const defaultForm = { id: null, app: { id: null }, deploys: [] }
export default {
  name: "Deploy",
  data() {
    return {
      headers: {
        Authorization: getToken()
      },
      deployInfo: {},
      form2:'',
      selectIndex:'',
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
      // 部署管理表格数据
      deployList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openUp:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        appId: undefined,
      },
      // 表单参数
      form: {},
      form1:{id: null, app: { id: null }, deploys: [] },
      // 表单校验
      rules: {
        'app.id': [
          { required: true, message: '应用不能为空', trigger: 'blur', type: 'number' }
        ],
        deploys: [
          { required: true, message: '服务器不能为空', trigger: 'blur' }
        ]
      },
      apps: [], servers: [],
    };
  },
  created() {
    this.getList();

  },
  computed: {
    ...mapGetters(['deployUploadApi'])
  },
  methods: {

    handleSuccess(response, file, fileList) {
      this.cancelUp()
    },
    // 监听上传失败
    handleError(e, file, fileList) {
      const msg = JSON.parse(e.message)
      this.$notify({
        title: msg.message,
        type: 'error',
        duration: 2500
      })
    },
    serverStatus(){
      serverStatusquery(this.selectIndex).then(response => {
        this.$notify({
          title: JSON.stringify(response),
          type: 'success',
          duration: 0
        })

      });
    },
    startServer() {
      serverStart(this.selectIndex).then(response => {
        this.$notify({
          title: JSON.stringify(response),
          type: 'success',
          duration: 0
        })

      });
    },
    stopServer() {
      serverStop(this.selectIndex).then(response => {
        this.$notify({
          title: JSON.stringify(response),
          type: 'success',
          duration: 0
        })

      });
    },
    onedeploy() {
      this.reset();
      this.openUp = true;
      this.title = "一键部署";
    },
    sysRestore(){
      this.$refs.sysRestore.dialog = true
    },
    /** 查询部署管理列表 */
    getList() {
      this.loading = true;
      listDeploy(this.queryParams).then(response => {
        this.deployList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancelUp(){
      this.openUp = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        deployId: undefined,
        appId: undefined,
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
      this.ids = selection.map(item => item.deployId)
      console.log(this.ids[0])
      this.selectIndex = this.ids[0]
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      //查询 app 和service
      queryApp().then(response => {
        console.log("返回参数:" + JSON.stringify(response))
        this.apps = response.rows;
      });
      queryServer().then(response => {
        console.log("返回参数:" + JSON.stringify(response))
        this.servers = response.rows;
        const deploys = []
        form1.deploys.forEach(function(deploy, index) {
          deploys.push(deploy.id)
        })
        this.form1.deploys = deploys
      });
      this.reset();
      this.open = true;
      this.title = "添加部署管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deployId = row.deployId || this.ids
      getDeploy(deployId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改部署管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      const deploys = []
      this.form1.deploys.forEach(function(data, index) {
        const deploy = { id: data }
        deploys.push(deploy)
      })
      this.form1.deploys = deploys

          if (this.form1.deployId != undefined) {
            updateDeploy(this.form1).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addDeploy(this.form1).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }

    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const deployIds = row.deployId || this.ids;
      this.$confirm('是否确认删除部署管理编号为"' + deployIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDeploy(deployIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有部署管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDeploy(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
  }
};
</script>
