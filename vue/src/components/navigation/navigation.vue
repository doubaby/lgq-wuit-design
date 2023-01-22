<!--学生考试首页-->
<template>
  <div id="student">
    <el-row class="padding-50">
      <el-col :span="24">
        <a><img style="background-color: #f4f6f9;position: absolute;top: 8px;left: 2px;width: 70px;height: 45px" :src="imgUrl"/></a>
        <ul class="list">
          <li class="logo"><i class="iconfont icon-kaoshi"></i><span>房屋租赁平台</span></li>
          <li><a href="javascript:;" @click="home()">首页</a></li>
          <li><a href="javascript:;" @click="practice()">房屋租赁</a></li>
          <li>
            <router-link to="/Board">公告信息</router-link>
          </li>
          <li>
            <router-link to="/Message">给我留言</router-link>
          </li>
          <li><a href="javascript:;">待定</a></li>
          <p style="margin-left: 600px;font-size: 15px;padding-top: 25px" v-if="!loginInfo.nickname">
            <span style="margin-top: 10px;cursor: pointer" @click="login">登录</span>
            <span style="margin-left: 10px;cursor: pointer" @click="register">注册</span>
          </p>
          <p  style="margin-left: 600px;font-size: 15px;padding-top: 8px" v-if="loginInfo.nickname">
            <span><el-avatar :src="loginInfo.avatar"></el-avatar></span>
            <span style="margin-left: 12px;top: 20px;cursor: pointer;position: absolute">{{ loginInfo.nickname }}</span>
            <span style="margin-top: 12px;right: 38px;cursor: pointer;position: absolute" @click="logout">退出</span>
          </p>
        </ul>
      </el-col>
    </el-row>
    <!--路由区域-->
    <div class="main">
      <router-view></router-view>
    </div>
    <v-footer></v-footer>
  </div>
</template>

<script>
import myFooter from "@/components/navigation/myFooter";
import swiper from "@/components/navigation/swiper";
import jsCookie from "js-cookie";
import login from "@/api/login/login";

export default {
  components: {
    "v-footer": myFooter,
    // eslint-disable-next-line vue/no-unused-components
    swiper
  },
  data() {
    return {
      imgUrl:require('@/assets/header.png'),
      flag: false,
      loginInfo: ''
    }
  },
  created() {
    this.showInfo()
  },
  methods: {
    showInfo() {
      let token = jsCookie.get("token");
      login.getUserInfo(token).then(res => {
        this.loginInfo = res.data
      })

    },
    logout() {
      //清空cookie
      jsCookie.set('token', '', {domain: 'localhost'}),
          this.$notify.success("安全退出")
          window.location.href = "/"
    },
    login() {
      this.$router.push({path: '/login'})
    },
    register() {
      this.$router.push({path: '/register'})
    },
    home(){
      this.$router.push({path: '/'})
    },
    practice(){
      this.$router.push({path: '/queryHouse'})

    }
  },


  // queryUserInfo() {
  //   login.UserInfoShow()
  //       .then((res) => {
  //         if (!res) {
  //           return
  //         }
  //         // 保存用户详情
  //         this.loginInfo = res.data;
  //         // 保存到本地
  //         jsCookie.set("userInfo", JSON.stringify(res.data))
  //       })
  //       .catch(err => {
  //         this.$message.error(err);
  //       })
  // },
}
</script>

<style scoped>
.right .icon {
  margin-right: 6px;
}

#student .padding-50 {
  margin: 0 auto;
  padding: 0 50px;
  box-shadow: 0 0 10px 4px rgba(1, 149, 255, 0.1);
  background-color: #f4f6f9;
}

.list a {
  text-decoration: none;
  color: #334046;
}

li {
  font-size: 20px;
  list-style: none;
  height: 60px;
  line-height: 60px;
}

#student .list {
  display: flex;
}

#student .list li {
  padding: 0 20px;
  cursor: pointer;
}

#student .list li:hover {
  background-color: #0195ff;
  transition: all 2s ease;
}

#student .list li:hover a {
  color: #fff;
}

#student .list .right {
  margin-left: auto;
  position: relative;
}

#student .list li.right :hover a {
  color: #000;
}

#student .list .logo {
  display: flex;
  font-weight: bold;
  color: #afbac2;
}

#student .list .logo i {
  font-size: 60px;
}

.right .msg {
  text-align: center;
  position: absolute;
  top: 60px;
  left: 0px;
  display: flex;
  flex-direction: column;
  border-radius: 2px;
  border-bottom: 3px solid #0195ff;
  background-color: #fff;
}

.right .msg p {
  height: 40px;
  line-height: 40px;
  width: 105px;
}

.right .msg p:hover {
  background-color: #0195ff;
}
</style>
