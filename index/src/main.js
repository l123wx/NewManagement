// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import fastClick from 'fastclick'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import globalData from '@/assets/javaScript/global.js'
import 'styles/reset.css'
import 'styles/adaption.css'
import 'styles/border.css'
import 'styles/iconfont/iconfont.css'
import 'swiper/dist/css/swiper.css'
import Mui from 'vue-awesome-mui';

Vue.config.productionTip = false;
fastClick.attach(document.body); // 使用fastclick
Vue.use(VueAwesomeSwiper);
Vue.prototype.$globalData = globalData; //全局变量
Vue.use(Mui);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
