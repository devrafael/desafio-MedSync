import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

// Importa CSS do Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'
import "bootstrap-icons/font/bootstrap-icons.css";

// Importa JS do Bootstrap
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

createApp(App).use(router).mount('#app')
