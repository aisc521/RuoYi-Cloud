<template>
  <div style="text-align: center">
    <el-form ref="nusreForm" :model="formData" :rules="rules" size="medium" label-width="100px"
      label-position="top">
      <el-form-item label="类型" prop="tnype">
        <el-select v-model="formData.ntype" placeholder="请选择类型" clearable>
          <el-option v-for="(item, index) in typeOptions" :key="index" :label="item.label" :value="item.value"
            :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-row :gutter="250" style="text-align: center">

        <el-form-item label="日期" prop="startDate">
          <el-date-picker v-model="formData.startDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                          :style="{width: '30%'}" placeholder="请选择日期" clearable></el-date-picker>
        </el-form-item>


        <el-form-item label="时间" prop="startTime">
          <el-time-picker v-model="formData.startTime" format="HH:mm:ss" value-format="HH:mm:ss"
                          :picker-options='{"selectableRange":"00:00:00-23:59:59"}' :style="{width: '30%'}"
                          placeholder="请选择时间" clearable></el-time-picker>
        </el-form-item>

      </el-row>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>

      <el-table v-loading="loading" :data="nurseListDk">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="打卡时间" align="center" prop="nowtime" width="100">
        </el-table-column>
        <el-table-column label="类型" :formatter="statusFormat" align="center" prop="ntype" />
        <el-table-column label="间隔" align="center" prop="ninterval" />
        <el-table-column label="是否结束" :formatter="statusFormatIsEnd" align="center" prop="isEnd" />
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-form>
  </div>
</template>
<script>
import {addNurseDk,listNurseForDk} from "@/api/system/nurse";


export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        ntype: '',
      },
      // 【请填写功能名称】表格数据
      nurseListDk: [],
      rules: {
        type: [{
          required: true,
          message: '请选择类型',
          trigger: 'change'
        }],
      },
      statusIsEnd: [],
      statusOptions: [],
      typeOptions: [{
        "label": "睡觉",
        "value": 0
      }, {
        "label": "睡好啦",
        "value": 2
      }, {
        "label": "吃奶",
        "value": 1
      }, {
        "label": "吃饱啦",
        "value": 3
      }],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nowtime: undefined,
        beforetime: undefined,
        ntype: undefined,
        ninterval: undefined,
        userid: undefined
      },
    }
  },
  computed: {},
  watch: {},
  created() {
    this.getList();
    this.getDicts("sys_is_end").then(response => {
      console.log("是否结束" + JSON.stringify(response.data))
      this.statusIsEnd = response.data;
    });
    this.getDicts("sys_nurse").then(response => {
      console.log("类型" + JSON.stringify(response.data))
      this.statusOptions = response.data;
    });
  },
  mounted() {},
  methods: {
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["nusreForm"].validate(valid => {
        if (valid) {
          addNurseDk(this.formData).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                location.reload()
              } else {
                this.msgError(response.msg);
              }
            });

        }
      });
    },
    statusFormatIsEnd(row, column) {
      return this.selectDictLabel(this.statusIsEnd, row.isEnd);
    },
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.ntype);
    },
    getList() {
      this.loading = true;
      listNurseForDk(this.queryParams).then(response => {
        console.log(response.rows)
        this.nurseListDk = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    resetForm() {
      this.$refs['nusreForm'].resetFields()
    },
  }
}

</script>
<style>
</style>
