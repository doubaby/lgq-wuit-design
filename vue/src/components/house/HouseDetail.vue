<template>
  <div class="paper">
    <div class="left">
      <p style="font-weight: bold;font-size: 20px;margin-bottom: 40px;">{{ houseDetails.roomDetail }}</p>
      <img style="width: 550px; height: 440px" :src="houseDetails.houseUrl">
    </div>
    <div class="right">
      <p class="price">{{ houseDetails.price }}元/㎡</p>
      <div class="jianZhu">
        <P>建筑类型</P>
        <P>物业费用</P>
        <P>物业公司</P>
        <P>开发商</P>
        <P>楼栋总数</P>
        <P>房屋总数</P>
        <P>附近门店</P>
      </div>
    </div>
    <div class="demo-image__preview">
      <el-image
          style="width: 100px; height: 100px"
          :src="url"
          :preview-src-list="srcList">
      </el-image>
    </div>
  </div>
</template>

<script>
import houseList from "@/api/house/houseList";
import jsCookie from "js-cookie";

export default {
  name: "HouseDetail",
  data() {
    return {
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      srcList: [
        'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
        'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
      ],
      houseDetails: {},
    }
  },
  created() {
    this.houseDetail()
  },
  methods: {
    houseDetail() {
      let houseId = this.$route.params.houseId //获取url路径上的houseId
      houseList.getHouseDetail(houseId)
          .then(res => {
            if (res.code === 20000){
              this.houseDetails = res.data
            }else {
              this.$router.push({path: '/login'})
              this.$message.warning("登陆以查看详情")
            }
          })
          .catch(err => {
            console.log(err)
          })

    }
  }
}
</script>
<style scoped>
.paper {
  width: 800px;
  height: 570px;
  display: inline-block;
  margin-left: 0px;
}

.img {
  width: 550px;
  height: 350px;
  margin-top: 60px;
  margin-left: -800px;
}

.left {
  float: left;
  margin-left: -100px;
  width: 60%;
  margin-top: 60px;
}

.right {
  margin-right: -100px;
  float: right;
  width: 60%;
  margin-top: 80px;
}

.price {
  font-weight: bold;
  font-family: Tahoma;
  color: #DB4C3F;
  font-size: 55px;
  margin: 0px 13px 0px 0px;
  margin-top: 25px;
}

.jianZhu {
  line-height: 3.5;
  margin: 0 auto;
}
</style>

