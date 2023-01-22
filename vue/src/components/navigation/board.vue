<template>
  <div>
    <el-card class="TopCard" style="height:50px">
      <!-- 小喇叭 -->
      <i class="el-icon-chat-dot-round" style="color:#606266;position: absolute;left: 10px"/>
      <span style="color:#606266;margin-top: 10px" class="tips">公告：</span>
      <!-- 滚动文字外层div，文字能展示的区域-->
      <div class="noticeBox"
           :style="'width:'+noticeWidth+'px;height:60px;position:relative;overflow:hidden;display:inline-block;vertical-align:middle;margin-top:-5px'">
        <!-- 滚动div，marginLeft变化-->
        <div :style="'margin-left:'+marginLeft+'px;white-space:nowrap'">
          <span class="showNotice">{{ showNotice }}</span>
          <!-- 不会展示，用来测量第二条与第一条重合时的长度 -->
          <span class="notice">{{ notice }}</span>
        </div>
      </div>
    </el-card>
    <P style="position: absolute;
 color:rgba(255,255,255,0.7);left: 200px;top: 230px;font-family: Tahoma;font-size: 45px;font-weight: bold">新闻咨询</P>
    <div>
      <img class="page-title" src="http://verio.liuyanzhao.com/assets/img/inner-banner.jpg"/>
    </div>
    <div class="info">
      <ul class="ulCss">
        <li class="paper-board" v-for="(item,index) in newsList" :key="index">
          <p class="top-title">{{ item.title }}</p>
          <p class="news-content">{{ item.content }}</p>
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
          @current-change="pageNews">
      </el-pagination>
    </div>
    <div style="clear: both"></div>
  </div>
</template>

<script>
import newsApi from "@/api/house/news";

export default {
  name: 'Home',
  data() {
    return {
      page: 1,
      size: 6,
      total: 0,
      newsList: {},
      // 适应屏幕分辨率
      noticeWidth: window.screen.width - 150,
      // 公告展示（过长时会重复两遍）
      showNotice: '',
      // 用于公告过长时，获取重复两遍中第一遍的长度
      notice: '',
      // 公告初始位置
      marginLeft: 100,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      news: {}
    }
  },
  mounted() {
    this.noticeData = ['房价跌拉致力成为\n' +
    '\n' +
    '新居住领域领先智库致力成为\n' +
    '\n' +
    '新居住领域领先智库致力成为\n' +
    '\n' +
    '新居住领域领先智库致力成为\n' +
    '\n' +
    '新居住领域领先智库致力成为\n' +
    '\n' +
    '新居住领域领先智库', '致力成为\n' +
    '\n' +
    '新居住领域领先智库', '致力成为\n' +
    '\n' +
    '新居住领域领先智库', '致力成为\n' +
    '\n' +
    '新居住领域领先智库', '致力成为\n' +
    '\n' +
    '新居住领域领先智库']
    this.noticeData.forEach((val, index) => {
      if (index === 0) {
        this.showNotice += '【' + (index + 1) + '】' + val
      } else {
        this.showNotice += '\xa0\xa0\xa0\xa0\xa0\xa0【' + (index + 1) + '】' + val
      }
    })
    // 公告上面先赋值，再获取宽度
    setTimeout(() => {
      // 公告div长度
      var oneNoticeWidth = document.getElementsByClassName('showNotice')[0].offsetWidth
      // 公告外层div长度
      var noticeBoxWidth = document.getElementsByClassName('noticeBox')[0].offsetWidth
      // 一条公告长度太大时，才滚动
      if (oneNoticeWidth > noticeBoxWidth) {
        // 滚动公告需要将公告重复两遍进行滚动，两遍中间需要加空格
        this.notice = this.showNotice + '\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0'
        // 上面先赋值，再获取宽度
        setTimeout(() => {
          // 获取一遍加中间空格的长度，即左移时第二遍与第一遍完全重合时的长度
          var oneNoticeAddEmptyWidth = document.getElementsByClassName('notice')[0].offsetWidth
          // 公告内容重复两遍
          this.showNotice = this.notice + this.showNotice
          this.timer = setInterval(() => {
            this.marginLeft -= 1
            // 第二遍与第一遍起始位置重合时（第一条已完全移到左侧隐藏），marginLeft置0，即回到第一条
            if (this.marginLeft === (-1) * oneNoticeAddEmptyWidth) {
              this.marginLeft = 0
            }
          }, 20)
        }, 10)
      } else { //公告并没有很长时不滚动
        this.marginLeft = 0
      }
    }, 10)
  },
  created() {
    this.pageNews()
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    pageNews(page = 1) {
      this.page = page
      newsApi.pageNews(this.page, this.size).then(res => {
        this.newsList = res.data.records;
        this.total = res.data.total;
      })
    }
  }
}
</script>
<style scoped>
/* 公告card */
.TopCard .el-card__body {
  padding: 0px 10px
}

/* 公告title */
.tips {
  position: absolute;
  top: 54px;
  left: 26px;
  line-height: 50px;
  color: #606266;
  font-weight: bold;
}

.paper-board {
  border-radius: 12px;
  list-style: none;
  background-color: #ffffff;
  /*width: 95%;*/
  height: 200px;
  margin: 20px auto;
  box-shadow: 1px 1px 1px 1px #fff;
}

.paper-board:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
  transition: transform .3s;
}

.info {
  background-color: #f4f6f9;
  width: 80%;
  height: 100%;
  margin: 20px auto;

}

.page-title {
  object-fit: cover;
  width: 100%;
  height: 250px;
}

.ulCss {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  flex-wrap: wrap;
}

.top-title {
  padding-top: 12px;
  margin: 0;
  font-weight: 700;
  font-size: 20px;
  font-family: 'Muli', sans-serif;
  color: #140c40;
}

.news-content {
  padding-top: 10px;
  padding-left: 20px;
  line-height: 28px;
  background: #fff;
  color: #72809D;
  font-size: 15px;
  font-family: 'Muli', sans-serif;
  margin: 0;
  text-align: left;
  overflow-x: hidden !important;
  font-weight: 400;
}
</style>
