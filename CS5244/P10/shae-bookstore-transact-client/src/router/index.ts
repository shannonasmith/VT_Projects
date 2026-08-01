import {createRouter, createWebHistory} from 'vue-router'
import CategoryView from '@/views/CategoryView.vue'
import CartView from '@/views/CartView.vue'
import CheckoutView from '@/views/CheckoutView.vue'
import ConfirmationView from '@/views/ConfirmationView.vue'
import NotFound from '@/views/NotFound.vue'
import HomeView from "@/views/HomeView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/home",
            name: "home-view",
            component: HomeView,
            alias: ["/", "/index.html", "/home"],
        },
        {
            path: '/category',
            redirect: '/category/STAFF PICKS'
        },
        {
            path: "/:pathMatch(.*)*",
            name: "not-found",
            component: NotFound,
        },
        {
            path: "/category/:name",
            name: "category-view",
            component: CategoryView,
            props: true,
        },
        {
            path: "/cart",
            name: "cart-view",
            component: CartView,
        },
        {
            path: "/checkout",
            name: "checkout-view",
            component: CheckoutView,
            meta: { hideNavbar: true },
        },
        {
            path: "/confirmation",
            name: "confirmation-view",
            component: ConfirmationView,
            meta: { hideNavbar: true },
        },

    ],
})

export default router