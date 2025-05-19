<template>
  <div class="page-wrapper">
    <div class="login-container">
      <h1>LOGIN</h1>
      <FormLoginComponent/>
    </div>
  </div>
</template>

<script setup>
import FormLoginComponent from '../components/FormLoginComponent.vue'


</script>

<style scoped>
h1{
  margin-bottom: 30px;
}
.page-wrapper {
  min-height: 100vh;            
  display: flex;                
  justify-content: center;      
  align-items: center;          
  background-color: var(--background);   
}

.login-container {
  background-color: white;
  padding: 30px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  width: 60%;
  max-width: 500px;            
  display: flex;
  flex-direction: column;
}



</style>