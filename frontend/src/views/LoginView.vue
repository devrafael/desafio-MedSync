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
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;

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
  width: 90%;
  max-width: 500px;            
  display: flex;
  justify-content: center; 
  flex-direction: column;
  height: 500px;
  border-radius: 10px;

       
}



</style>