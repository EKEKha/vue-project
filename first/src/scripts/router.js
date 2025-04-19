import Home from "@/components/pages/Home.vue";
import Login from "@/components/pages/Login.vue";
import Cart from "@/components/pages/Cart.vue";
import Order from "@/components/pages/Order.vue";
import {createRouter, createWebHistory} from "vue-router";

const routes = [

    {path:'/', component: Home},
    {path:'/login', component: Login},
    {path:'/cart', component: Cart},
    {path:'/order', component: Order},
]

const router = createRouter({
    history : createWebHistory(),
    routes: routes
})

export default router;

