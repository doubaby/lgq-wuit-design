<template>
  <!--基础存放容器-->
  <div class="swiper-container">
    <!-- 需要进行轮播的部分 -->
    <div class="swiper-wrapper">
      <!-- 每个节点 -->
      <div class="swiper-slide">
        <img src="../../assets/banner1.png" alt="图片1" style="width: 100%;height: 100%" />
      </div>
      <div class="swiper-slide">
        <img src="../../assets/newsLogo.png" alt="图片2" style="width: 100%;height: 100%"/>
      </div>
      <div class="swiper-slide">
        <img src="../../assets/banner2.png" alt="图片3" style="width: 100%;height: 100%"/>
      </div>
    </div>
    <!--如果需要使用分页器-->
    <div class="swiper-pagination swiper-pagination-white"></div>
    <!-- 如果需要使用前进后退按钮 -->
    <div class="swiper-button-prev swiper-button-white"></div>
    <div class="swiper-button-next swiper-button-white"></div>
  </div>
</template>
<script>
import Swiper from "swiper";
export default {
  name: "swiperView",
  data() {
    return {};
  },
  mounted() {
    this.initSwiper();
  },
  methods: {
    initSwiper() {
      const mySwiper = new Swiper(".swiper-container", {

        //设置轮播的循环方式
        loop: true,
        //设置自动播放间隔时间
        autoplay: {
          delay:3000,
          pauseOnMouseEnter: true
        },
        // 轮播效果,默认为平滑轮播
        effect: "coverflow",
        //分页器
        pagination: {
          el: '.swiper-pagination',
          dynamicBullets: true,
          dynamicMainBullets: 2
        },
        //前进后退按钮
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
          hideOnClick: true,
        },
        // 用户中断轮播后续播
        autoplayDisableOnInteraction: true,
      });
      //鼠标离开时自动播放
      mySwiper.el.onmouseover = function(){
        mySwiper.navigation.$nextEl.removeClass('hide');
        mySwiper.navigation.$prevEl.removeClass('hide');
        mySwiper.autoplay.stop();
      }
      mySwiper.el.onmouseout = function(){
        mySwiper.navigation.$nextEl.addClass('hide');
        mySwiper.navigation.$prevEl.addClass('hide');
        mySwiper.autoplay.start();
      };
    },
  },
};
</script>

<style scoped>
@import "../../../../node_modules/swiper/css/swiper.css";
.swiper-container {
  width: 100%;
  height: 550px;
  margin-top: 40px;
  background-attachment: fixed;
}

.swiper-slide img {
  width: 100%;
  object-fit: cover;

}
.swiper .hide{
  opacity:0;
}
.swiper-button-next,.swiper-button-prev{
  transition:opacity 0.5s;
}

</style>

