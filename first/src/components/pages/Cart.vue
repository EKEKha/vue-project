<template>
  <div class="cart">
    <div class="container">
      <ul>
        <li v-for="(i,idx) in state.items" :key="idx">
          <img :src="i.imgPath"/>
          <span class="name">{{i.name}}</span>
          <span class="price">{{i.price}}</span>
        </li>
      </ul>
    </div>
  </div>
</template>


<script>
import axios from "axios";
import {reactive} from "vue";

export default{
  setup(){

    const state = reactive({
      items:[]
    })

    axios.get("/api/cart/items").then(({data}) => {
       console.log(data);
       state.items = data;
    })


    return {state};
  }
}

</script>

<style scoped>

.form-signin {
  max-width: 330px;
  padding: 1rem;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>