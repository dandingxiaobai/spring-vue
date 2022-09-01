<template>
    <div>
<el-menu
 style="width:200px;min-height: 100vh;"
      default-active="user"
      class="el-menu-vertical-demo"
    router
>
  <el-menu-item index="home" >
    <template slot="title">首页</template>
  </el-menu-item>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>系统管理</span>
        </template>
        <el-menu-item index="equ" >
          <template slot="title">设备管理</template>
        </el-menu-item>
        <el-menu-item index="user" >
          <template slot="title">用户管理</template>
        </el-menu-item>
      </el-submenu>

    </el-menu>
    </div>
</template>

<script>

    export default {
        name:"Aside"
    }
</script>

<style lang="scss" scoped>

</style>