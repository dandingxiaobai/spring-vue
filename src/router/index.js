import Vue from 'vue'
import VueRouter from 'vue-router'

import Layout from "@/layout/Layout";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/home",
    children:[
      {
        path: 'user',
        name: 'User',
        component: ()=>import("@/views/User"),
      },
      {
        path: 'home',
        name: 'Home',
        component: ()=>import("@/views/Home"),
      },
      {
        path: 'equ',
        name: 'Equ',
        component: ()=>import("@/views/equ"),
      }

    ]
  },
  {
    path: '/login',
    name: 'Login',
    component:()=>import("@/views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component:()=>import("@/views/Register")
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
