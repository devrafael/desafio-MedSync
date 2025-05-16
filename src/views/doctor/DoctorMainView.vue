<template>
  <div>
    <HeaderMainComponent />
    <div class="container">
      <div class="row g-4">
        <CardMainComponent
          class-icon="bi bi-clipboard2-plus-fill text-primary fs-1 mb-3"
          title="Criar Horários"
          description="Crie os horários das consultas"
          :link="'/schedule'"
          link-text="Acessar"
        >
        </CardMainComponent>
        <CardMainComponent
          class-icon="bi bi-clock-fill text-primary fs-1 mb-3"
          title="Gerenciar Horários das Consultas"
          description="Gerencie os horários das consultas"
          :link="'/listSchedule'"
          link-text="Acessar"
        >
        </CardMainComponent>
        <CardMainComponent
          class-icon="bi bi-clipboard2-pulse-fill text-primary fs-1 mb-3"
          title="Consultas Agendadas"
          description="Visualize as consultas agendadas"
          :link="{ name: 'appointments' }"
          link-text="Acessar"
        >
        </CardMainComponent>
      </div>
    </div>
  </div>
</template>

<script setup>
import HeaderMainComponent from "../../components/doctor/HeaderMainComponent.vue";
import CardMainComponent from "../../components/CardMainComponent.vue";
</script>
