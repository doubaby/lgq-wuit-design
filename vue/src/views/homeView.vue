<template>
  <div class="home">
<!--    <navigation style="margin: 0;padding: 0;font-size: 0"></navigation>-->
<!--    <swiperVue style="width: auto;margin: 0;padding: 0;font-size: 0" />-->
<!--    <div style="margin-top: 20px">-->
<!--      <el-select-->
<!--          v-model="value"-->
<!--          multiple-->
<!--          filterable-->
<!--          remote-->
<!--          reserve-keyword-->
<!--          placeholder="请输入关键词"-->
<!--          :remote-method="remoteMethod"-->
<!--          :loading="loading">-->
<!--        <el-option-->
<!--            v-for="item in options"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value">-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--      <el-select v-model="value" placeholder="请选择">-->
<!--        <el-option-group-->
<!--            v-for="group in options"-->
<!--            :key="group.label"-->
<!--            :label="group.label">-->
<!--          <el-option-->
<!--              v-for="item in group.options"-->
<!--              :key="item.value"-->
<!--              :label="item.label"-->
<!--              :value="item.value">-->
<!--          </el-option>-->
<!--        </el-option-group>-->
<!--      </el-select>-->
<!--      <el-select v-model="value" clearable placeholder="请选择">-->
<!--        <el-option-->
<!--            v-for="item in options"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value">-->
<!--        </el-option>-->
<!--      </el-select>-->
<!--      <el-button type="primary" icon="el-icon-search" style="margin-left: 5px">搜索</el-button>-->
<!--      </div>-->
<!--      <div>-->
<!--        <h2 style="margin-top: 20px">热门房屋 Hot house</h2>-->
<!--    </div>-->
<!--    <div>-->

<!--    </div>-->
<!--   <hot-house-list/>-->
</div>
</template>

<script>
import swiperVue from '../components/swiper.vue'
import navigation from "@/components/navigation";
import hotHouseList from "@/components/hotHouseList";
export default {
  name: "index",
  components:{
    // swiperVue,
    // navigation,
    // hotHouseList
  },
  data() {
    return {
      options: [{
      label: '热门城市',
      options: [{
        value: 'Shanghai',
        label: '上海'
      }, {
        value: 'Beijing',
        label: '北京'
      }]
    }, {
      label: '城市名',
      options: [{
        value: 'Chengdu',
        label: '成都'
      }, {
        value: 'Shenzhen',
        label: '深圳'
      }, {
        value: 'Guangzhou',
        label: '广州'
      }, {
        value: 'Dalian',
        label: '大连'
      }]
    }],
      value: [],
      list: [],
      loading: false,
      states: [],

    }
  },
  mounted() {
    this.list = this.states.map(item => {
      return { value: `value:${item}`, label: `label:${item}` };
    });
  },
  methods: {
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
          this.options = this.list.filter(item => {
            return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.options = [];
      }
    }
  }
}
</script>
<style>
</style>
