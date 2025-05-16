<template>
  <form @submit.prevent="submitSchedule">
    <h2>CADASTRAR HORÁRIO</h2>

    <div class="mb-3">
      <label class="form-label">Data</label>
      <input type="date" class="form-control" v-model="date"/>
    </div>

    <div class="mb-3">
      <label class="form-label">Horário</label>
      <input type="time" class="form-control" v-model="time"/>
    </div>

    <div class="btn-container">
      <BackButtonComponent :routeLink="'/main/doctor'"/>
      <button type="submit" class="btn btn-primary btn-save">
        <i class="bi bi-save"></i> Salvar
      </button>
    </div>
  </form>
</template>

<script setup>
import BackButtonComponent from '../../components/BackButtonComponent.vue';
import axios from "axios";
import { ref } from "vue";

const urlBase = process.env.VUE_APP_URL_BASE;
const endpoint = "schedule/new";
const doctorName = "dr. rafael almeida";
const date = ref('');
const time = ref('');

const submitSchedule = async () => {
  try {
    const payload = {
      doctor: doctorName,
      date: date.value,
      time: time.value
    };
    
    const response = await axios.post(`${urlBase}/${endpoint}`, payload);
    console.log('Resposta do servidor:', response.data);
    alert('Horário cadastrado com sucesso!');
  } catch (error) {
    alert('Erro ao cadastrar horário!');
  }
};
</script>


<style scoped>

h2{
    padding-bottom: 20px;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

input {
  margin-bottom: 20px;
  padding: 10px;
  font-size: 16px;
}

label{
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

.btn-container {
  display: flex;
  justify-content: space-evenly;
  gap: 10px;
  margin-top: 10px;
  flex-wrap: wrap;
}

a, .btn-save{
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
  align-items: center;   
  justify-content: center;
  gap:10px;  
}

a:hover{
    background-color: var(--primary);
}


@media (max-width: 480px) {
  .btn-container {
    flex-direction: column;
    align-items: stretch;
  }

  button {
    width: 100%;
  }
}
</style>