
<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="输入公告">
        <el-input v-model="listQuery.name" placeholder="输入标题"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="small" @click="getStuQuery">查询</el-button>
        <el-button type="warning" icon="el-icon-close" size="small" @click="resetData">重置</el-button>
      </el-form-item>
    </el-form>
    <el-button type="success" icon="el-icon-circle-plus-outline" size="small" @click="add">添加</el-button>
    <!--表格渲染数据-->
    <el-table :data="list" style="width: 100%" v-loading="listLoading">
      <el-table-column type="index" label="序号" width="80"> </el-table-column>
      <el-table-column v-for="th in tableHead" align="center" :prop="th.prop" :key="th.label" :label="th.label">
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" plain size="mini" icon="el-icon-edit" @click="getUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" plain size="mini" icon="el-icon-edit" @click="getDelById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--  分页显示-->
    <el-pagination :current-page="page" :page-size="pageSize" :total="total" style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper" @current-change="getDate" />
    <!--  添加修改页面-->
    <div>
      <el-dialog title="添加/编辑学生信息" :visible.sync="stuVisible">
        <el-form :model="stuform">
          <el-form-item v-for="th in tableHead" :key="th.label" :label="th.label" :label-width="formLabelWidth"
            :prop="th.prop" size="small">
            <el-input v-if="th.prop !== 'senddate' && th.prop !== 'expiredate'" v-model="stuform[th.prop]"
              autocomplete="off" size="small"></el-input>
            <el-date-picker v-else-if="th.prop === 'senddate'" v-model="stuform.senddate" type="datetime"
              placeholder="选择日期时间" align="right" :picker-options="pickerOptions">
            </el-date-picker>
            <el-date-picker v-else v-model="stuform.expiredate" type="datetime" placeholder="选择日期时间" align="right"
              :picker-options="pickerOptions">
            </el-date-picker>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="stuVisible = false;" size="small">取 消</el-button>
          <el-button type="primary" @click="stuformData" size="small">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import announceApi from "@/api/announce";
export default {
  name: "StuList",
  data() {
    return {
      list: [],
      page: 1, //当前第几页
      pageSize: 10, //每页多少条数据
      total: 0,
      stuform: {
        "title": null,
        "content": null,
        "senddate": null,
        "expiredate": null,
        "publisher": null,
        "ispub": null,
        "remark": null,
      },
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      tableHead: [
        { prop: "title", label: "标题" },
        { prop: "content", label: "内容" },
        { prop: "senddate", label: "发布时间" },
        { prop: "expiredate", label: "新建时间" },
        { prop: "publisher", label: "发布人" },
        { prop: "ispub", label: "是否公开" },
        { prop: "remark", label: "备注" }
      ],
      listQuery: {
        page: 1,
        limit: 20,
        title: '',
        importance: undefined,
        title: undefined,
        type: undefined
      },
      stuVisible: false, //控制页面是否显示
      formLabelWidth: "120px",
      listLoading: true
    };
  },
  created() {
    this.getDate();
  },
  methods: {
    //按照条件查询
    getStuQuery() {
      this.getDate();
    },

    getUpdate(row) {
      this.stuform = JSON.parse(JSON.stringify(row))
      this.stuVisible = true;
    },

    add() {
      this.stuform = {}
      this.stuVisible = true;
    },

    //删除数据
    getDelById(id) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return announceApi.getDelById(id);
        })
        .then((response) => {
          this.$message({
            message: response.message,
            type: "success",
          });
          this.getDate();
        })
        .catch((error) => {
          console.log("catch的error", error);
          if (error === "cancel") {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          }
        });
    },

    //查询所有数据
    getDate() {
      announceApi
        .getPageList(this.listQuery)
        .then((response) => {
          this.list = response.data.list
          this.total = response.data.total
          setTimeout(() => {
            this.listLoading = false
          }, 1.5 * 1000)
        })

    },

    stuformData() {
      if (
        !this.stuform.id == 0 ||
        !this.stuform.id == null
      ) {
        //修改方法
        announceApi.updateAnnounce(this.stuform).then((response) => {
          this.$message({
            message: response.message,
            type: "success",
          });
          this.stuform = {};
          this.stuVisible = false;
          this.getDate();
        });
      } else {
        //添加方法
        announceApi.addAnnounce(this.stuform).then((response) => {
          this.$message({
            message: response.message,
            type: "success",
          });
          this.stuform = {};
          this.stuVisible = false;
          this.getDate();
        });
      }
    },

    //重置查询条件
    resetData() {
      this.classifyQuery = {};
      this.getDate();
    }
  }
}
</script>
<style scoped></style>

    