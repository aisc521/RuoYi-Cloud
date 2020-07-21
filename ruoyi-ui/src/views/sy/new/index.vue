<template>
  <div class="app-container">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="报单人姓名" prop="userName" style="width: 250px">
          <el-input v-model="form.userName" placeholder="请输入报单人姓名" />
        </el-form-item>
        <el-form-item label="报单人性别" style="width: 250px">
          <el-select v-model="form.sex" placeholder="请选择报单人性别">
            <el-option
              v-for="dict in sys_user_sex"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="手机号码" prop="cell" style="width: 250px">
          <el-input v-model="form.cell" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="地址" prop="address" style="width: 500px">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard" style="width: 350px">
          <el-input v-model="form.idCard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="报单类型" prop="customTools" style="width: 250px">
          <el-select v-model="form.customTools" placeholder="报单类型" @change="getSelectValue">
            <el-option
              v-for="dict in sys_sy_type"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>

        </el-form-item>
        <el-form-item label="账户名" prop="accountName" style="width: 250px">
          <el-input v-model="form.accountName" placeholder="请输入账户名" />
        </el-form-item>
        <el-form-item label="账户号" prop="accountNumber" style="width: 350px">
          <el-input v-model="form.accountNumber" placeholder="请输入账户号" />
        </el-form-item>

        <!--名正言顺 弹框 -->
        <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="openUp" :showClose="false" title="请输入详细信息" width="400px">
          <el-form-item label="姓名" prop="orderName" style="width: 350px">
            <el-input v-model="form.orderName" placeholder="姓名" />
          </el-form-item>
          <el-form-item label="性别" prop="order_sex" style="width: 350px">
            <el-select v-model="form.order_sex" placeholder="请选择性别">
              <el-option
                v-for="dict in sys_user_sex"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="出生日期" prop="order_birthday" style="width: 450px">
            <el-date-picker clearable size="small" style="width: 200px"
                            v-model="form.order_birthday"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="出生日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="生肖" prop="order_zodiac" style="width: 350px">
            <el-input v-model="form.order_zodiac" placeholder="生肖" />
          </el-form-item>
          <el-form-item label="时辰" prop="order_time" style="width: 350px">
            <el-input v-model="form.order_time" placeholder="时辰" />
          </el-form-item>
          <el-form-item label="父亲姓名" prop="order_father_name" style="width: 350px">
            <el-input v-model="form.order_father_name" placeholder="父亲姓名" />
          </el-form-item>
          <el-form-item label="母亲姓名" prop="order_mother_name" style="width: 350px">
            <el-input v-model="form.order_mother_name" placeholder="母亲姓名" />
          </el-form-item>
          <el-form-item label="兄妹姓名" prop="order_bs_name" style="width: 350px">
            <el-input v-model="form.order_bs_name" placeholder="兄妹姓名" />
          </el-form-item>
          <el-form-item label="禁忌字" prop="words_tao" style="width: 350px">
            <el-input v-model="form.words_tao" placeholder="禁忌字" />
          </el-form-item>
          <el-form-item label="备注" prop="remark" style="width: 350px">
            <el-input v-model="form.remark" placeholder="备注" />
          </el-form-item>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm2">确 定</el-button>
            <el-button @click="cancel2">取 消</el-button>
          </div>
        </el-dialog>


        <!--手表 弹框 -->
        <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="openWatch" :showClose="false" title="请输入详细信息" width="400px">
          <el-form-item label="姓名" prop="orderName" style="width: 350px">
            <el-input v-model="form.orderName" placeholder="姓名" />
          </el-form-item>
          <el-form-item label="性别" prop="order_sex" style="width: 350px">
            <el-select v-model="form.order_sex" placeholder="请选择性别">
              <el-option
                v-for="dict in sys_user_sex"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="出生日期" prop="order_birthday" style="width: 450px">
            <el-date-picker clearable size="small" style="width: 200px"
                            v-model="form.order_birthday"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="出生日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="是否刻字" prop="lettering_not" style="width: 350px">
            <el-select v-model="form.lettering_not" placeholder="是否刻字">
              <el-option
                v-for="dict in sys_lettering_not"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="备注" prop="remark" style="width: 350px">
            <el-input v-model="form.remark" placeholder="备注" />
          </el-form-item>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitFormWatch">确 定</el-button>
            <el-button @click="cancelWatch">取 消</el-button>
          </div>
        </el-dialog>

        <!--戒指 弹框 -->
        <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="openJz" :showClose="false" title="请输入详细信息" width="400px">
          <el-form-item label="姓名" prop="orderName" style="width: 350px">
            <el-input v-model="form.orderName" placeholder="姓名" />
          </el-form-item>
          <el-form-item label="性别" prop="order_sex" style="width: 350px">
            <el-select v-model="form.order_sex" placeholder="请选择性别">
              <el-option
                v-for="dict in sys_user_sex"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="出生日期" prop="order_birthday" style="width: 450px">
            <el-date-picker clearable size="small" style="width: 200px"
                            v-model="form.order_birthday"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="出生日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="戒指尺寸" prop="jz_size" style="width: 350px">
              <el-input v-model="form.jz_size" placeholder="戒指尺寸" />
          </el-form-item>
          <el-form-item label="戒指名称" prop="remark" style="width: 350px">
            <el-input v-model="form.remark" placeholder="戒指名称" />
          </el-form-item>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitFormJz">确 定</el-button>
            <el-button @click="cancelJz">取 消</el-button>
          </div>
        </el-dialog>

        <!--手链 弹框 -->
        <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="openSl" :showClose="false" title="请输入详细信息" width="400px">
          <el-form-item label="姓名" prop="orderName" style="width: 350px">
            <el-input v-model="form.orderName" placeholder="姓名" />
          </el-form-item>
          <el-form-item label="性别" prop="order_sex" style="width: 350px">
            <el-select v-model="form.order_sex" placeholder="请选择性别">
              <el-option
                v-for="dict in sys_user_sex"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="出生日期" prop="order_birthday" style="width: 450px">
            <el-date-picker clearable size="small" style="width: 200px"
                            v-model="form.order_birthday"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="出生日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="备注" prop="remark" style="width: 350px">
            <el-input v-model="form.remark" placeholder="备注" />
          </el-form-item>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitFormSl">确 定</el-button>
            <el-button @click="cancelSl">取 消</el-button>
          </div>
        </el-dialog>

        <!--风水牌 弹框 -->
        <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="openFsp" :showClose="false" title="请输入详细信息" width="400px">

          <el-form-item label="请选择类型" prop="type_name" style="width: 350px">
            <el-select v-model="form.type_name" placeholder="请选择类型">
              <el-option
                v-for="dict in sys_fs_pai"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="备注" prop="remark" style="width: 350px">
            <el-input v-model="form.remark" placeholder="备注" />
          </el-form-item>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitFormFsp">确 定</el-button>
            <el-button @click="cancelFsp">取 消</el-button>
          </div>
        </el-dialog>


        <!--寻龙撅 弹框 -->
        <el-dialog append-to-body :close-on-click-modal="false" :visible.sync="openXlj" :showClose="false" title="请输入详细信息" width="400px">

          <el-form-item label="请选择类型" prop="type_name" style="width: 350px">
            <el-select v-model="form.type_name" placeholder="请选择类型" >
              <el-option
                v-for="dict in sys_xun_long_jue"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="备注" prop="remark" style="width: 350px">
            <el-input v-model="form.remark" placeholder="备注" />
          </el-form-item>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitFormXlj">确 定</el-button>
            <el-button @click="cancelXlj">取 消</el-button>
          </div>
        </el-dialog>


      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: left">
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
  </div>
