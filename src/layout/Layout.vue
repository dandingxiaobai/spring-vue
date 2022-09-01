<template>
    <div>
    <div>
    <Header></Header>
  </div>
  <div style="display:flex">
    <Aside></Aside>
    <router-view style="flex:1"></router-view>
  </div>  
    </div>
</template>

<script>
import Header from '@/components/Header.vue';
import Aside from '@/components/Aside.vue';
    export default {
        name:"Layout",
      components:{
        Header,
        Aside
      }
    }
</script>

<style lang="scss" scoped>

</style>