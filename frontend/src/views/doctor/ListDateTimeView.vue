
<template>
  <div class="table-wrapper">
    <div class="table-container">
      <TableDoctorComponent
        :thead="['Data', 'Horário', 'Editar', 'Excluir']"
        :tbody="formattedSchedule"
        title="Horários Disponíveis"
        :routeLink="'/main/doctor'"
        @edit-item="handleEdit"
        @delete-item="handleDelete"
      />
    </div>
  </div>
</template>

<script setup>
import TableDoctorComponent from "@/components/doctor/TableDoctorComponent.vue";
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { jwtDecode } from "jwt-decode";

const token = localStorage.getItem('token')
const decoded = jwtDecode(token);

const router = useRouter()

const urlBase = process.env.VUE_APP_URL_BASE;
const endpoint = "schedule?doctor=";
const doctorId = decoded.userId;

const listSchedule = ref([]);
const formattedSchedule = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get(
      `${urlBase}/${endpoint}${encodeURIComponent(doctorId)}`
    );

    listSchedule.value = response.data.filter(item => item.aviability === true);

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

async function handleDelete(index) {
  const item = listSchedule.value[index];
  const id = item.appointmentDateTimeId;

  if (confirm('Tem certeza que deseja excluir este horário?')) {
    try {
      await axios.delete(`${urlBase}/schedule/${id}`);
      listSchedule.value.splice(index, 1);
      formattedSchedule.value.splice(index, 1);
      alert('Horário excluído com sucesso!');
    } catch (error) {
      console.error('Erro ao excluir o horário:', error);
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

.table-container {
  width: 100%;
  max-width: 1200px;
}
</style>
