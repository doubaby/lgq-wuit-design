import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
const routes = [
    {
        path: '/',
        component: () => import('../components/navigation/navigation'),
        children: [
            {
                path: '/',
                component: () => import('../components/house/hotHouseList.vue')

            },
            {
                path:'/Message',
                component: () => import('../components/comment/Comment')
            },
            {
                path:'/Board',
                component: () => import('../components/board/board')
            },
            {
                path:'/HouseDetail/:houseId',
                name:'HouseDetail',
                component: () => import('../components/house/HouseDetail')
            },
            {
                path:'/wholeHouse',
                component: () => import('../components/house/wholeHouse')
            },
            {
                path:'/togetherHouse',
                component: () => import('../components/house/togetherHouse')
            },
            {
                path:'/queryHouse',
                name: 'queryHouse',
                component: () => import('../components/house/queryHouse')
            }
        ]

    },
    {
        path:'/login',
        component: () => import('../components/login/login')
    },
    {
        path:'/register',
        component: () => import('../components/login/register')
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
