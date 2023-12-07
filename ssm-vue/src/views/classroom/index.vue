
<template>
  <div class="app-container">
    <el-form :inline="true" :model="listQuery" class="demo-form-inline">
      <el-form-item label="输入教室编号">
        <el-input v-model="listQuery.name" placeholder="输入编号"></el-input>
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
    <el-pagination :current-page.sync="listQuery.page" :page-size.sync="listQuery.limit" :page-sizes="[5, 10, 15, 20, 50]"
      :total="total" style="padding: 30px 0; text-align: center" layout="total, prev, pager, next, jumper"
      @current-change="getDate" />
    <!--  添加修改页面-->
    <div>
      <el-dialog title="添加/编辑教室信息" :visible.sync="stuVisible">
        <el-form :model="stuform">
          <el-form-item v-for="th in tableHead" :key="th.label" :label="th.label" :label-width="formLabelWidth"
            :prop="th.prop" size="small">
            <el-input v-if="th.prop !== 'address'" v-model="stuform[th.prop]" autocomplete="off" size="small"></el-input>
            <el-select v-model="stuform.address" placeholder="请选择" v-else>
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
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
import classroomApi from "@/api/classroom";
export default {
  name: "StuList",
  data() {
    return {
      list: [],
      total: 0,
      stuform: {
        "id": null,
        "classid": null,
        "address": null,
        "status": null,
        "maxmember": null,
        "remark": null,

      },
      tableHead: [
        { prop: "classid", label: "教室编号" },
        { prop: "address", label: "地址" },
        { prop: "status", label: "状态" },
        { prop: "maxmember", label: "人数" },
        { prop: "remark", label: "描述" }
      ],
      options: [{
        value: '1栋',
        label: '1栋'
      }, {
        value: '2栋',
        label: '2栋'
      }, {
        value: '3栋',
        label: '3栋'
      }, {
        value: '4栋',
        label: '4栋'
      }, {
        value: '5栋',
        label: '5栋'
      }, {
        value: '6栋',
        label: '6栋'
      }, {
        value: '7栋',
        label: '7栋'
      }, {
        value: '8栋',
        label: '8栋'
      }, {
        value: '9栋',
        label: '9栋'
      }, {
        value: '10栋',
        label: '10栋'
      }],
      listQuery: {
        page: 1,
        limit: 20,
        classid: '',
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
          return classroomApi.getDelById(id);
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

    stuformData() {
      if (
        !this.stuform.id == 0 ||
        !this.stuform.id == null
      ) {
        //修改方法
        classroomApi.updateClassroom(this.stuform).then((response) => {
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
        classroomApi.addClassroom(this.stuform).then((response) => {
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

    