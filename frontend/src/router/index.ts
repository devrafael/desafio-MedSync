import { createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/main/patient",
  },
  {
    path: "/login",
    name: "login",
    component: () =>
      import(/* webpackChunkName: "login" */ "../views/LoginView.vue"),
  },
  {
    path: "/main/doctor",
    name: "main/doctor",
    component: () =>
      import(
        /* webpackChunkName: "mainDoctor" */ "../views/doctor/DoctorMainView.vue"
      ),
  },
  {
    path: "/schedule",
    name: "schedule",
    component: () =>
      import(
        /* webpackChunkName: "schedule" */ "../views/doctor/RegisterDateTimeView.vue"
      ),
  },
  {
    path: "/listSchedule",
    name: "listSchedule",
    component: () =>
      import(
        /* webpackChunkName: "listSchedule" */ "../views/doctor/ListDateTimeView.vue"
      ),
  },
  {
    path: "/appointments",
    name: "appointments",
    component: () =>
      import(
        /* webpackChunkName: "appointments" */ "../views/doctor/ScheduledAppointmentsView.vue"
      ),
  },
  {
    path: "/main/patient",
    name: "/main/patient",
    component: () =>
      import(
        /* webpackChunkName: "/main/patient" */ "../views/patitent/PatitentMainView.vue"
      ),
  },
  {
    path: "/my-appointments",
    name: "my-appointments",
    component: () =>
      import(
        /* webpackChunkName: "my-appointments" */ "../views/patitent/MyAppointmentsView.vue"
      ),
  },
  {
    path: "/new-appointments",
    name: "new-appointments",
    component: () =>
      import(
        /* webpackChunkName: "new-appointments" */ "../views/patitent/NewAppointmentView.vue"
      ),
  },
  {
    path: "/edit/:id",
    name: "edit",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "/edit" */ "../views/doctor/UpdateDateTimeView.vue"
      ),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
