<template>
  <div class="table-wrapper">
    <TableComponent
      :thead="['Data da Consulta', 'Horário', 'Paciente', 'Agendada em', 'Ação']"
      :tbody="listAppointments"
      title="Consultas Agendadas"
      :routeLink="'/main/doctor'"
      @finish-item="handleFinish"
    ></TableComponent>
  </div>
</template>

<script setup>
import TableComponent from "@/components/doctor/TableDoctorComponent.vue";
import axios from "axios";
import { ref, onMounted } from "vue";
import {jwtDecode} from "jwt-decode";


const token = localStorage.getItem('token')
const decoded = jwtDecode(token);

const urlBase = process.env.VUE_APP_URL_BASE;
const endpoint = "appointment?doctor=";
const doctorId = decoded.userId;

const listAppointments = ref([]);
const originalAppointments = ref([]);

onMounted(async () => {
 
  try {
    const response = await axios.get(`${urlBase}/${endpoint}${doctorId}`);

    const allAppointments = response.data;
    const filtered = allAppointments.filter(item => item.appointmentCompleted === false);
    originalAppointments.value = filtered;

    const formatDateForVisualization = (isoDateString) => {
      const [year, month, day] = isoDateString.split("T")[0].split("-");
      return `${day}/${month}/${year}`;
    };

    const formatTimeForVisualization = (isoTimeString) => {
      const [hour, minutes] = isoTimeString.split("T")[0].split(":");
      return `${hour}:${minutes}`;
    };

    listAppointments.value = filtered.map(item => {
      return [
      formatDateForVisualization(item.appointmentDateTime.date),
      formatTimeForVisualization(item.appointmentDateTime.time),
        item.patient,
        formatDateForVisualization(item.requestAt),
        'Finalizar'
      ];
    });

  } catch (error) {
    alert("Erro ao buscar horários:", error);
  }
});

async function handleFinish(index) {
  const item = originalAppointments.value[index];
  const appointmentId = item.appointmentId;

  if (confirm("Deseja realmente finalizar esta consulta?")) {
    try {
      await axios.patch(`${urlBase}/appointment/${appointmentId}`);
      alert("Consulta finalizada com sucesso!");

      listAppointments.value.splice(index, 1);
      originalAppointments.value.splice(index, 1);
    } catch (error) {
      alert("Erro ao finalizar consulta.");
    }
  }
}

</script> 
<style scoped>
.table-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; 
  box-sizing: border-box;
}
</style>
