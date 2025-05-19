<template>
  <div class="col-md-4">
    <div class="card card-menu h-100">
      <div class="card-body text-center">
        <i :class="classIcon"></i>
        <h3>{{ title }}</h3>
        <p>{{ description }}</p>
        <router-link :to="link" class="btn btn-card w-100">
          {{ linkText }} <i class="bi bi-arrow-right"></i>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  classIcon: String,
  title: String,
  description: String,
  link: [String, Object], 
  linkText: String
})
</script>

<style scoped>
.card-menu {
  border: none;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-body {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}
.card-body p {
  flex-grow: 1;
}
.btn-card {
  background-color: var(--secondary);
  color: white;
  font-size: 18px;
}
.btn-menu:hover {
  background-color: #2c62b9;
}
</style>
