<template>
  <form @submit.prevent="handleLogin">
    <input type="text" v-model="email" placeholder="Email" class="form-control"/>
    <input type="password" v-model="password" placeholder="Senha" class="form-control"/>
    <div class="btn-container">
      <button type="submit">Login</button>
      <button type="button">Cadastrar</button>
    </div>
  </form>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import {jwtDecode} from "jwt-decode";
import { useRouter } from "vue-router";

const router = useRouter();

const email = ref("");
const password = ref("");
const urlBase = process.env.VUE_APP_URL_BASE;
const endpoint = "auth/login";

const handleLogin = async () => {
  try {
    const response = await axios.post(`${urlBase}/${endpoint}`, {
      email: email.value,
      password: password.value,
    });
    const token = response.data.accessToken;
    localStorage.setItem("token", token);
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    const decoded = jwtDecode(token);
    
    const roles = decoded.roles || [];

    if (roles.includes("ROLE_DOCTOR")) {
      router.push("/main/doctor");
    } else if (roles.includes("ROLE_PATIENT")) {
      router.push("/main/patient");
    } else {
      alert("Perfil não autorizado.");
    }

  } catch (error) {
    console.error("Erro no login:", error.response?.data || error.message);
    alert("Erro ao efetuar o login");
  }
};

</script>

<style scoped>
form{
  display: flex;
  flex-direction: column;
  width: 80%;
  margin: 0 auto;
  
}

input{
  margin-bottom: 15px;
}

.btn-container {
  display: flex;
  justify-content: space-evenly;
  gap: 10px;
  margin-top: 10px;
  flex-wrap: wrap;
}

button {
  border: none;
  color: white;
  background-color: var(--secondary);
  border-radius: 5px;
  width: 100px;
  height: 35px;
  font-size: 18px;
  cursor: pointer;
  flex: 1;
  text-decoration: none;
  align-content: center;
   display: flex;
   flex-direction: row;
  align-items: center;   
  justify-content: center; 
}

button:hover{
    background-color: var(--primary);
}
</style>
