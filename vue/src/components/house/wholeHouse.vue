<template>
  <div>
  <div>
    <p style="font-size:30px;margin-bottom: 15px">最新整租</p>
    <span style="font-family: 'Muli',sans-serif;color: #72809D">给你推荐了一些最新的发布的整租房子</span>
  </div>
  <div class="container">
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
    <div class="pagination">
      <el-pagination
          :current-page="page"
          :page-size="size"
          :total="total"
          style="padding: 30px 0; text-align: center;"
          layout="total, prev, pager, next, jumper"
          @current-change="wholePage">
      </el-pagination>
  </div>
  </div>
</template>

<script>
import houseList from "@/api/house/houseList";

export default {
  name: "wholeHouse",
  data() {
    return {
      wholeLists:{},
      total:0,
      page:1,
      size:6
    }
  },
  created() {
    this.wholePage()
  },
  methods: {
    wholePage(page=1){
      this.page=page
      houseList.wholeListPage(this.page,this.size).then(res=>{
        if (res.code === 20000){
          this.wholeLists=res.data.records
          this.total=res.data.total
        }else {
          this.$router.push({path: '/login'})
          this.$message.warning("登陆以查看详情")
        }
      })
    },
    gotoDetail(houseId) {
      this.$router.push({path: '/HouseDetail', name: 'HouseDetail', params: {houseId: houseId}})
    }
  }
}
</script>

<style scoped>
pagination {
  padding: 20px 0px 30px 0px;

}

.paper .item a {
  color: #000;
  margin: 0px;
  padding: 0px;
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


.paper * {
  margin: 0;
  padding: 0;
}

.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}

.top li {
  display: flex;
  align-items: center;
}

.wrapper .top li {
  margin: 20px;
  padding: 0px;
}

.houseIma {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
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

.container {
  margin-left: 100px;
}

ul {
  list-style: none;
}

.paper {
  display: flex;
  width: 80%;
  justify-content: space-around;
  flex-flow: row wrap;
}

</style>
