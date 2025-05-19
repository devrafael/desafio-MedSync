<template>
    <header class="header p-3 mb-4">
        <h1><i class="bi bi-person"></i> Painel do Paciente</h1>
        <a class="btn btn-logout" @click="logout">
            <i class="bi bi-box-arrow-left"></i> Sair
        </a>
    </header>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const logout = () => {
  localStorage.removeItem('token')
  router.push('/login');
};
</script>

<style scoped>
.header {
  background-color: var(--primary);
  color: white;
  position: relative;
}

.btn-logout {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  background-color: var(--background);
  border: 1px solid var(--background);
  padding: 5px 15px;
  border-radius: 5px;
  color: var(--primary);
  display: flex;
  align-items: center;
  gap: 5px;
}
.btn-logout:hover {
  background-color: var(--primary);
  color:var(--background)
}
</style>