<template>
  <div style="width:100%; height:100vh; background-color: bisque; overflow: hidden">
    <div style="width: 400px; margin: 150px auto;">
      <div style="color: brown;font-size: 40px;text-align: center;padding: 30px">欢迎注册</div>
      <el-form ref="form" :model="form" label-width="80px" size="normal" :rules="rules">
        <el-form-item label="账号" style="margin: 20px" prop="username">
          <el-input prefix-icon="el-icon-user" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" style="margin: 20px" prop="password">
          <el-input prefix-icon="el-icon-lock" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" style="margin: 20px" prop="confirm">
          <el-input prefix-icon="el-icon-lock" v-model="form.confirm" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 30%" @click="register">注册</el-button>
          <el-button type="primary" style="width: 30%;margin-left: 100px " @click="change">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name:"Register",
  data(){
    return {
      form:{},
      rules: {
        username: [
          {required: true, message: '请输入账号名称', trigger: 'blur'},

        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        confirm:[
          {required: true, message: '请输入密码', trigger: 'blur'},
        ]
      }
    }
  },
  methods:{
    register(){

      if(this.form.password!=this.form.confirm){
        this.$message({
          type:"error",
          message:"两次密码输入不一致"
        })
        return
      }
      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/user/register",this.form).then(res=>{
            if(res.code==='0'){
              this.$message({
                type:"success",
                message:"注册成功"
              })
              this.$router.push("/login")//登陆跳转
            }else{
              this.$message({
                type:"error",
                message:res.msg
              })
            }

          })
        }
      })

    },
    change(){
      this.$router.push("/login")
    }
  }
}
</script>

<style lang="scss" scoped>

</style>