import axios from 'axios';

//로그인 수행
const executeLogin = (form) => {
  //id : password
  axios.post('api/login',  form ).then(res =>{
    //TODO 로그인처리
    console.log(res)
  });
};

export { executeLogin };
