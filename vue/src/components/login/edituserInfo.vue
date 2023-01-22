<template>
  <div></div>
</template>

<script>
import login from "@/api/login/login";

export default {
  name: "edituserInfo",
  data() {
    return {
      user: {},
      info: {}
    }
  },
  created() {
    this.checkLogin();
  },
  methods: {
    checkLogin() {
      // 查询用户信息
      login.EditUserInfo
          .then((res) => {
            this.user = res.data;
            this.info = JSON.parse(sessionStorage.getItem("userInfo")) || {}
          })
          .catch(err => {
            this.$message.error(err);
          })
    }
  }
}
</script>

<style scoped>

</style>
