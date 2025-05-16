<template>
  <div class="container py-5">
    <div class="table-container p-4">
      <h2 class="mb-4">
        <i class="bi bi-list-check"></i> {{ title }}
      </h2>

      <table class="table table-hover">
        <thead>
          <tr>
            <th v-for="(header, index) in thead" :key="index">{{ header }}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(row, rowIndex) in tbody" :key="rowIndex">
            <td v-for="(cell, cellIndex) in row" :key="cellIndex">
              <span
            v-if="cell === 'Editar'"
            @click="$emit('edit-item', rowIndex)"
            style="cursor: pointer;"
          >
            Editar
          </span>
          <span v-else-if="cell === 'Excluir'">Excluir</span>
          <span v-else>{{ cell }}</span>
            </td>
          </tr>
        </tbody>
      </table>

      <BackButtonComponent :routeLink="routeLink"/>



    </div>
  </div>
</template>

<script setup>
import BackButtonComponent from '../BackButtonComponent.vue';

const props = defineProps({
  title: {
    type: String,
    default: 'Lista'
  },
  thead: {
    type: Array,
    required: true 
  },
  tbody: {
    type: Array,
    required: true 
  },
  routeLink: {
    type: String,
    default: '/'
  }
});

const routeLink = props.routeLink
defineEmits(['edit-item']);
</script>

<style scoped>
.container{
  padding: 50px;
  background-color: white;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  display: flex;
  flex-direction: column;
  justify-content: center;

}

.table-container{
  border: 1px solid black;
  display: flex;
  flex-direction: column;

}
table{
  border: 1px solid rgb(0, 0, 0);
}

a{
  background-color: var(--secondary);
  border: none;
}

thead th{
  background-color: var(--primary);
  color: white;
}
</style>