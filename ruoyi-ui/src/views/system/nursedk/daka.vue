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
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import {addNurseDk} from "@/api/system/nurse";

export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        ntype: '',
      },
      rules: {
        type: [{
          required: true,
          message: '请选择类型',
          trigger: 'change'
        }],
      },
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
    }
  },
  computed: {},
  watch: {},
  created() {},
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
              } else {
                this.msgError(response.msg);
              }
            });

        }
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
