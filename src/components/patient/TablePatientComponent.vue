
<template>
  <div class="container py-5">
    <div class="table-container p-4">
      <h2 class="mb-4"><i class="bi bi-list-check"></i> {{ title }}</h2>

     
      <div class="btn-container mb-3">
        <button
        class="btn btn-filter" 
        :class="{ selected: selectedFilter === 'Todas' }"
        @click="clearFilter()">
        Todas
      </button>

        <button 
        class="btn btn-filter" 
        :class="{ selected: selectedFilter === 'Agendada' }"
        @click="filterByStatus('Agendada')">
        Agendadas
      </button>

        <button 
        class="btn btn-filter" 
        :class="{ selected: selectedFilter === 'Finalizada' }"
        @click="filterByStatus('Finalizada')">
        Finalizadas
      </button>

      </div>

      <table class="table table-hover">
        <thead>
          <tr>
            <th v-for="(header, index) in thead" :key="index">{{ header }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(row, rowIndex) in filteredRows" :key="rowIndex">
            <td v-for="(cell, cellIndex) in row" :key="cellIndex">
              {{ cell }}
            </td>
          </tr>
        </tbody>
      </table>

      <BackButtonComponent :routeLink="routeLink" />
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import BackButtonComponent from "../BackButtonComponent.vue";

const props = defineProps({
  title: {
    type: String,
    default: "Lista",
  },
  thead: {
    type: Array,
    required: true,
  },
  tbody: {
    type: Array,
    required: true,
  },
  routeLink: {
    type: String,
    default: "/",
  },
});

const filteredRows = ref([]);
const selectedFilter = ref("Todas");

watch(
  () => props.tbody,
  (newVal) => {
    filteredRows.value = [...newVal]; 
  },
  { immediate: true }
);

function filterByStatus(status) {
  selectedFilter.value = status;
  filteredRows.value = props.tbody.filter(row => row.includes(status));
}

function clearFilter() {
  selectedFilter.value = "Todas";
  filteredRows.value = [...props.tbody];
}
</script>


<style scoped>
.container {
  padding: 50px;
  background-color: white;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 50vh;
}

.btn-container{
  display: flex;
  border: none;
  margin-bottom: 10px;
}

.btn-filter{
  background-color: var(--secondary);
  color: white;
  margin-right: 10px;
}

.btn-filter:hover{
  background-color: var(--primary);
}

.btn-filter.selected {
  background-color: var(--primary); 
  font-weight: bold;
}

.table-container {
  border: 1px solid black;
  display: flex;
  flex-direction: column;
}
table {
  border: 1px solid rgb(0, 0, 0);
}

a {
  background-color: var(--secondary);
  border: none;
}

thead th {
  background-color: var(--primary);
  color: white;
}
</style>
