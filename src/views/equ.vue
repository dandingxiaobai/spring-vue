<template>
  <div style="padding:100px">
  <div style="margin:10px 0">
     设备编码:<el-input v-model="search"style="width:20%"></el-input>
    <el-button type="primary" style="margin-left:10px" @click="load">查询</el-button>
  </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          prop="id"
          label="序号"
          width="180"
          sortable>
      </el-table-column>
      <el-table-column
          prop="num"
          label="设备编码"
          width="180">
      </el-table-column>
      <el-table-column
          prop="nickname"
          label="设备名称">
      </el-table-column>
      <el-table-column
          prop="ip"
          label="设备ip">
      </el-table-column>
      <el-table-column
          prop="host"
          label="端口">
      </el-table-column>
      <el-table-column
          prop="type"
          label="协议类型">
      </el-table-column>
      <el-table-column
          prop="time"
          label="创建时间">
      </el-table-column>
      <el-table-column
          prop="peo"
          label="创建人">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
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

    </div>

  </div>
</template>

<script>
// @ is an alias to /src


import request from "@/utils/request";

export default {
  name: 'equ',
  components: {

  },
  created() {
    this.load()
  },
  data(){
    return{
      form:{},
      dialogVisible:false,
      tableData:[

      ],
      search:'',
      currentPage:1,
      total:0,
      pageSize:10,
    }
  },

  methods:{
    load(){
      request.get("/equ",{
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
        request.put("/equ",this.form).then(res =>{
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
        request.post("/equ",this.form).then(res =>{
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
     request.delete("/equ/"+id).then(res =>{
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

<style lang="scss" scoped>

</style>