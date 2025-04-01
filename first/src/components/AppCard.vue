<template>
    <div class="card shadow-sm">
      <span class="img" :style="{backgroundImage:`url(${item.imgPath})`}" />
            <div class="card-body">
              <p class="card-text">
                <span>{{ item.name }}&nbsp;</span>
                <span class="discount badge bg-danger ">{{ item.discountPer }}%</span>
              </p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-primary" @click="addToCart(item.id)">
                    <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                  </button>
                </div>
                <small class="price text-muted">
                  {{ lib.getNumberFormatted(item.price) }}원
                </small>

                <small class="real text-danger ">
                  {{ lib.getNumberFormatted(item.price - (item.price * item.discountPer/100)) }}%
                </small>

              </div>
            </div>
          </div> 
</template>


<script>

import lib from "@/scripts/lib";
import axios from "axios";
export default {
    name : "AppCard",
    props :{
      item:Object
    },
    setup(){

      const addToCart = (itemId) => {
        axios.post(`/api/cart/items/${itemId}`).then(()=>{

        })
      };


      return {lib, addToCart}
    }
}
</script>

<style scoped>
.card .img{
  width: 100%;
  display: inline-block;
  height: 250px;
  background-size: cover;
  background-position: center;
}

.card .card-body .price{
  text-decoration: line-through;
}


</style>
