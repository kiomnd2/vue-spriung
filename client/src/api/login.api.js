import axios from 'axios';

//로그인 수행
const executeLogin = (form) => {
  //id : password
  axios.post('/api/login',  form ).then(res =>{
    console.log(res);
  });
};

export default { executeLogin };
