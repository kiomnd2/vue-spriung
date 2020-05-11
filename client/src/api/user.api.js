import axios from 'axios';

//로그인 수행
const executeLogin = (form) => {
  //id : password
  return axios.post('api/login', form);
};

const executeJoin = (form) => {
  return axios.post('api/join', form);
};

export { executeLogin, executeJoin };