</template>

<script>
import { addOrder } from "@/api/sy/new/order";

export default {
  name: "Order",
  data() {
    return {
      openXlj:false,
      openFsp:false,
      openSl:false,
      openJz:false,
      openWatch:false,
      openUp:false,
      // 是否显示弹出层
      open: false,
      sys_user_sex:[],
      sys_sy_type:[],
      sys_lettering_not:[],
      sys_fs_pai:[],
      sys_xun_long_jue:[],
      // 查询参数
      queryParams: {
        type_name:undefined,
        jz_size:undefined,
        lettering_not:undefined,
        remark:undefined,
        words_tao:undefined,
        order_bs_name:undefined,
        order_mother_name:undefined,
        order_father_name:undefined,
        order_zodiac:undefined,
        order_time:undefined,
        order_birthday:undefined,
        pageNum: 1,
        pageSize: 10,
        createDate: undefined,
        guidanceTeacher: undefined,
        orderId: undefined,
        serviceTeacher: undefined,
        userName: undefined,
        sex: undefined,
        cell: undefined,
        address: undefined,
        idCard: undefined,
        customTools: undefined,
        accountName: undefined,
        accountNumber: undefined,
        orderName:undefined,
        order_sex:undefined
      },
      // 表单参数
      form: {
        type_name:undefined,
        jz_size:undefined,
        lettering_not:undefined,
        remark:undefined,
        words_tao:undefined,
        order_bs_name:undefined,
        order_mother_name:undefined,
        order_father_name:undefined,
        order_zodiac:undefined,
        order_time:undefined,
        order_birthday:undefined,
        pageNum: 1,
        pageSize: 10,
        createDate: undefined,
        guidanceTeacher: undefined,
        orderId: undefined,
        serviceTeacher: undefined,
        userName: undefined,
        sex: undefined,
        cell: undefined,
        address: undefined,
        idCard: undefined,
        customTools: undefined,
        accountName: undefined,
        accountNumber: undefined,
        orderName:undefined,
        order_sex:undefined
      },
      selectType:'',
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getDicts("sys_user_sex").then(response => {
      this.sys_user_sex = response.data;
    });
    this.getDicts("sys_sy_type").then(response => {
      this.sys_sy_type = response.data;
    });
    this.getDicts("sys_lettering_not").then(response => {
      this.sys_lettering_not = response.data;
    });
    this.getDicts("sys_fs_pai").then(response => {
      this.sys_fs_pai = response.data;
    });
    this.getDicts("sys_xun_long_jue").then(response => {
      this.sys_xun_long_jue = response.data;
    });

  },
  methods: {
    getSelectValue(val){
      console.log(val)
      if(val == 1){
        this.openUp = true;
      }
      if(val == 2){
        this.openWatch = true;
      }
      if(val == 3){
        this.openWatch = true;
      }
      if(val == 4){
        this.openJz = true;
      }
      if(val == 5){
        this.openSl = true;
      }
      if(val == 6){
        this.openSl = true;
      }
      if(val == 7){
        this.openSl = true;
      }
      if(val == 8){
        this.openFsp = true;
      }
      if(val == 9){
        this.openXlj = true;
      }

      /*this.selectType = selVal.code;
      this.name = selVal.label;
      console.log(selVal);*/
      //console.log("1212" + selVal);
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        createDate: undefined,
        guidanceTeacher: undefined,
        orderId: undefined,
        serviceTeacher: undefined,
        userName: undefined,
        sex: undefined,
        cell: undefined,
        address: undefined,
        idCard: undefined,
        customTools: undefined,
        remark: undefined,
        accountName: undefined,
        accountNumber: undefined
      };
      this.resetForm("form");
    },
    /** 提交按钮 */
    submitForm: function() {
      if(this.form.userName == '' || this.form.userName == 'undefined' || this.form.userName == undefined){
        this.msgError("报单人姓名不能为空");
        return;
      }
      if(this.form.sex == '' || this.form.sex == 'undefined' || this.form.sex == undefined){
        this.msgError("报单人性别不能为空");
        return;
      }
      if(this.form.cell == '' || this.form.cell == 'undefined' || this.form.cell == undefined){
        this.msgError("手机号码不能为空");
        return;
      }
      if(this.form.address == '' || this.form.address == 'undefined' || this.form.address == undefined){
        this.msgError("地址不能为空");
        return;
      }
      if(this.form.idCard == '' || this.form.idCard == 'undefined' || this.form.idCard == undefined){
        this.msgError("身份证号不能为空");
        return;
      }
      if(this.form.accountName == '' || this.form.accountName == 'undefined' || this.form.accountName == undefined){
        this.msgError("账户名不能为空");
        return;
      }
      if(this.form.accountNumber == '' || this.form.accountNumber == 'undefined' || this.form.accountNumber == undefined){
        this.msgError("账户号不能为空");
        return;
      }
      this.$refs["form"].validate(valid => {
            addOrder(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
      });
    },
    submitForm2(){
      console.log(this.form.orderName)
      if(this.form.orderName == '' || this.form.orderName == 'undefined' || this.form.orderName == undefined){
        this.msgError("姓名不能为空");
        return;
      }
      if(this.form.order_sex == '' || this.form.order_sex == 'undefined' || this.form.order_sex == undefined){
        this.msgError("性别不能为空");
        return;
      }
      if(this.form.order_birthday == '' || this.form.order_birthday == 'undefined' || this.form.order_birthday == undefined){
        this.msgError("出生日期不能为空");
        return;
      }
      if(this.form.order_zodiac == '' || this.form.order_zodiac == 'undefined' || this.form.order_zodiac == undefined){
        this.msgError("生肖不能为空");
        return;
      }
      if(this.form.order_time == '' || this.form.order_time == 'undefined' || this.form.order_time == undefined){
        this.msgError("时辰不能为空");
        return;
      }
      if(this.form.order_father_name == '' || this.form.order_father_name == 'undefined' || this.form.order_father_name == undefined){
        this.msgError("父亲姓名不能为空");
        return;
      }
      if(this.form.order_mother_name == '' || this.form.order_mother_name == 'undefined' || this.form.order_mother_name == undefined){
        this.msgError("母亲姓名不能为空");
        return;
      }
     this.openUp = false;

    },
    cancel2(){
      this.openUp = false;
      this.resert();
    },
    submitFormWatch(){
      if(this.form.orderName == '' || this.form.orderName == 'undefined' || this.form.orderName == undefined){
        this.msgError("姓名不能为空");
        return;
      }
      if(this.form.order_sex == '' || this.form.order_sex == 'undefined' || this.form.order_sex == undefined){
        this.msgError("性别不能为空");
        return;
      }
      if(this.form.order_birthday == '' || this.form.order_birthday == 'undefined' || this.form.order_birthday == undefined){
        this.msgError("出生日期不能为空");
        return;
      }
      if(this.form.lettering_not == '' || this.form.lettering_not == 'undefined' || this.form.lettering_not == undefined){
        this.msgError("请选择是否刻字");
        return;
      }
      this.openWatch = false;

    },
    cancelWatch(){
      this.openWatch = false;
      this.resert();
    },
    submitFormJz(){
      if(this.form.orderName == '' || this.form.orderName == 'undefined' || this.form.orderName == undefined){
        this.msgError("姓名不能为空");
        return;
      }
      if(this.form.order_sex == '' || this.form.order_sex == 'undefined' || this.form.order_sex == undefined){
        this.msgError("性别不能为空");
        return;
      }
      if(this.form.order_birthday == '' || this.form.order_birthday == 'undefined' || this.form.order_birthday == undefined){
        this.msgError("出生日期不能为空");
        return;
      }
      if(this.form.jz_size == '' || this.form.jz_size == 'undefined' || this.form.jz_size == undefined){
        this.msgError("戒指尺寸不能为空");
        return;
      }
      if(this.form.remark == '' || this.form.remark == 'undefined' || this.form.remark == undefined){
        this.msgError("戒指名称不能为空");
        return;
      }

      this.openJz = false;

    },
    cancelJz(){
      this.openJz = false;
      this.resert();
    },

    cancelSl(){
      this.openSl = false;
      this.resert();
    },

    submitFormSl(){
      if(this.form.orderName == '' || this.form.orderName == 'undefined' || this.form.orderName == undefined){
        this.msgError("姓名不能为空");
        return;
      }
      if(this.form.order_sex == '' || this.form.order_sex == 'undefined' || this.form.order_sex == undefined){
        this.msgError("性别不能为空");
        return;
      }
      if(this.form.order_birthday == '' || this.form.order_birthday == 'undefined' || this.form.order_birthday == undefined){
        this.msgError("出生日期不能为空");
        return;
      }
      this.openSl = false;

    },

    submitFormFsp(){
      if(this.form.type_name == '' || this.form.type_name == 'undefined' || this.form.type_name == undefined){
        this.msgError("请选择类型");
        return;
      }
      this.openFsp = false;

    },
    cancelFsp(){
      this.openFsp = false;
      this.resert();
    },
    submitFormXlj(){
      if(this.form.type_name == '' || this.form.type_name == 'undefined' || this.form.type_name == undefined){
        this.msgError("请选择类型");
        return;
      }
      this.openXlj = false;

    },
    cancelXlj(){
      this.openXlj = false;
      this.resert();
    },
    resert(){

        this.form.type_name=''
        this.form.jz_size=''
        this.form.lettering_not=''
        this.form.remark=''
        this.form.words_tao=''
        this.form.order_bs_name=''
        this.form.order_mother_name=''
        this.form.order_father_name=''
        this.form.order_zodiac=''
        this.form.order_time=''
        this.form.order_birthday=''
        this.form.orderName=''
        this.form.order_sex=''
    }
  }
};
</script>
