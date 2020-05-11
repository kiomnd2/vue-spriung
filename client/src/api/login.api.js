import axios from 'axios';

//로그인 수행
const executeLogin = (form) => {
  //id : password
  return axios.post('api/login', form);
};

export { executeLogin };
