<template>
  <div class="scroll"  @touchstart="touchStart($event)" @touchend="touchEnd($event)">
    <pull-refresh v-model="isRefresh" 
                  @refresh="onRefresh">
      <template #pulling="props">
        <div class="refresh_head">下拉刷新</div>
      </template>
      <!-- 释放提示 -->
      <template #loosing>
        <div class="refresh_head">释放刷新</div>
      </template>  

      <!-- 加载提示 -->
      <template #loading>
        <div class="refresh_head">加载中...</div>
      </template>

      <template #success>
        <div class="refresh_head">刷新成功</div>
      </template>
      
      <slot></slot>
      
      <!-- 加载更多 -->
      <div class="loadMore" v-if="ifLoadMore">
        {{loadMoreText}}
      </div>
    </pull-refresh>
  </div>
</template>

<script>
var that;
import { PullRefresh } from 'vant';
export default {
  name: 'Scroll',
  components: {
    PullRefresh,
  },
  data () {
    that = this;
    return {
      startX: 0,
      startY: 0,
      loadMoreText: "上拉加载更多",
      isLoading: false,
      isRefresh:false
    }
  },
  props: {
    showType: {
      type: Number,
      default: 0
    },
    ifLoadMore:{
      typt: Boolean
    }
  },
  methods: {
    onRefresh() {
      // setTimeout(() => {
      //   this.isRefresh = false;
      //   this.count++;
      // }, 1000);
      this.$emit('onRefresh')
    },
    refreshSuccess(e){
      // console.log("1")
      this.isRefresh = e;
    },
    touchStart(e) {
      this.startY = e.targetTouches[0].pageY;
      this.startX = e.targetTouches[0].pageX;  
    },
    scrollToEnd(e) {
      let scrollHeight = this.$el.scrollHeight; 
      let clientHeight = this.$el.clientHeight; 
      let scrollTop = this.$el.scrollTop;  
      if (scrollTop + clientHeight + 10 >= scrollHeight || this.enableLoadMore) {
          this.doLoadMore();
      } 
    },
    touchEnd(e) {
      if (this.isLoading) {
          return;
      }

      let endX = e.changedTouches[0].pageX,
          endY = e.changedTouches[0].pageY,
          dy = this.startY - endY,
          dx = endX - this.startX;
      if(Math.abs(dx) < 2 && Math.abs(dy) < 2) {
          return;
      }
      if (endY < this.startY) {
          this.scrollToEnd(e)
      }
    },
    doLoadMore() {
        this.isLoading = true
        this.loadMoreText = '加载中'
        var i = 0;
        var LoadingText = setInterval(()=>{
          // console.log(this.loadMoreText);
          if(i == 0){
            this.loadMoreText = '加载中.';
            i++;
          }else if(i == 1){
            this.loadMoreText = '加载中..';
            i++;
          }else if(i == 2){
            this.loadMoreText = '加载中...';
            i++;
          }else if(i == 3){
            this.loadMoreText = '加载中';
            i = 0;
          }
        },500);
        setTimeout(()=>{
          clearInterval(LoadingText);
          this.loadDone();
        },3000)
    },
    loadDone() {
        this.isLoading = false;
        this.loadMoreText = '上拉加载更多'
    }
  }
}
</script>

<style lang="css" scoped>
  .scroll{
    
    overflow:auto;
  }
  .scroll >>> .van-pull-refresh__head{
    color:inherit;
  }
  /*加载更多*/
  .loadMore{
    height:1.2rem;
    line-height: 1.2rem;
    text-align: center;
    margin-top: .2rem;
  }

</style>

