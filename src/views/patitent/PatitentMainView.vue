<template>
  <div>
    <HeaderMainComponent />
    <div class="container">
      <div class="row g-4">
        <CardMainComponent
          class-icon="bi bi-plus-circle-fill text-primary fs-1 mb-3"
          title="Agendar Consulta"
          description="Agende uma nova consulta médica"
          :link="'/new-appointments'"
          link-text="Acessar"
        >
        </CardMainComponent>
        <CardMainComponent
          class-icon="bi bi-card-list text-primary fs-1 mb-3"
          title="Minhas consultas"
          description="Visualize as consultas agendadas e realizadas"
          :link="'/my-appointments'"
          link-text="Acessar"
        >
        </CardMainComponent>
      </div>
    </div>
  </div>
</template>

<script setup>
import HeaderMainComponent from "../../components/patient/HeaderMainPatientComponent.vue";
import CardMainComponent from "../../components/CardMainComponent.vue";

</script>
