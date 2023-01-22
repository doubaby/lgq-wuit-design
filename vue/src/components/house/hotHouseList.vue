<template>
  <div id="myExam">
    <div class="wrapper">
      <div class="start">
        <p class="mainFamily" style="margin-bottom: 5px">开始一段美好的生活吧！</p>
        <span>搜索你所在的城市的小区或房屋类型吧！</span>
      </div>
      <!--幻灯片组件-->
      <div style="margin-bottom: 30px">
        <swiper-view class="SwiperView"></swiper-view>
      </div>
      <div class="container">
        <ul class="top">
          <li class="search-li">
            <el-select class="input" style="width: 120px" clearable placeholder="选择城市" v-model="city">
              <el-option :value="0" label="深圳市"/>
              <el-option :value="1" label="武汉市"/>
            </el-select>
          </li>
          <li class="search-li">
            <el-select class="input" style="width: 120px" clearable placeholder="选择价格" v-model="price">
              <el-option :value="1000" label="1000-2000"/>
              <el-option :value="2000" label="2000-3000"/>
              <el-option :value="3000" label="3000-4000"/>
            </el-select>
          </li>
          <li style="margin-left: 0px">
            <el-input class="input" style="width: 400px;" placeholder="输入房屋信息查询" v-model="address"></el-input>
          </li>
          <li style="float: right;margin-left: -15px;">
            <el-button style="border-radius:17px;" slot="append" icon="el-icon-search" @click="getList(city,price,address)">搜索</el-button>
          </li>
        </ul>
      </div>
      <div>
        <p style="font-size:30px;margin-bottom: 15px">最新整租</p>
        <span style="font-family: 'Muli',sans-serif;color: #72809D">给你推荐了一些最新的发布的整租房子</span>
      </div>
      <div style="margin-top: 20px">
        <ul class="paper" v-loading="loading">
          <li class="item" v-for="(item,index) in wholeLists" :key="index">
            <div class="info" style="margin-right: 0px">
              <i><img class="houseIma" :src="item.houseUrl" @click="gotoDetail(item.houseId)">
              </i>
              <div class="bottom-title">
                <span>{{ item.city == 0 ? '已出租' : '未出租' }}</span>
                <span>{{ item.roomDetail }}</span>
                <span class="price">{{ item.price }}<span class="price-cons">元/月</span></span>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div>
        <el-button style="background-color: darkorange"><span style="color: #f4f6f9;font-size: 20px" @click="gotoWhole">查看更多整租房</span><i
            style="color: #f4f6f9;font-size: 20px" class="el-icon-arrow-right el-icon--right"></i></el-button>
      </div>
      <div>
        <p style="font-size:30px;margin-bottom: 15px;margin-top: 50px">最新合租</p>
        <span style="font-family: 'Muli',sans-serif;color: #72809D">给你推荐了一些最新的发布的合租房子</span>
      </div>
      <div style="margin-top: 20px">
        <ul class="paper" v-loading="loading">
          <li class="item" v-for="(item,index) in togetherLists" :key="index">
            <div class="info" style="margin-right: 0px">
              <i><img class="houseIma" :src="item.houseUrl" @click="gotoDetail(item.houseId)">
              </i>
              <p class="room">{{ item.address }}</p>
              <div class="bottom-title">
                <span>{{ item.city == 0 ? '已出租' : '未出租' }}</span>
                <span>{{ item.roomDetail }}</span>
                <span class="price">{{ item.price }}<span class="price-cons">元/月</span></span>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div>
        <el-button style="background-color: darkorange;margin-bottom: 60px" @click="gotoTogether"><span
            style="color: #f4f6f9;font-size: 20px">查看更多合租房</span><i style="color: #f4f6f9;font-size: 20px"
                                                                    class="el-icon-arrow-right el-icon--right"></i>
        </el-button>
      </div>
      <div style="clear: both"></div>
    </div>
  </div>
</template>

<script>
import houseList from "@/api/house/houseList";
import SwiperView from "@/components/navigation/swiper";
import jsCookie from "js-cookie";

export default {
  components: {
    SwiperView
  },
  // name: 'myExam'
  data() {
    return {
      wholeLists: {},
      togetherLists: {},
      loading: false,
        city: '',
        price: '',
        address: ''
    }
  },
  created() {
    this.loading = true
    this.wholeList(), this.togetherList()
  },
  methods: {
    getList(city,price,address) {
      console.log(city)
      this.$router.push({path: '/queryHouse', name: 'queryHouse', query: {city: city,price:price,address:address}})
    },
    gotoWhole() {
      let token = jsCookie.get('token');
      if (token!='' || token != null) {
        this.$router.push('/wholeHouse')
      } else {
        this.$router.push({path: '/login'})
        this.$message.warning("登陆以查看详情")
      }
    },
    gotoTogether() {
      let token = jsCookie.get('token');
      if (token!='' || token != null) {
        this.$router.push('/wholeHouse')
      } else {
        this.$router.push({path: '/login'})
        this.$message.warning("登陆以查看详情")
      }
    },
    gotoDetail(houseId) {
      this.$router.push({path: '/HouseDetail', name: 'HouseDetail', params: {houseId: houseId}})
    },
    wholeList() {
      houseList.wholeList().then(res => {
        this.wholeLists = res.data
        this.loading = false
      })
    },
    togetherList() {
      houseList.togetherList().then(res => {
        this.togetherLists = res.data
        this.loading = false
      })
    }
  }
}
</script>

