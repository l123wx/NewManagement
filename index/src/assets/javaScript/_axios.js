import axios from 'axios'
import { Toast } from 'vant';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.headers.get['Content-Type'] = 'application/x-www-form-urlencoded'
axios.defaults.baseURL ='http://192.168.0.104:8081'
axios.defaults.transformRequest = [function (data) {
  let ret = ''
  for (let it in data) {
    ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
  }
  return ret
}]

axios.interceptors.request.use(
config => {
  Toast.loading({
    duration: 0, // 持续展示 toast
    forbidClick: true,
    message: '请稍后',
  });
    return config;
}, err => { //请求错误处理
  Toast.fail('请求失败');
  Promise.reject(err)
});

axios.interceptors.response.use(
  res => { //成功请求到数据
    Toast.clear();
    return res.data
  },
  err => { //响应错误处理
    if (err && err.response) {
      switch (err.response.status){
        case 400:
            Toast.fail('错误请求')
          break;
        case 401:
            Toast.fail('未授权，请重新登录')
          break;
        case 403:
          Toast.fail('拒绝访问')
          break;
        case 404:
          Toast.fail('请求错误,未找到该资源')
          break;
        case 405:
          Toast.fail('请求方法未允许')
          break;
        case 408:
          Toast.fail('请求超时')
          break;
        case 500:
          Toast.fail('服务器端出错')
          break;
        case 501:
          Toast.fail('网络未实现')
          break;
        case 502:
          Toast.fail('网络错误')
          break;
        case 503:
          Toast.fail('服务不可用')
          break;
        case 504:
          Toast.fail('网络超时')
          break;
        case 505:
          Toast.fail('http版本不支持该请求')
          break;
        default:
          Toast.fail(`连接错误${err.response.status}`)
      }
    } else {
      Toast.fail('连接到服务器失败');
    }
    return Promise.reject(err.response)
  }
);
// 新建一条新闻
export function createNew(options){
  return new Promise((resolve,reject) => {
    axios({
        url: '/CreateNew',
        method: 'get',
        params: options
    }).then(res=>{
        resolve(res)
    }).catch(err=>{
        reject(err)
    })
  })
}
// 修改一条新闻
export function reviseNew(options){
  return new Promise((resolve,reject) => {
    axios({
        url: '/ReviseNew',
        method: 'get',
        params: options
    }).then(res=>{
        resolve(res)
    }).catch(err=>{
        reject(err)
    })
  })
}
// 审核一条新闻
export function auditNew(options){
  return new Promise((resolve,reject) => {
    axios({
        url: '/AuditNew',
        method: 'get',
        params: options
    }).then(res=>{
        resolve(res)
    }).catch(err=>{
        reject(err)
    })
  })
}
// 删除一条新闻
export function deleteNew(options){
  return new Promise((resolve,reject) => {
    axios({
        url: '/DeleteNew',
        method: 'get',
        params: options
    }).then(res=>{
        resolve(res)
    }).catch(err=>{
        reject(err)
    })
  })
}
// 获取所有新闻
export function getNewLists(options){
  return new Promise((resolve,reject) => {
    axios({
        url: '/GetNewLists',
        method: 'get',
        params: options
    }).then(res=>{
        resolve(res)
    }).catch(err=>{
        reject(err)
    })
  })
}
// 获取审核的新闻
export function getAuditNewLists(options){
  return new Promise((resolve,reject) => {
    axios({
        url: '/GetAuditNewLists',
        method: 'get',
        params: options
    }).then(res=>{
        resolve(res)
    }).catch(err=>{
        reject(err)
    })
  })
}