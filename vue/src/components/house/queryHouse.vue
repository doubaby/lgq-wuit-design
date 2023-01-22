<template>
  <div>
    <div style="margin-top: 20px">
      <ul class="paper" v-loading="loading">
        <li class="item" v-for="(item,index) in houseVo" :key="index">
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
          @current-change="getList">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import houseList from "@/api/house/houseList";

export default {
  name: "queryHouse",
  data() {
    return {
      houseVo: {
        city: this.$route.query.city,
        price: this.$route.query.price,
        address: this.$route.query.address
      },
      total: 0,
      page: 1,
      size: 6
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList(page = 1) {
      this.page = page
      houseList.getHotHouseListQuery(this.page, this.size, this.houseVo)
          .then(res => {
            this.houseVo = res.data.records;
            this.total = res.data.total;
          })
          .catch(err => {
            console.log(err)
          })
    },
  }
}
</script>

<style scoped>
.paper {
  display: flex;
  width: 80%;
  justify-content: space-around;
  flex-flow: row wrap;
}

ul {
  list-style: none;
}

.item {
  flex: 1;
  height: 30%;
}

.info {
  /*width: 70%;*/
}
</style>
