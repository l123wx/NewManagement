
let token = '';
if(localStorage.getItem('token')){
  token =localStorage.getItem('token')
}

// 用户的个人信息
let userInfo = {};
// if(localStorage.getItem('userInfo')){
//   userInfo = JSON.parse(localStorage.getItem('userInfo'))
// }



export default{
  userInfo,
  token,   //用户token信息
}