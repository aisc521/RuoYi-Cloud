<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="本次时间" prop="nowtime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.nowtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="本次时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="上次时间" prop="beforetime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.beforetime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="上次时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="打卡类型" prop="ntype">
        <el-select v-model="queryParams.ntype" placeholder="请选择" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否结束" prop="isEnd">
        <el-select v-model="queryParams.isEnd" placeholder="请选择" clearable size="small">
        <el-option
          v-for="dict in statusIsEnd"
          :key="dict.dictValue"
          :label="dict.dictLabel"
          :value="dict.dictValue"
        />
        </el-select>
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
          v-hasPermi="['system:nurse:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:nurse:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:nurse:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:nurse:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="nurseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户id" align="center" prop="id" />
      <el-table-column label="打卡时间" align="center" prop="nowtime" width="180">
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.nowtime) }}</span>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="上次时间" align="center" prop="beforetime" width="180">
      <!--  <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beforetime) }}</span>
        </template>-->
      </el-table-column>
      <el-table-column label="打卡类型" :formatter="statusFormat" align="center" prop="ntype" />
      <el-table-column label="打卡间隔" align="center" prop="ninterval" />
      <el-table-column label="是否结束" :formatter="statusFormatIsEnd" align="center" prop="isEnd" />
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">


        <el-form-item label="打卡类型">
          <el-select v-model="form.ntype" placeholder="请选择">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
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
import { listNurse, getNurse, delNurse, addNurse, updateNurse, exportNurse } from "@/api/system/nurse";

export default {
  name: "Nurse",
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
      // 总条数
      total: 0,
      // 【请填写功能名称】表格数据
      nurseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      statusOptions: [],
      statusIsEnd: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nowtime: undefined,
        beforetime: undefined,
        ntype: undefined,
        ninterval: undefined,
        userid: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_nurse").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_is_end").then(response => {
      this.statusIsEnd = response.data;
    });
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listNurse(this.queryParams).then(response => {
        this.nurseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.ntype);
    },
    statusFormatIsEnd(row, column) {
      return this.selectDictLabel(this.statusIsEnd, row.isEnd);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        nowtime: undefined,
        beforetime: undefined,
        ntype: undefined,
        ninterval: undefined,
        userid: undefined
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getNurse(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateNurse(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addNurse(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除【请填写功能名称】编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delNurse(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有【请填写功能名称】数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportNurse(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
