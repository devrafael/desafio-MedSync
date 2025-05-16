
<template>
  <form @submit.prevent="submitAppointment">
    <h2>AGENDAR CONSULTA</h2>

    <div class="mb-3">
      <label class="form-label">Médico</label>
      <select class="form-select" v-model="selectedDoctor" @change="fetchSchedule">
        <option value="">Selecione um médico</option>
        <option v-for="doctor in doctors" :key="doctor" :value="doctor">
          {{ doctor }}
        </option>
      </select>
    </div>

    <div class="mb-3" v-if="availableDates.length > 0">
      <label class="form-label">Data</label>
      <select class="form-select" v-model="selectedDate">
        <option value="">Selecione uma data</option>
        <option v-for="date in availableDates" :key="date" :value="date">
          {{ formatDate(date) }}
        </option>
      </select>
    </div>

    <div class="mb-3" v-if="selectedDate">
      <label class="form-label">Horário</label>
      <select class="form-select" v-model="selectedTime">
        <option value="">Selecione um horário</option>
        <option
          v-for="time in getAvailableTimesForDate(selectedDate)"
          :key="time"
          :value="time"
        >
          {{ formatTime(time) }}
        </option>
      </select>
    </div>

    <div class="mb-3">
      <label class="form-label">Paciente</label>
      <input type="text" class="form-control" v-model="patientName" />
    </div>

    <div class="btn-container mt-4">
      <BackButtonComponent :routeLink="'/main/patient'" />
      <button type="submit" class="btn btn-primary ms-2">
        <i class="bi bi-save"></i> Agendar
      </button>
    </div>
  </form>
</template>


<script setup>
import BackButtonComponent from "../../components/BackButtonComponent.vue";
import axios from "axios";
import { ref, computed } from "vue";
import { useRouter } from 'vue-router';

const router = useRouter();


const urlBase = process.env.VUE_APP_URL_BASE;
const appointmentEndpoint = "appointment/new";
const doctors = ref([
  "Dr. Rafael Almeida",
  "Dra. Carla Souza",
  "Dr. João Silva"
]);

const selectedDoctor = ref('');
const selectedDate = ref('');
const selectedTime = ref('');
const patientName = ref("");
const listSchedule = ref([]);

const availableDates = computed(() => {
  const dates = listSchedule.value
    .filter(item => item.aviability) 
    .map(item => item.date);
  return [...new Set(dates)];
});

const getAvailableTimesForDate = (date) => {
  return listSchedule.value
    .filter(item => item.aviability && item.date === date) 
    .map(item => item.time);
};

const formatDate = (isoDate) => {
  const [year, month, day] = isoDate.split("-");
  return `${day}/${month}/${year}`;
};

const formatTime = (time) => {
  return time.slice(0, 5); 
};

const fetchSchedule = async () => {
  if (!selectedDoctor.value) return;

  try {
    const response = await axios.get(`${urlBase}/schedule?doctor=${encodeURIComponent(selectedDoctor.value)}`);
    listSchedule.value = response.data;

    selectedDate.value = '';
    selectedTime.value = '';
  } catch (error) {
    console.error("Erro ao buscar horários:", error);
  }
};

const submitAppointment = async () => {
  if (!selectedDoctor.value || !selectedDate.value || !selectedTime.value) {
    alert("Preencha todos os campos antes de agendar.");
    return;
  }
  try {
    const payload = {
      appointmentDateTimeRequest: {
        date: selectedDate.value,
        time: selectedTime.value,
        doctor: selectedDoctor.value
      },
      patient: patientName.value
    };

    await axios.post(`${urlBase}/${appointmentEndpoint}`, payload);
    alert("Consulta agendada com sucesso!");
    router.push('/main/patient');
  } catch (error) {
    console.error("Erro ao agendar consulta:", error);
    alert("Erro ao agendar consulta!");
  }
};
</script>

<style scoped>
h2 {
  padding-bottom: 20px;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
}

input, select {
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

a, button {
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
  gap: 10px;
}

a:hover {
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
