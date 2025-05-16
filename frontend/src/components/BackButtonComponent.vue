<template>
    <router-link :to="routeLink" class="btn btn-secondary">
      <i class="bi bi-arrow-left"></i> Voltar
    </router-link>
</template>

<script setup>
defineProps({
  routeLink: {
    type: String,
    default: ""
  }
});
</script>

<style scoped>
.btn-container{
    display: flex;
}

a{
  width: 10%;
  background-color: var(--secondary);
  border: none;
  display: flex;              
  align-items: center;   
  justify-content: center;
  gap: 10px; 

}
a:hover{
    background-color: var(--primary);
}
</style>
