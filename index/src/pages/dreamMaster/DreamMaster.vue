<template>
  <div class="box">
    <div class="header">
      <div>
        <p>欢迎你,管理员</p>
      </div>
    </div>
    <div class="center">
      <div class="nav">
        <div class="title">新闻管理系统</div>
        <img src="/static/images/logo.gif" />
        <ul>
          <li @click="pageIndex=0">新闻管理</li>
          <li @click="pageIndex=1">新闻审核</li>
        </ul>
      </div>
      <div class="content">
        <div v-show="pageIndex==0">
          <div class="table_box">
            <table>
              <thead>
                <tr role="row">
                  <th style="width:70px">id</th>
                  <th style="width:100px">新闻标题</th>
                  <th style="width:300px">新闻内容</th>
                  <th style="width:70px">新闻状态</th>
                  <th style="width:70px">操作</th>
                </tr>
              </thead>
              <tbody>
                <tr role="row">
                  <th>auto</th>
                  <th 
                    contenteditable 
                    ref="titleInput" 
                    @focus="titleClick"
                    @blur="titleBlur">点击输入标题</th>
                  <th 
                    contenteditable 
                    ref="contentInput"
                    @focus="contentClick"
                    @blur="contentBlur">点击输入内容</th>
                  <th>新增</th>
                  <th>
                    <span @click="createNew" style="color:#ff4141;cursor:pointer">发布</span>
                  </th>
                </tr>
                <tr role="row" v-for="(item,index) in newLists" :key="index">
                  <th>{{item.id}}</th>
                  <th ref="title">{{item.title}}</th>
                  <th ref="content">{{item.content}}</th>
                  <th>{{item.type==0?'已发布':'待审核'}}</th>
                  <th>
                    <span 
                      @click="reviseNew(index)" 
                      style="color:#ff4141;cursor:pointer"
                      ref="reviseBtn">修改</span>
                    <span @click="deleteNew(item.id)" style="color:#ff4141;cursor:pointer">删除</span>
                  </th>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div v-show="pageIndex==1">
          <div class="table_box">
            <table>
              <thead>
                <tr role="row">
                  <th style="width:70px">id</th>
                  <th style="width:100px">新闻标题</th>
                  <th style="width:300px">新闻内容</th>
                  <th style="width:70px">新闻状态</th>
                  <th style="width:70px">操作</th>
                </tr>
              </thead>
              <tbody>
                <tr role="row" v-for="(item,index) in newAuditLists">
                  <th>{{item.id}}</th>
                  <th>{{item.title}}</th>
                  <th>{{item.content}}</th>
                  <th>{{item.type==0?'已发布':'待审核'}}</th>
                  <th>
                    <span style="color:#50c750;cursor:pointer;margin-right:10px"
                          @click="auditNew(item.id)">通过</span>
                  </th>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { createNew,auditNew,deleteNew,getNewLists,getAuditNewLists,reviseNew } from '@/assets/javaScript/_axios'
