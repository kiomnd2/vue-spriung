<template>
  <div class="top">
    <el-row>
      <el-col class="login_top">
        <el-card class="login_main" shadow="false">
          <el-image :src="img_src" />
          <el-form
            :label-position="labelPosition"
            label-width="100px"
            :model="loginForm"
            :rules="rules"
            ref="loginForm"
          >
            <el-form-item prop="id">
              <el-input
                placeholder="아이디를 입력해주세요."
                v-model="loginForm.id"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                type="password"
                placeholder="패스워드를 입력해주세요."
                v-model="loginForm.password"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="btn_login('loginForm')">로그인</el-button>
              <el-button type="">회원가입</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { executeLogin } from '../api/login.api';

export default {
  name: 'Login',
  data() {
    const validPassword = (rule, value, callback) => {
      if(value == '') {
        callback(new Error('패스워드를 입력해 주세요'))
      }
    };
    const validId = (rule, value, callback) => {
      if(value == '') {
        callback(new Error('아이디를 입력해 주세요'))
      }
    };
    return {
      labelPosition: 'top',
      img_src: 'logo.jpg',
      loginForm: {
        id: '',
        password: '',
      },
      rules : {
        id : [{ validator : validId , trigger : 'blur'}],
        password : [{ validator : validPassword , trigger : 'blur'}],
      }
    };
  },
  methods: {
    btn_login(formname) {
      //검증
      console.log();
      this.$refs[formname].validate((valid) => {
        if(valid){
          executeLogin(this.form);
        }
      });
    },
  },
};
</script>

<style scoped>
.top {
  margin-top: 300px;
}

.login_top {
  text-align: center;
  display: inline-block;
}

.login_main {
  display: inline-block;
  height: 381px;
  width: 500px;
}
</style>
