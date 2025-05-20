import {
  createRouter,
  createWebHashHistory,
  createWebHistory,
  RouteRecordRaw,
} from "vue-router";
import { jwtDecode } from "jwt-decode";
import { NavigationGuardNext, RouteLocationNormalized } from "vue-router";


const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    name: "login",
    component: () =>
      import(/* webpackChunkName: "login" */ "../views/LoginView.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () =>
      import(
        /* webpackChunkName: "register" */ "../views/RegisterUserView.vue"
      ),
  },
  {
    path: "/main/doctor",
    name: "main/doctor",
    component: () =>
      import(
        /* webpackChunkName: "mainDoctor" */ "../views/doctor/DoctorMainView.vue"
      ),
    meta: { requiresAuth: true, role: "ROLE_DOCTOR" },
  },
  {
    path: "/schedule",
    name: "schedule",
    component: () =>
      import(
        /* webpackChunkName: "schedule" */ "../views/doctor/RegisterDateTimeView.vue"
      ),
    meta: { requiresAuth: true, role: "ROLE_DOCTOR" },
  },
  {
    path: "/listSchedule",
    name: "listSchedule",
    component: () =>
      import(
        /* webpackChunkName: "listSchedule" */ "../views/doctor/ListDateTimeView.vue"
      ),
    meta: { requiresAuth: true, role: "ROLE_DOCTOR" },
  },
  {
    path: "/appointments",
    name: "appointments",
    component: () =>
      import(
        /* webpackChunkName: "appointments" */ "../views/doctor/ScheduledAppointmentsView.vue"
      ),
    meta: { requiresAuth: true, role: "ROLE_DOCTOR" },
  },
  {
    path: "/main/patient",
    name: "/main/patient",
    component: () =>
      import(
        /* webpackChunkName: "/main/patient" */ "../views/patitent/PatitentMainView.vue"
      ),
    meta: { requiresAuth: true, role: "ROLE_PATIENT" },
  },
  {
    path: "/edit/:id",
    name: "edit",
    props: true,
    component: () =>
      import(
        /* webpackChunkName: "/edit" */ "../views/doctor/UpdateDateTimeView.vue"
      ),
    meta: { requiresAuth: true, role: "ROLE_DOCTOR" },
  },
  {
    path: "/my-appointments",
    name: "my-appointments",
    component: () =>
      import(
        /* webpackChunkName: "my-appointments" */ "../views/patitent/MyAppointmentsView.vue"
      ),
    meta: { requiresAuth: true, role: "ROLE_PATIENT" },
  },
  {
    path: "/new-appointments",
    name: "new-appointments",
    component: () =>
      import(
        /* webpackChunkName: "new-appointments" */ "../views/patitent/NewAppointmentView.vue"
      ),
    meta: { requiresAuth: true, role: "ROLE_PATIENT" },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach(
  (
    to: RouteLocationNormalized,
    from: RouteLocationNormalized,
    next: NavigationGuardNext
  ) => {
    const token = localStorage.getItem("token");
    const requiresAuth = to.meta.requiresAuth as boolean | undefined;
    const expectedRole = to.meta.role as string | undefined;

    if (!requiresAuth) {
      return next();
    }

    if (!token) {
      if (to.path !== "/login") {
        return next("/login");
      }
      return next();
    }

    try {
      const decoded: any = jwtDecode(token);
      const roles: string[] = decoded.roles || [];

      if (!roles.length || (expectedRole && !roles.includes(expectedRole))) {
        const redirectPath = roles.includes("ROLE_DOCTOR")
          ? "/main/doctor"
          : "/main/patient";

        if (to.path !== redirectPath) {
          return next(redirectPath);
        }
      }
    } catch (e) {
      console.error("Erro ao decodificar token:", e);
      if (to.path !== "/login") {
        return next("/login");
      }
    }

    return next();
  }
);

export default router;
