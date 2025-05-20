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
  border: none;
  color: white;
  background-color: var(--secondary);
  border-radius: 5px;
  min-width: 100px;
  height: 18px;
  font-size: 18px;
  cursor: pointer;
  flex: 1;
  text-decoration: none;
  align-content: center;
  display: flex;
  align-items: center;   
  justify-content: center;
  gap:10px;
  width: 10%;

}
a:hover{
    background-color: var(--primary);
}
</style>
