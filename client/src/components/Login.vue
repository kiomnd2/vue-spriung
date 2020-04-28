<template>
  <div class="top">
    <el-row>
      <el-col class="login_top">
        <el-card class="login_main" shadow="false">
          <el-image :src="img_src" />
          <el-form
            :label-position="labelPosition"
            label-width="100px"
            :model="form"
          >
            <el-form-item>
              <el-input
                placeholder="아이디를 입력해주세요."
                v-model="form.id"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-input
                type="password"
                placeholder="패스워드를 입력해주세요."
                v-model="form.password"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @submit="btn_login">로그인</el-button>
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
    return {
      labelPosition: 'top',
      img_src: 'logo.jpg',
      form: {
        id: '',
        password: '',
      },
    };
  },
  methods: {
    btn_login() {
      //검증
      executeLogin(this.form);
    },
  },
};
</script>

<style scoped>
.top {
  margin-top: 400px;
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
