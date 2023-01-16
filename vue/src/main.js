import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'swiper/css/swiper.min.css'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import store from './store/index'
import vueCookie from 'vue-cookies'
import axios from "axios";

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.use(vueCookie);
Vue.prototype.bus = new Vue()
Vue.prototype.$axios = axios
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
