<template>
    <div class="home">
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Album example</h1>
        <p class="lead text-body-secondary"></p>
        <p>
          <a href="#" class="btn btn-primary my-2">Main call to action</a>
          <a href="#" class="btn btn-secondary my-2">Secondary action</a>
        </p>
      </div>
    </div>
  </section>

  <div class="album py-5 bg-body-tertiary">
    <div class="container">

      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    
        <div class="col" v-for="(item, idx) in state.items" :key="idx">
            <AppCard :item="item"/>
        </div>
      </div>
    </div> 
  </div>

</div>
</template>

<script>

import axios from 'axios';
import { reactive } from 'vue';
import AppCard from '../AppCard.vue';

export default ({
  components: {AppCard},
    name: "AppHome",
    setup(){

      const state = reactive({
        items:[]

      })


      axios.get("/api/items")
      .then(({data}) => {
        state.items = data;
        
      })

      return {state}
    }
})
</script>

<style scoped></style>
