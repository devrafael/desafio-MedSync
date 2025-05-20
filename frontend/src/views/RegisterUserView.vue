
<template>
  <form @submit.prevent="handleRegister">
    <h2>CADASTRAR USUÁRIO</h2>

    <div class="mb-3">
      <label class="form-label">Nome completo</label>
      <input type="text" class="form-control" v-model="fullName" required />
    </div>

    <div class="mb-3">
      <label class="form-label">Email</label>
      <input type="email" class="form-control" v-model="email" required />
    </div>

    <div class="mb-3">
      <label class="form-label">Senha</label>
      <input type="password" class="form-control" v-model="password" required />
    </div>

    <div class="mb-3">
      <label class="form-label">Perfil</label>
      <div class="form-check">
        <input
          class="form-check-input"
          type="radio"
          id="doctor"
          value="DOCTOR"
          v-model="role"
        />
        <label class="form-check-label" for="doctor">Médico</label>
      </div>
      <div class="form-check">
        <input
          class="form-check-input"
          type="radio"
          id="patient"
          value="PATIENT"
          v-model="role"
        />
        <label class="form-check-label" for="patient">Paciente</label>
      </div>
    </div>

    <div class="d-flex flex-wrap justify-content-between gap-2 mt-3">
      <router-link to="/login" class="btn btn-save flex-fill text-center">
        <i class="bi bi-box-arrow-in-right"></i> Login
      </router-link>
      <button type="submit" class="btn btn-primary btn-save flex-fill">
        <i class="bi bi-check-circle"></i> Cadastrar
      </button>
    </div>
  </form>
</template>

<script setup>
import axios from "axios";
import { ref } from "vue";

const fullName = ref("");
const email = ref("");
const password = ref("");
const role = ref("");

const urlBase = process.env.VUE_APP_URL_BASE;
const endpoint = "auth/register";

const handleRegister = async () => {
  try {
    const payload = {
      fullName: fullName.value,
      email: email.value,
      password: password.value,
      role: role.value,
    };

    await axios.post(`${urlBase}/${endpoint}`, payload);
    alert("Usuário cadastrado com sucesso!");
  } catch (error) {
    console.error("Erro no cadastro:", error.response?.data || error.message);
    alert("Erro ao cadastrar usuário!");
  }
};
</script>

<style scoped>
h2 {
  padding-bottom: 20px;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
}

input {
  margin-bottom: 20px;
  padding: 10px;
  font-size: 16px;
}

label {
  display: flex;
}

form {
  display: flex;
  flex-direction: column;
  width: 90%;
  max-width: 400px;
  min-width: 250px;
  margin: 50px auto;
  background-color: white;
  padding: 30px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  border-radius: 10px;
}

a,
.btn-save {
  border: none;
  color: white;
  background-color: var(--secondary);
  border-radius: 5px;
  height: 35px;
  font-size: 18px;
  cursor: pointer;
  text-decoration: none;
  align-content: center;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

a:hover {
  background-color: var(--primary);
}
</style>

