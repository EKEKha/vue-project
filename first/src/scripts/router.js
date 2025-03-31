import Home from "@/components/pages/Home.vue";
import Login from "@/components/pages/Login.vue";
import Cart from "@/components/pages/cart.vue";
import {createRouter, createWebHistory} from "vue-router";

const routes = [

    {path:'/', component: Home},
    {path:'/login', component: Login},
    {path:'/cart', component: Cart},
]

const router = createRouter({
    history : createWebHistory(),
    routes: routes
})

export default router;

