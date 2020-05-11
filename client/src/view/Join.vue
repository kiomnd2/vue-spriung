<template>
  <div class="top">
    <el-row>
      <el-col class="join_top">
        <el-card class="join_main" shadow="false">
          <span>가입화면</span>
          <el-form
            :label-position="labelPosition"
            label-width="100px"
            :model="joinForm"
            :rules="rules"
            ref="joinForm"
          >
            <el-form-item prop="id" label="ID">
              <el-input
                placeholder="아이디를 입력해주세요"
                v-model="joinForm.id"
              ></el-input>
            </el-form-item>
            <el-form-item label="Email">
              <el-input
                placeholder="이메일을 입력해 주세요"
                v-model="joinForm.email"
              >
              </el-input>
            </el-form-item>
            <el-form-item prop="password" label="Password">
              <el-input
                placeholder="패스워드를 입력해주세요"
                v-model="joinForm.password"
              >
              </el-input>
            </el-form-item>
            <el-form-item prop="checkPassword" label="Confirm">
              <el-input
                      placeholder="패스워드를 재입력 해주세요"
                      v-model="joinForm.password"
              >
              </el-input>
            </el-form-item>
            <el-button type="primary" @click="btn_submit('ruleForm')">가입하기</el-button>
            <el-button @click="btn_reset('ruleForm')">RESET</el-button>
            <el-button @click="btn_back">뒤로가기</el-button>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
    </div>
</template>

<script>
  export default {
    name: 'Join',
    data() {
      return {
        labelPosition: 'left',
        joinForm : {
          id : '',
          password : '',
          checkPassword : '',
          email : '',
        }
      }
    },
    methods : {
      btn_submit (form) {
        console.log(form);
      },
      btn_reset (form) {
        console.log(form);
      },
      btn_back (){
        this.$router.push('/');
      }
    }
  };
</script>

<style scoped>
  .top {
    margin-top: 300px;
  }

  .join_top {
    text-align: center;
    display: inline-block;
  }

  .join_main {
    display: inline-block;
    height: 381px;
    width: 500px;
  }
</style>