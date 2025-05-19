<template>
  <TablePatientComponent
    :thead="['Data da Consulta', 'Horário', 'Doutor', 'Agendada em', 'Status']"
    :tbody="listAppointments"
    title="Minhas Consultas"
    :routeLink="'/main/patient'"
  ></TablePatientComponent>
</template>

<script setup>
import TablePatientComponent from "@/components/patient/TablePatientComponent.vue";
import axios from "axios";
import { ref, onMounted } from "vue";
import {jwtDecode} from "jwt-decode";


const token = localStorage.getItem('token')
const decoded = jwtDecode(token);

const urlBase = process.env.VUE_APP_URL_BASE;
const endpoint = "appointment";
const patientId = decoded.userId
const listAppointments = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get(`${urlBase}/${endpoint}/${patientId}`);
    const formatDateForVisualization = (isoDateString) => {
      const [year, month, day] = isoDateString.split("T")[0].split("-");
      return `${day}/${month}/${year}`;
    };

    const formatTimeForVisualization = (isoTimeString) => {
      const [hour, minutes] = isoTimeString.split("T")[0].split(":");
      return `${hour}:${minutes}`;
    };

    listAppointments.value = response.data.map(item => {
      const formattedAppointmentDate = formatDateForVisualization(item.appointmentDateTime.date);
      const formattedRequestDate = formatDateForVisualization(item.requestAt);
      const formattedRequestTime = formatTimeForVisualization(item.appointmentDateTime.time);
      

      return [
        formattedAppointmentDate,
        formattedRequestTime,
        item.appointmentDateTime.doctor.fullName,
        formattedRequestDate,
        item.appointmentCompleted ? "Finalizada" : "Agendada"
      ];
    });
  } catch (error) {
    alert("Erro ao buscar horários:", error);
  }
});



</script>


