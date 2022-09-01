<template>
  <div style="padding:100px">
    <el-container>
      <el-aside width="1400px">  <div>
        <el-button type="primary" @click="add">新增按钮</el-button>
        <el-button type="primary">导入按钮</el-button>
        <el-button type="primary">导出按钮</el-button>
      </div>
        <div style="margin:10px 0">
          <el-input v-model="search" placeholder="请输入关键字" style="width:20%"></el-input>
          <el-button type="primary" style="margin-left:10px" @click="load">查询</el-button>
        </div>
        <el-table
            :data="tableData"
            border
            stripe
            style="width: 100%">
          <el-table-column
              prop="id"
              label="id"
              width="180"
              sortable>
          </el-table-column>
          <el-table-column
              prop="username"
              label="用户名"
              width="180">
          </el-table-column>
          <el-table-column
              prop="nickName"
              label="昵称">
          </el-table-column>
          <el-table-column
              prop="age"
              label="年龄">
          </el-table-column>
          <el-table-column
              prop="sex"
              label="性别">
          </el-table-column>
          <el-table-column
              prop="address"
              label="地址">
          </el-table-column>
          <el-table-column
              fixed="right"
              label="操作"
              width="100">
            <template slot-scope="scope">
              <el-button @click="handleEdit(scope.row)" type="text" >编辑</el-button>
              <el-popconfirm
                  title="确认删除吗？" @confirm="handleDelete(scope.$index,scope.row.id)"
              >
                <template #reference>
                  <el-button size="mini">删除</el-button>
                </template>
              </el-popconfirm>

            </template>
          </el-table-column>
        </el-table>
        <div style="margin:10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>

          <el-dialog
              title="提示"
              :visible.sync="dialogVisible"
              width="30%">
            <el-form :model="form" label-width="120px">
              <el-form-item label="用户名">
                <el-input v-model="form.username"></el-input>
              </el-form-item>
              <el-form-item label="昵称">
                <el-input v-model="form.nickName"></el-input>
              </el-form-item>
              <el-form-item label="年龄">
                <el-input v-model="form.age"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <el-radio v-model="form.sex" label="男">男</el-radio>
                <el-radio v-model="form.sex" label="女">女</el-radio>
              </el-form-item>
              <el-form-item label="地址">
                <el-input  type="textarea" v-model="form.address"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
          </el-dialog>
        </div></el-aside>
      <el-main>
        <el-tree
            :data="data"
            show-checkbox
            node-key="id"
            :default-expanded-keys="[2]"
            :default-checked-keys="[5,6,7,8]"
            :props="defaultProps">
        </el-tree>
      </el-main>
    </el-container>

  </div>
</template>

<script>
// @ is an alias to /src


import request from "@/utils/request";

export default {

  name: 'HomeView',
  components: {
    
  },
  created() {
    this.load()
  },
  data(){
    return{
      msg:'外部消息',
      form:{},
      dialogVisible:false,
      tableData:[

      ],
        search:'',
        currentPage:1,
        total:0,
        pageSize:10,
      data: [{
        id: 1,
        label: '系统管理',
        children: [{
          id: 3,
          label: '添加',
        },
          {
            id: 4,
            label: '删除',
          },
        ]
      },
        {
        id: 2,
        label: '用户管理',
        children: [{
          id: 5,
          label: '增加'
        }, {
          id: 6,
          label: '删除'
        },
          {
            id: 7,
            label: '修改'
          },
          {
            id: 8,
            label: '查询'
          }
        ]
      },
      ],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },

  methods:{
    load(){
      request.get("/user",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search,}
      }).then(res=>{
        console.log(res)
        this.tableData =res.data.records
        this.total=res.data.total
      })
    },
    add(){
  this.dialogVisible=true
      this.form={}
    },
        save(){
      if(this.form.id){ //更新
        request.put("/user",this.form).then(res =>{
          console.log(res)
          if(res.code==='0'){
            this.$message({
              type:"success",
              message:"更新成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        this.load() //刷新表格
          this.dialogVisible=false//关闭弹窗
        })
      }else{  //新增
        request.post("/user",this.form).then(res =>{
          console.log(res)
          if(res.code==='0'){
            this.$message({
              type:"success",
              message:"新增成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load() //刷新表格
          this.dialogVisible=false//关闭弹窗
        })
      }
        },
      handleEdit(row){
      //深拷贝防止提前录入
          this.form=JSON.parse(JSON.stringify(row))
        //打开弹窗
        this.dialogVisible=true
      },
      handleSizeChange(pageSize){
      this.pageSize=pageSize
          this.load()
      },
      handleCurrentChange(pageNum){
      this.pageNum=pageNum
    this.load()
      },
      handleDelete(id){
        console.log(id)
        request.delete("/user/"+id).then(res =>{
          if(res.code==='0'){
            this.$message({
              type:"success",
              message:"删除成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
          this.load()
        })
      }
    }
}
</script>
