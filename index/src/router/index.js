import Vue from 'vue'
import Router from 'vue-router'
// 解梦师后台
import DreamMaster from '@/pages/dreamMaster/DreamMaster'
Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    name: 'DreamMaster',
    component: DreamMaster
  }]
})
