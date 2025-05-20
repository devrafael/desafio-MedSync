<template>
  <header class="header py-3 mb-4">
    <div class="container">
      <div class="d-flex justify-content-between align-items-center flex-wrap">
        <h1 class="h4 m-0 text-white d-flex align-items-center gap-2">
          <i class="bi bi-person"></i> Painel do Paciente
        </h1>
        <button class="btn btn-light d-flex align-items-center gap-2 mt-2 mt-md-0" @click="logout">
          <i class="bi bi-box-arrow-left"></i> Sair
        </button>
      </div>
    </div>
  </header>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const logout = () => {
  localStorage.removeItem('token');
  router.push('/login');
};
</script>

<style scoped>
.header {
  background-color: var(--primary);
}

.btn-light {
  background-color: var(--background);
  border-color: var(--background);
  color: var(--primary);
}
.btn-light:hover {
  background-color: var(--primary);
  color: var(--background);
}
</style>
