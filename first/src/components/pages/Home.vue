<template>
    <div class="home">
  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Album example</h1>
        <p class="lead text-body-secondary">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>
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
    
        <div class="col" v-for="i in 12" :key="i">
            <app-card/>
        </div>
      </div>
    </div>
  </div>

</div>
</template>

<script>

import axios from 'axios';
import AppCard from '../AppCard.vue';

export default ({
  components: { AppCard},
    name: "AppHome",
    setup(){
      axios.get("/api/items")
      .then((res) => {
        console.log(res);
        
      })
    }
})
</script>

<style scoped></style>
