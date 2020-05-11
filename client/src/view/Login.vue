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
            <el-form-item prop="id" label="ID">
              <el-input
                placeholder="아이디를 입력해주세요."
                v-model="loginForm.id"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password" label="Password">
              <el-input
                type="password"
                placeholder="패스워드를 입력해주세요."
                v-model="loginForm.password"
              ></el-input>
            </el-form-item>
            <el-button type="primary" @click="btn_login('loginForm')">로그인</el-button>
            <el-button v-on:click="btn_join" type="">회원가입</el-button>
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
      if (value === '') {
        callback(new Error('패스워드를 입력해 주세요'));
      }
      callback();
    };
    const validId = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('아이디를 입력해 주세요'));
      }
      callback();
    };
    return {
      labelPosition: 'left',
      img_src: 'logo.jpg',
      loginForm: {
        id: '',
        password: '',
      },
      rules: {
        id: [{ validator: validId, trigger: 'blur' }],
        password: [{ validator: validPassword, trigger: 'blur' }],
      },
    };
  },
  methods: {
    btn_login(formname) {
      //검증
      this.$refs[formname].validate((valid) => {
        if (valid) {
          console.log(executeLogin);
          executeLogin({
            id: this.loginForm.id,
            password: this.loginForm.password,
          });
        }
      });
    },
    btn_join() {
      this.$router.push('join');
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
