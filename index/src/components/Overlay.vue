<template>
  <div :class="['overlay',overlayShow]" @click="click"></div>
</template>

<script>
var that;
export default {
  name: 'Overlay',
  props: {
    show: {
      type: Boolean,
      default: false
    }
  },
  data () {
    that = this;
    return {
      overlayShow:[],
    }
  },
  watch: {
    show(e) {
      if(e){
        this.overlayShow = ['block']
        setTimeout(function() {
          that.overlayShow.push('show')
        },0)
      }else{
        this.overlayShow = ['block']
        setTimeout(function() {
          that.overlayShow = []
        },500)
      }
    }
  },
  methods: {
    click() {
      this.$emit('overlayClick');
    }
  }
}
</script>

<style scoped>
  .overlay{
    width:100%;
    height:100vh;
    background-color:rgba(0,0,0,0.5);
    opacity:0;
    position: absolute;
    top:0;
    z-index: 5;
    transition:all .5s;
    display: none;
  }
  .block{
    display: block;
  }
  .show{
    opacity: 1;
  }
</style>
