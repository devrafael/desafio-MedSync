<template>
  <TableComponent
    :thead="['Data da Consulta', 'Horário', 'Paciente', 'Agendada em', 'Ação']"
    :tbody="listAppointments"
    title="Consultas Agendadas"
    :routeLink="'/main/doctor'"
  ></TableComponent>
</template>

<script setup>
import TableComponent from "@/components/doctor/TableDoctorComponent.vue";
import axios from "axios";
import { ref, onMounted } from "vue";

const urlBase = process.env.VUE_APP_URL_BASE;
const endpoint = "appointment?doctor=";
const doctorName = "dr. rafael almeida";
const listAppointments = ref([]);

onMounted(async () => {
 
  try {
    const response = await axios.get(`${urlBase}/${endpoint}${encodeURIComponent(doctorName)}`);
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
        item.patient,
        formattedRequestDate,
        'Finalizar'
      ];
    });
  } catch (error) {
    alert("Erro ao buscar horários:", error);
  }
});



</script>
