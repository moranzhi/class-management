<template>
  <el-row>
    <el-col :span="8" style="padding-right: 10px">
      <el-card class="box-card">
        <div class="user">
          <img src="@/assets/404_images/kn.jpg" alt="" />
          <div class="userinfo">
            <p class="name">Admin</p>
            <p class="access">超级管理员</p>
          </div>
        </div>
        <div class="login-info">
          <p>上次登录时间: <span>2023.12.27</span></p>
          <p>上次登录地点: <span>上海 浦东</span></p>
        </div>
      </el-card>
      <el-card style="margin-top: 20px; height: 460px">
        <el-table :data="list" style="width: 100%">
          <el-table-column type="index" label="序号" width="80"> </el-table-column>
          <el-table-column v-for="th in tableHead" align="center" :prop="th.prop" :key="th.label" :label="th.label">
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
    <el-col :span="16" style="padding-left: 10px">
      <div class="num">
        <el-card v-for="item in countData" :key="item.name" :body-style="{ display: 'flex', padding: 0 }">
          <i class="icon" :class="`el-icon-${item.icon}`" :style="{ background: item.color }"></i>
          <div class="detail">
            <p class="price">￥{{ item.value }}</p>
            <p class="desc">{{ item.name }}</p>
          </div>
        </el-card>
      </div>
      <el-card style="height: 400px">
        <div ref="mychart" style="height: 400px"></div>

      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import * as echarts from "echarts";
import classroomApi from "@/api/classroom";

export default {
  name: "dashedrName",
  data() {
    return {
      list: [],
      page: 1,
      pageSize: 5,
      total: 0,
      ordersQuery: {},
      orderForm: {
        ordersNumber: "",
        ordersPrice: "",
      },
      tableHead: [
        { prop: "classid", label: "教室编号" },
        { prop: "address", label: "地址" },
        { prop: "status", label: "状态" },
        { prop: "maxmember", label: "人数" },
        { prop: "remark", label: "描述" }
      ],
      countData: [
        {
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          value: 210,
          icon: "star-on",
          color: "#ffb980",
        },
        {
          value: 1234,
          icon: "s-goods",
          color: "#5ab1ef",
        },
        {
          value: 1234,
          icon: "success",
          color: "#2ec7c9",
        },
        {
          value: 210,
          icon: "star-on",
          color: "#ffb980",
        },
        {
          value: 1234,
          icon: "s-goods",
          color: "#5ab1ef",
        },
      ],


    }
  },

  created() {
  },
  async mounted() {
    await classroomApi
      .getPageList(this.listQuery)
      .then((response) => {
        this.list = response.data.list
        this.total = response.data.total
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    let mycharts = echarts.init(this.$refs.mychart);
    const addresses = this.list.map(item => item.address + '教室人数');
    const sum = this.list.map(item => item.maxmember);
    console.log(addresses, 67667);
    let myColor = ['#eb2100', '#eb3600', '#d0570e', '#d0a00e', '#34da62', '#00e9db', '#00c0e9', '#0096f3', '#33CCFF', '#33FFCC']
    let option = {
      backgroundColor: '#0e2147',
      grid: {
        left: '11%',
        top: '12%',
        right: '0%',
        bottom: '8%',
        containLabel: true
      },
      xAxis: [{
        show: false,
      }],
      yAxis: [{
        axisTick: 'none',
        axisLine: 'none',
        offset: '27',
        axisLabel: {
          textStyle: {
            color: '#ffffff',
            fontSize: '16',
          }
        },
        data: addresses
      }, {
        axisTick: 'none',
        axisLine: 'none',
        axisLabel: {
          textStyle: {
            color: '#ffffff',
            fontSize: '16',
          }
        },
        data: sum
      }, {
        name: '分拨延误TOP 10',
        nameGap: '50',
        nameTextStyle: {
          color: '#ffffff',
          fontSize: '16',
        },
        axisLine: {
          lineStyle: {
            color: 'rgba(0,0,0,0)'
          }
        },
        data: [],
      }],
      series: [{
        name: '条',
        type: 'bar',
        yAxisIndex: 0,
        data: sum,
        label: {
          normal: {
            show: true,
            position: 'right',
            textStyle: {
              color: '#ffffff',
              fontSize: '16',
            }
          }
        },
        barWidth: 12,
        itemStyle: {
          normal: {
            color: function (params) {
              var num = myColor.length;
              return myColor[params.dataIndex % num]
            },
          }
        },
        z: 2
      }, {
        name: '白框',
        type: 'bar',
        yAxisIndex: 1,
        barGap: '-100%',
        barWidth: 20,
        itemStyle: {
          normal: {
            color: '#0e2147',
            barBorderRadius: 5,
          }
        },
        z: 1
      }, {
        name: '外框',
        type: 'bar',
        yAxisIndex: 2,
        barGap: '-100%',
        barWidth: 24,
        itemStyle: {
          normal: {
            color: function (params) {
              var num = myColor.length;
              return myColor[params.dataIndex % num]
            },
            barBorderRadius: 5,
          }
        },
        z: 0
      },
      {
        name: '外圆',
        type: 'scatter',
        hoverAnimation: false,
        yAxisIndex: 2,
        symbolSize: 35,
        itemStyle: {
          normal: {
            color: function (params) {
              var num = myColor.length;
              return myColor[params.dataIndex % num]
            },
            opacity: 1,
          }
        },
        z: 2
      }
      ]
    }
    mycharts.setOption(option);

  },


  methods: {
    getDate() {
      classroomApi
        .getPageList(this.listQuery)
        .then((response) => {
          this.list = response.data.list
          this.total = response.data.total
          setTimeout(() => {
            this.listLoading = false
          }, 1.5 * 1000)
        })
    },
  },
};
</script>

<style lang="scss" scoped>
.user {
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;

  img {
    margin-right: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }

  .userinfo {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }

    .access {
      color: #999999;
    }
  }
}

.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;

    span {
      color: #666666;
      margin-left: 60px;
    }
  }
}

.num {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;

  .icon {
    width: 80px;
    height: 80px;
    font-size: 30px;
    text-align: center;
    line-height: 80px;
    color: #fff;
  }

  .detail {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .el-card {
    width: 32%;
    margin-bottom: 20px;
  }

  .price {
    font-size: 30px;
    margin-bottom: 10px;
    line-height: 30px;
    height: 30px;
  }

  .desc {
    font-size: 14px;
    color: #999;
    text-align: center;
  }
}

.graph {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;

  .el-card {
    width: 48%;
  }
}
</style>