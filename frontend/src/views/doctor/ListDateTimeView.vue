<template>
  <TableDoctorComponent
    :thead="['Data', 'Horário', 'Editar', 'Excluir']"
    :tbody="formattedSchedule"
    title="Horários Cadastrados"
    :routeLink="'/main/doctor'"
    @edit-item="handleEdit"
  />
</template>
<script setup>
import TableDoctorComponent from "@/components/doctor/TableDoctorComponent.vue";
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter()

const urlBase = process.env.VUE_APP_URL_BASE;
const endpoint = "schedule?doctor=";
const doctorName = "dr. rafael almeida";

const listSchedule = ref([]);

const formattedSchedule = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get(
      `${urlBase}/${endpoint}${encodeURIComponent(doctorName)}`
    );
    listSchedule.value = response.data;

    const formatDateForVisualization = (isoDateString) => {
      const [year, month, day] = isoDateString.split("T")[0].split("-");
      return `${day}/${month}/${year}`;
    };

    const formatTimeForVisualization = (isoTimeString) => {
      const [hour, minutes] = isoTimeString.split("T")[0].split(":");
      return `${hour}:${minutes}`;
    };

    formattedSchedule.value = listSchedule.value.map((item) => [
      formatDateForVisualization(item.date),
      formatTimeForVisualization(item.time),
      "Editar",
      "Excluir",
    ]);
  } catch (error) {
    console.error("Erro ao buscar horários:", error);
  }
});

function handleEdit(index) {
  const item = listSchedule.value[index];
  if (item.appointmentDateTimeId) {
    router.push(`/edit/${item.appointmentDateTimeId}`);
  }
}
</script>
