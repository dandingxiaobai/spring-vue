<template>
    <div style="padding: 100px">
      <el-container>
        <el-header style="height: 450px">
          <h1 style="font-weight: bold">监控日志</h1>
          <el-table :data="tableData"
             >
            <el-table-column
                prop="date"
                width="900px">
            </el-table-column>
          </el-table>
          <el-pagination
              background
              layout="prev, pager, next"
              :total="1000">
          </el-pagination>
        </el-header>
        <el-main style=" width: 1600px;color: brown;height: 500px">
          <el-row>
            <el-col :span="4" style="margin: 50px"><div class="grid-content bg-purple">
              <div style="font-size: 15px;text-align: center;color: black">
                <img src="../assets/123.webp"style="height: 150px;width: 150px"><br>
                设备名称:xxx<br>
                运行状态:xxx<br>
                加工个数:xxx<br>
                扭矩值:xxx<br>
                温度:xxx<br>
                湿度:xxx<br>
              </div>
            </div></el-col>
            <el-col :span="4"style="margin: 50px"><div class="grid-content bg-purple-light">
              <div style="font-size: 15px;text-align: center;color: black">
                <img src="../assets/123.webp"style="height: 150px;width: 150px"><br>
                设备名称:xxx<br>
                运行状态:xxx<br>
                加工个数:xxx<br>
                扭矩值:xxx<br>
                温度:xxx<br>
                湿度:xxx<br>
              </div>
            </div></el-col>
            <el-col :span="4"style="margin: 50px"><div class="grid-content bg-purple">
              <div style="font-size: 15px;text-align: center;color: black">
                <img src="../assets/123.webp"style="height: 150px;width: 150px"><br>
                设备名称:xxx<br>
                运行状态:xxx<br>
                加工个数:xxx<br>
                扭矩值:xxx<br>
                温度:xxx<br>
                湿度:xxx<br>
              </div>
            </div></el-col>
          </el-row>
        </el-main>
      </el-container>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          tableData: [{
            date: '大号列表，Sketch48以上工艺',
          },
            {
          date: '大号列表，Sketch48以上工艺',
        },
            {
              date: '大号列表，Sketch48以上工艺',
            },
            {
              date: '大号列表，Sketch48以上工艺',
            },
            {
              date: '大号列表，Sketch48以上工艺',
            },
          ],

        }
      }
    }
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  height: 450px;
  width: 250px;
  margin: 50px;
}
</style>