<style lang="less" scoped>
.pagination {
  padding: 20px 0px 30px 0px;

  .el-pagination {
    display: flex;
    justify-content: center;
  }
}

.paper {
  width: 90%;

  h4 {
    cursor: pointer;
  }

  list-style-type: none;
  margin: 0 auto;
  padding: 0px;
}

.paper .item a {
  color: #000;
  margin: 0px;
  padding: 0px;
}

.wrapper .top .order {

  cursor: pointer;
}

.wrapper .top .order:hover {
  color: #0195ff;
  border-bottom: 22px solid #0195ff;
}

.wrapper .top .order:visited {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}

.item .info i {
  margin-right: 5px;
  color: #0195ff;
}

.item .info {
  margin-right: 14px;
}

.paper .item {
  width: 350px;
  border-radius: 4px;
  padding: 10px 20px;
  border: 1px solid #eee;
  box-shadow: 0 0 4px 2px rgba(217, 222, 234, 0.3);
  transition: all 0.6s ease;
  margin: 0px;
  padding: 0px;
  margin-bottom: 30px;
  margin-right: 10px;
  margin-top: 0px;
}

.paper .item:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
}

.paper .item .info {
  font-size: 14px;
  color: #88949b;
}

.paper .item .name {
  font-size: 14px;
  color: #88949b;
}

.paper * {
  margin: 0;
  padding: 0;
}

.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  //margin: 0;
}

.top .el-icon-search {
  position: absolute;
  right: 10px;
  top: 10px;
}

.top .icon {
  position: relative;
}

.wrapper .top {
  margin-bottom: 20px;
}

#myExam .search-li {
  margin-left: 0px;
  margin-right: 0px;
}

#myExam .search-li-input {
  margin-left: 0px;
  margin-right: 10px;
}

.top .search-li {
  margin-left: auto;
}

.top li {
  display: flex;
  align-items: center;
}

.top .search {
  margin-left: 0px;
  margin-right: 0px;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

.top .search:hover {
  color: #0195ff;
  border-color: #0195ff;
}

.wrapper .top {
  display: flex;
}

.wrapper .top li {
  margin: 20px;
  padding: 0px;
}

#myExam {
  width: 100%;
  margin: 0px;
  padding: 0px;
}

#myExam .title {
  margin: 0px;
  text-align: center;
  color: #88949b;
  font-size: 33px;
  line-height: 68px;
  font-weight: 600;
  font-family: \\5FAE\8F6F\96C5\9ED1, Hiragino Sans GB;
  letter-spacing: -2.4px;
}

#myExam .wrapper {
  background-color: #fff;
  margin: 0px;
  padding: 0px;
}

.houseIma {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

.selectCss {
  width: 120px;
  padding-right: 0px;
  margin-left: 2px;
  margin-right: 2px;
}

.SwiperView {
  margin-top: 0px;
  width: 100%;
}

/deep/ .price {
  font-family: Hiragino Sans GB, Microsoft Yahei UI, Microsoft Yahei, \\5FAE\8F6F\96C5\9ED1, Segoe UI, Tahoma, "\5B8B\4F53   b8b\4F53", SimSun, sans-serif;
  color: #FA5741;
  font-size: 18px;
  font-weight: 900;
  line-height: 20px;
}

/deep/ .price-cons {
  font-family: Hiragino Sans GB, Microsoft Yahei UI, Microsoft Yahei, \\5FAE\8F6F\96C5\9ED1, Segoe UI, Tahoma, "\5B8B\4F53   b8b\4F53", SimSun, sans-serif;
  color: #FA5741;
  font-size: 12px;
}

/deep/ .bottom-title {
  margin-right: 0px;
  padding: 0px;
  display: flex;
  justify-content: space-around;

}

.bottom-title span {
  margin-right: 0px;
  padding: 0px;
  flex: 1;
}

.bottom-title span:nth-child(2) {
  margin: 0px;
  padding: 0px;
  flex: 2;
}

.room {
  margin-top: 12px;
  margin-bottom: 12px;
  font-size: 18px;
  font-weight: 700;
  line-height: 24px;
  color: #222;
}

.container {
  z-index: 2;
  position: absolute;
  top: 300px;
  right: 400px;

}

.input {
  /deep/ .el-input__inner {
    border-radius: 17px;
  }
}

.start {
  z-index: 3;
  position: absolute;
  top: 150px;
  right: 520px;
  color: #ffffff;
}

.mainFamily {
  font-size: 45px;
  font-family: 'Work Sans', sans-serif;
  font-weight: 700;
}

</style>