import { Notify } from 'vant';
export default {
  name: 'DreamMaster',
  data () {
    return {
      replyContent:'',
      newLists:[],
      newAuditLists:[],
      pageIndex:0,
    }
  },
  methods: {
    // 加载全部新闻
    loadingNew() {
      getNewLists({
      }).then(res=> {
        this.newLists = res
      })
    },
    // 加载待审核的全部新闻
    loadingAuditNew() {
      getAuditNewLists({
      }).then(res=> {
        this.newAuditLists = res
      })
    },
    // 删除梦
    deleteNew(e) {
      deleteNew({
        id: e
      }).then(res => {
        if( res == 1 ){
          Notify({type:'success',message: '已删除'});
          this.loadingNew();
          this.loadingAuditNew();
        } else {
          Notify({type:'danger',message: '操作失败，请重试'});
        }
      })
    },
    // 修改梦
    reviseNew(index) {
      if( this.$refs.reviseBtn[index].innerHTML == "修改" ){
        this.$refs.reviseBtn[index].innerHTML = "完成"
        this.$refs.title[index].contentEditable = true
        this.$refs.content[index].contentEditable = true
      } else {
        this.$refs.reviseBtn[index].innerHTML = "修改"
        this.$refs.title[index].contentEditable = false
        this.$refs.content[index].contentEditable = false
        reviseNew({
          id: this.newLists[index].id,
          title: this.$refs.title[index].innerHTML,
          content: this.$refs.content[index].innerHTML,
        }).then( res => {
          if( res == 1 ){
            Notify({type:'success',message: '已修改'});
            this.loadingNew();
            this.loadingAuditNew();
          } else {
            Notify({type:'danger',message: '操作失败，请重试'});
          }
        })
      }
    },
    // 审核梦
    auditNew(e) {
      auditNew({
        id: e
      }).then(res => {
        if( res == 1 ){
          Notify({type:'success',message: '已通过'});
          this.loadingNew();
          this.loadingAuditNew();
        } else {
          Notify({type:'danger',message: '操作失败，请重试'});
        }
      })
    },
    // 发布一个梦
    createNew() {
      if( this.$refs.titleInput.innerHTML=='点击输入标题' ){
        Notify({type:'danger',message: '请输入标题'});
      }else if( this.$refs.contentInput.innerHTML=='点击输入内容' ) {
        Notify({type:'danger',message: '请输入内容'});
      }else {
        createNew({
          title: this.$refs.titleInput.innerHTML,
          content: this.$refs.contentInput.innerHTML
        }).then(res => {
          if( res == 1 ){
            Notify({type:'success',message: '已发布'});
            this.loadingNew();
            this.loadingAuditNew();
            this.$refs.titleInput.innerHTML='点击输入标题'
            this.$refs.contentInput.innerHTML='点击输入内容'
          } else {
            Notify({type:'danger',message: '操作失败，请重试'});
          }
        })
      }
    },
    titleClick() {
      this.$refs.titleInput.innerHTML = this.$refs.titleInput.innerHTML=='点击输入标题'?'':this.$refs.titleInput.innerHTML
    },
    contentClick() {
      this.$refs.contentInput.innerHTML = this.$refs.contentInput.innerHTML=='点击输入内容'?'':this.$refs.contentInput.innerHTML
    },
    titleBlur() {
      this.$refs.titleInput.innerHTML = this.$refs.titleInput.innerHTML==''?'点击输入标题':this.$refs.titleInput.innerHTML
    },
    contentBlur() {
      this.$refs.contentInput.innerHTML = this.$refs.contentInput.innerHTML==''?'点击输入内容':this.$refs.contentInput.innerHTML
    }
  },
  mounted() {
    // get_dream_master_msg({
    // }).then(res=> {
    //   this.revealDreamLists = res.data
    // })
    // get_audit_dream({
    // }).then(res=>{
    //   this.auditDreamLists = res.data;
    // })
    // this.username = localStorage.getItem('username');
    this.loadingNew();
    this.loadingAuditNew();
  }
}
</script>

<style lang="css" scoped>
  tr,th,td {
    border: 1px solid #dddddd;
    padding: 8px;
    color: #777;
    text-align: center
  }
  tbody>tr>th {
    font-weight: normal;
    line-height: 20px;
  }
  tbody>tr:nth-child(2n) {
    background-color: #f9f9f9;
  }
  table {
    width: 100%;
  }
  .box {
    height: 100vh;
  }
  .header {
    height: 60px;
    background-color: #34495e;
    border-bottom: 3px solid #faebac;
    position: relative;
  }
  .header>div {
    position: absolute;
    display: inline-block;
    right: 20px;
    color: #fff;
    margin-right: 20px;
  }
  .header>div:hover>.logout {
    display: block;
  }
  .header p {
    display: inline-block;
    line-height: 60px;
    color: #fff;
    position: relative;
  }
  .logout {
    width: 100px;
    height: 50px;
    line-height: 50px;
    border-radius: 4px;
    background-color: #fff;
    text-align: center;
    cursor: pointer;
    display: none;
    color: #000;
    margin: 0 auto;
  }
  .logout:hover {
    color: #696969;
  }
  .center {
    min-height: calc(100vh - 60px);
    background-color: #666;
    display: flex;
  }
  .center .nav {
    flex: 1;
    background-color: #34495e;
  }
  .center .nav .title {
    text-align: center;
    color: #fff;
    font-size: 24px;
    padding: 40px 0 10px;
  }
  .center .nav img {
    width: 100%;
    margin: 30px auto 20px;
    display: block;
  }
  .center .nav li {
    font-size: 18px;
    color: #abb4be;
    white-space: nowrap;
    text-overflow: ellipsis;
    text-align: center;
    height: 50px;
    line-height: 50px;
    cursor: pointer;
  }
  .center .nav li:hover {
    background-color: #21262d;
  }
  .center .content {
    flex: 5;
    background-color: #ecedf0;
    overflow: auto;
    height: calc(100vh - 60px - 80px);
    padding: 40px;
  }
  .table_box {
    padding: 20px;
    border-radius: 6px;
    background-color: #fff;
  }
</style>