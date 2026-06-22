<template>
  <div class="main-containers">
    <div class="body-containers"
         :style='{"minHeight":"100vh","padding":"0","margin":"0","position":"relative","background":"#fff"}'>
      <div class="top-bar">
        <div class="top-bar-inner">
          <div class="site-title">校园场地与设备预约管理系统</div>
          <div class="top-actions">
            <template v-if="Token">
              <img class="user-avatar" v-if="headportrait" :src="baseUrl + headportrait">
              <span class="username-text">{{ username }}</span>
              <span class="top-link" v-if="notAdmin" @click="goMenu('/index/center')">个人中心</span>
              <span class="top-link" @click="logout">退出</span>
            </template>
            <template v-else>
              <span class="top-link login-btn" @click="toLogin()">登录 / 注册</span>
            </template>
          </div>
        </div>
      </div>


      <div class="nav-bar">
        <div class="nav-inner">
          <el-menu class="nav-menu" :default-active="activeMenu" mode="horizontal" :router="true" @select="handleSelect">
            <el-menu-item class="nav-item" index="/index/home" @click.native="goMenu('/index/home')">首页</el-menu-item>
            <el-menu-item class="nav-item" v-for="(menu, index) in menuList" :index="menu.url" :key="index" @click.native="goMenu(menu.url)">{{ menu.name }}</el-menu-item>
          </el-menu>
        </div>
      </div>


      <div v-if="$route.path==='/index/home' || $route.path==='/index'" class="swiper3" :style='{"width":"100%","padding":"0 10%","margin":"10px auto","height":"auto"}'>
        <div class="swiper-container mySwiper3">
          <div class="swiper-wrapper">
            <div class="swiper-slide" v-for="item in carouselList" :key="item.id">
              <div :style='{"width":"100%","height":"auto"}'>
                <el-image @click="carouselClick(item.url)"
                          :style='{"objectFit":"cover","width":"100%","height":"360px"}' :src="baseUrl + item.value"
                          fit="cover"></el-image>
              </div>
            </div>
          </div>
          <!-- Add Pagination -->
          <div class="swiper-pagination" :style='{"width":"100%","left":"0","bottom":"10px","display":"block"}'></div>
          <!-- Add Arrows -->
          <div class="swiper-button-next"
               :style='{"width":"24px","margin":"-12px 20px 0 0","top":"50%","display":"none","height":"24px"}'>
            <span class="icon iconfont icon-jiantou18"
                  :style='{"width":"24px","fontSize":"24px","color":"#fff","height":"24px"}'></span>
          </div>
          <div class="swiper-button-prev"
               :style='{"width":"24px","margin":"-12px 0 0 20px","top":"50%","display":"none","height":"24px"}'>
            <span class="icon iconfont icon-jiantou39"
                  :style='{"width":"24px","fontSize":"24px","color":"#fff","height":"24px"}'></span>
          </div>
        </div>
      </div>
      <router-view id="scrollView"></router-view>

      <div class="site-footer">
        <div v-html="bottomContent" class="footer-content"></div>
        <div class="footer-copy">© 2025 校园场地与设备预约管理系统</div>
      </div>
    </div>

  </div>
</template>

<script>
import Vue from 'vue'
import Swiper from "swiper";
import axios from 'axios'

export default {
  data() {
    return {
      activeIndex: '0',
      roleMenus: [{
        "backMenu": [{
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-circle",
            "buttons": ["新增", "查看", "修改", "删除"],
            "menu": "用户",
            "menuJump": "列表",
            "tableName": "huiyuan"
          }], "menu": "用户管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-explore",
            "buttons": ["新增", "查看", "修改", "删除"],
            "menu": "员工",
            "menuJump": "列表",
            "tableName": "yuangong"
          }], "menu": "员工管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "场地预约", "场地维修"],
            "appFrontIcon": "cuIcon-newshot",
            "buttons": ["新增", "查看", "修改", "删除", "查看评论", "场地预约", "场地维修"],
            "menu": "场地信息",
            "menuJump": "列表",
            "tableName": "changdixinxi"
          }], "menu": "场地信息管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "课程预约"],
            "appFrontIcon": "cuIcon-camera",
            "buttons": ["新增", "查看", "修改", "删除", "查看评论", "课程预约"],
            "menu": "课程信息",
            "menuJump": "列表",
            "tableName": "kechengxinxi"
          }], "menu": "课程信息管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "设备租赁", "设备维修"],
            "appFrontIcon": "cuIcon-taxi",
            "buttons": ["新增", "查看", "修改", "删除", "查看评论", "设备租赁", "设备维修"],
            "menu": "设备租赁",
            "menuJump": "列表",
            "tableName": "shebeizulin"
          }], "menu": "设备租赁管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "报名活动"],
            "appFrontIcon": "cuIcon-camera",
            "buttons": ["新增", "查看", "修改", "删除", "查看评论", "报名活动"],
            "menu": "活动信息",
            "menuJump": "列表",
            "tableName": "huodongxinxi"
          }], "menu": "活动信息管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "比赛报名"],
            "appFrontIcon": "cuIcon-wenzi",
            "buttons": ["新增", "查看", "修改", "删除", "查看评论", "比赛报名"],
            "menu": "比赛",
            "menuJump": "列表",
            "tableName": "bisai"
          }], "menu": "比赛管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-album",
            "buttons": ["新增", "查看", "修改", "删除", "审核"],
            "menu": "场地预约",
            "menuJump": "列表",
            "tableName": "changdiyuyue"
          }], "menu": "场地预约管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-medal",
            "buttons": ["新增", "查看", "修改", "删除", "审核"],
            "menu": "课程预约",
            "menuJump": "列表",
            "tableName": "kechengyuyue"
          }], "menu": "课程预约管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核", "归还"],
            "appFrontIcon": "cuIcon-full",
            "buttons": ["新增", "查看", "修改", "删除", "审核", "归还"],
            "menu": "租赁记录",
            "menuJump": "列表",
            "tableName": "zulinjilu"
          }], "menu": "租赁记录管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-addressbook",
            "buttons": ["新增", "查看", "修改", "删除", "审核"],
            "menu": "报名活动",
            "menuJump": "列表",
            "tableName": "baominghuodong"
          }], "menu": "报名活动管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-present",
            "buttons": ["新增", "查看", "修改", "删除", "审核"],
            "menu": "比赛报名",
            "menuJump": "列表",
            "tableName": "bisaibaoming"
          }], "menu": "比赛报名管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-goodsnew",
            "buttons": ["新增", "查看", "修改", "删除", "审核"],
            "menu": "租赁归还",
            "menuJump": "列表",
            "tableName": "zulinguihai"
          }], "menu": "租赁归还管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "维修记录"],
            "appFrontIcon": "cuIcon-send",
            "buttons": ["新增", "查看", "修改", "删除", "维修记录"],
            "menu": "场地维修",
            "menuJump": "列表",
            "tableName": "changdiweixiu"
          }], "menu": "场地维修管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "维修记录"],
            "appFrontIcon": "cuIcon-medal",
            "buttons": ["新增", "查看", "修改", "删除", "维修记录"],
            "menu": "设备维修",
            "menuJump": "列表",
            "tableName": "shebeiweixiu"
          }], "menu": "设备维修管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-send",
            "buttons": ["新增", "查看", "修改", "删除"],
            "menu": "场地维修记录",
            "menuJump": "列表",
            "tableName": "changdiweixiujilu"
          }], "menu": "场地维修记录管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-newshot",
            "buttons": ["新增", "查看", "修改", "删除"],
            "menu": "设备维修记录",
            "menuJump": "列表",
            "tableName": "shebeiweixiujilu"
          }], "menu": "设备维修记录管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "日用户消费", "日租赁费用", "日活动收入", "日总收入", "首页总数", "首页统计"],
            "appFrontIcon": "cuIcon-copy",
            "buttons": ["新增", "查看", "修改", "删除", "首页总数", "首页统计"],
            "menu": "财务情况",
            "menuJump": "列表",
            "tableName": "caiwuqingkuang"
          }], "menu": "财务情况管理"
        }, {
          "child": [{
            "allButtons": ["查看", "修改", "回复", "删除"],
            "appFrontIcon": "cuIcon-message",
            "buttons": ["查看", "修改", "回复", "删除"],
            "menu": "在线留言",
            "tableName": "messages"
          }], "menu": "在线留言"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-wenzi",
            "buttons": ["新增", "查看", "修改", "删除"],
            "menu": "轮播图管理",
            "tableName": "config"
          }, {
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-news",
            "buttons": ["新增", "查看", "修改", "删除"],
            "menu": "公告信息",
            "tableName": "news"
          }, {
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-news",
            "buttons": ["新增", "查看", "修改", "删除"],
            "menu": "公告信息分类",
            "tableName": "newstype"
          }], "menu": "系统管理"
        }],
        "frontMenu": [{
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "场地预约", "场地维修"],
            "appFrontIcon": "cuIcon-similar",
            "buttons": ["查看", "场地预约"],
            "menu": "场地信息列表",
            "menuJump": "列表",
            "tableName": "changdixinxi"
          }], "menu": "场地信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "课程预约"],
            "appFrontIcon": "cuIcon-flashlightopen",
            "buttons": ["查看", "课程预约"],
            "menu": "课程信息列表",
            "menuJump": "列表",
            "tableName": "kechengxinxi"
          }], "menu": "课程信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "设备租赁", "设备维修"],
            "appFrontIcon": "cuIcon-discover",
            "buttons": ["查看", "设备租赁"],
            "menu": "设备租赁列表",
            "menuJump": "列表",
            "tableName": "shebeizulin"
          }], "menu": "设备租赁模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "报名活动"],
            "appFrontIcon": "cuIcon-shop",
            "buttons": ["查看", "报名活动"],
            "menu": "活动信息列表",
            "menuJump": "列表",
            "tableName": "huodongxinxi"
          }], "menu": "活动信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "比赛报名"],
            "appFrontIcon": "cuIcon-addressbook",
            "buttons": ["查看", "比赛报名"],
            "menu": "比赛列表",
            "menuJump": "列表",
            "tableName": "bisai"
          }], "menu": "比赛模块"
        }],
        "hasBackLogin": "是",
        "hasBackRegister": "否",
        "hasFrontLogin": "否",
        "hasFrontRegister": "否",
        "roleName": "管理员",
        "tableName": "users"
      }, {
        "backMenu": [{
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-album",
            "buttons": ["查看"],
            "menu": "场地预约",
            "menuJump": "列表",
            "tableName": "changdiyuyue"
          }], "menu": "场地预约管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-medal",
            "buttons": ["查看"],
            "menu": "课程预约",
            "menuJump": "列表",
            "tableName": "kechengyuyue"
          }], "menu": "课程预约管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核", "归还"],
            "appFrontIcon": "cuIcon-full",
            "buttons": ["查看", "归还"],
            "menu": "租赁记录",
            "menuJump": "列表",
            "tableName": "zulinjilu"
          }], "menu": "租赁记录管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-addressbook",
            "buttons": ["查看"],
            "menu": "报名活动",
            "menuJump": "列表",
            "tableName": "baominghuodong"
          }], "menu": "报名活动管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-present",
            "buttons": ["查看"],
            "menu": "比赛报名",
            "menuJump": "列表",
            "tableName": "bisaibaoming"
          }], "menu": "比赛报名管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "审核"],
            "appFrontIcon": "cuIcon-goodsnew",
            "buttons": ["查看"],
            "menu": "租赁归还",
            "menuJump": "列表",
            "tableName": "zulinguihai"
          }], "menu": "租赁归还管理"
        }],
        "frontMenu": [{
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "场地预约", "场地维修"],
            "appFrontIcon": "cuIcon-similar",
            "buttons": ["查看", "场地预约"],
            "menu": "场地信息列表",
            "menuJump": "列表",
            "tableName": "changdixinxi"
          }], "menu": "场地信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "课程预约"],
            "appFrontIcon": "cuIcon-flashlightopen",
            "buttons": ["查看", "课程预约"],
            "menu": "课程信息列表",
            "menuJump": "列表",
            "tableName": "kechengxinxi"
          }], "menu": "课程信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "设备租赁", "设备维修"],
            "appFrontIcon": "cuIcon-discover",
            "buttons": ["查看", "设备租赁"],
            "menu": "设备租赁列表",
            "menuJump": "列表",
            "tableName": "shebeizulin"
          }], "menu": "设备租赁模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "报名活动"],
            "appFrontIcon": "cuIcon-shop",
            "buttons": ["查看", "报名活动"],
            "menu": "活动信息列表",
            "menuJump": "列表",
            "tableName": "huodongxinxi"
          }], "menu": "活动信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "比赛报名"],
            "appFrontIcon": "cuIcon-addressbook",
            "buttons": ["查看", "比赛报名"],
            "menu": "比赛列表",
            "menuJump": "列表",
            "tableName": "bisai"
          }], "menu": "比赛模块"
        }],
        "hasBackLogin": "否",
        "hasBackRegister": "否",
        "hasFrontLogin": "是",
        "hasFrontRegister": "是",
        "roleName": "用户",
        "tableName": "huiyuan"
      }, {
        "backMenu": [{
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "维修记录"],
            "appFrontIcon": "cuIcon-send",
            "buttons": ["查看", "维修记录"],
            "menu": "场地维修",
            "menuJump": "列表",
            "tableName": "changdiweixiu"
          }], "menu": "场地维修管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "维修记录"],
            "appFrontIcon": "cuIcon-medal",
            "buttons": ["查看", "维修记录"],
            "menu": "设备维修",
            "menuJump": "列表",
            "tableName": "shebeiweixiu"
          }], "menu": "设备维修管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-send",
            "buttons": ["查看"],
            "menu": "场地维修记录",
            "menuJump": "列表",
            "tableName": "changdiweixiujilu"
          }], "menu": "场地维修记录管理"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除"],
            "appFrontIcon": "cuIcon-newshot",
            "buttons": ["查看"],
            "menu": "设备维修记录",
            "menuJump": "列表",
            "tableName": "shebeiweixiujilu"
          }], "menu": "设备维修记录管理"
        }],
        "frontMenu": [{
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "场地预约", "场地维修"],
            "appFrontIcon": "cuIcon-similar",
            "buttons": ["查看", "场地预约"],
            "menu": "场地信息列表",
            "menuJump": "列表",
            "tableName": "changdixinxi"
          }], "menu": "场地信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "课程预约"],
            "appFrontIcon": "cuIcon-flashlightopen",
            "buttons": ["查看", "课程预约"],
            "menu": "课程信息列表",
            "menuJump": "列表",
            "tableName": "kechengxinxi"
          }], "menu": "课程信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "设备租赁", "设备维修"],
            "appFrontIcon": "cuIcon-discover",
            "buttons": ["查看", "设备租赁"],
            "menu": "设备租赁列表",
            "menuJump": "列表",
            "tableName": "shebeizulin"
          }], "menu": "设备租赁模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "报名活动"],
            "appFrontIcon": "cuIcon-shop",
            "buttons": ["查看", "报名活动"],
            "menu": "活动信息列表",
            "menuJump": "列表",
            "tableName": "huodongxinxi"
          }], "menu": "活动信息模块"
        }, {
          "child": [{
            "allButtons": ["新增", "查看", "修改", "删除", "查看评论", "比赛报名"],
            "appFrontIcon": "cuIcon-addressbook",
            "buttons": ["查看", "比赛报名"],
            "menu": "比赛列表",
            "menuJump": "列表",
            "tableName": "bisai"
          }], "menu": "比赛模块"
        }],
        "hasBackLogin": "是",
        "hasBackRegister": "是",
        "hasFrontLogin": "否",
        "hasFrontRegister": "否",
        "roleName": "员工",
        "tableName": "yuangong"
      }],
      baseUrl: '',
      carouselList: [],
      menuList: [],
      form: {
        ask: '',
        userid: localStorage.getItem('frontUserid')
      },
      headportrait: localStorage.getItem('frontHeadportrait') ? localStorage.getItem('frontHeadportrait') : '',
      Token: localStorage.getItem('frontToken'),
      username: localStorage.getItem('username'),
      notAdmin: localStorage.getItem('frontSessionTable') != '"users"',
      timer: '',
      iconArr: [
        'el-icon-star-off',
        'el-icon-goods',
        'el-icon-warning',
        'el-icon-question',
        'el-icon-info',
        'el-icon-help',
        'el-icon-picture-outline-round',
        'el-icon-camera-solid',
        'el-icon-video-camera-solid',
        'el-icon-video-camera',
        'el-icon-bell',
        'el-icon-s-cooperation',
        'el-icon-s-order',
        'el-icon-s-platform',
        'el-icon-s-operation',
        'el-icon-s-promotion',
        'el-icon-s-release',
        'el-icon-s-ticket',
        'el-icon-s-management',
        'el-icon-s-open',
        'el-icon-s-shop',
        'el-icon-s-marketing',
        'el-icon-s-flag',
        'el-icon-s-comment',
        'el-icon-s-finance',
        'el-icon-s-claim',
        'el-icon-s-opportunity',
        'el-icon-s-data',
        'el-icon-s-check'
      ],
      bottomContent: '',
    }
  },
  created() {
    this.baseUrl = this.$config.baseUrl;
    this.menuList = this.$config.indexNav;
    this.getCarousel();
    if (localStorage.getItem('frontToken') && localStorage.getItem('frontToken') != null) {
      this.getSession()
    }
  },
  mounted() {
    this.activeIndex = localStorage.getItem('keyPath') || '0';


    // banner
    setTimeout(() => {
      new Swiper(".mySwiper3", {
        "navigation": {"nextEl": ".swiper-button-next", "prevEl": ".swiper-button-prev"},
        "pagination": {"el": ".swiper-pagination", "clickable": true},
        "autoplay": {"delay": 2500, "disableOnInteraction": false},
        "effect": "fade"
      })
    }, 500)

  },
  computed: {
    activeMenu() {
      const route = this.$route
      const {
        meta,
        path
      } = route
      // if st path, the sidebar will highlight the path you sete
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
  },
  watch: {
    $route(newValue) {
      let that = this
      let url = window.location.href
      let arr = url.split('#')
      for (let x in this.menuList) {
        if (newValue.path == this.menuList[x].url) {
          this.activeIndex = x
        }
      }
      this.Token = localStorage.getItem('frontToken')
      if (arr[1] != '/index/home') {
        var element = document.getElementById('scrollView');
        var distance = element.offsetTop;
        window.scrollTo(0, distance)
      } else {
        window.scrollTo(0, 0)
      }
    },
    headportrait() {
      this.$forceUpdate()
    },
  },
  methods: {

    async getSession() {
      await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
        if (res.data.code == 0) {
          localStorage.setItem('sessionForm', JSON.stringify(res.data.data))
          localStorage.setItem('frontUserid', res.data.data.id);
          if (res.data.data.vip) {
            localStorage.setItem('vip', res.data.data.vip);
          }
          if (res.data.data.touxiang) {
            this.headportrait = res.data.data.touxiang
            localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
          } else if (res.data.data.headportrait) {
            this.headportrait = res.data.data.headportrait
            localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
          }
        }
      });
    },
    handleSelect(keyPath) {
      if (keyPath) {
        localStorage.setItem('keyPath', keyPath)
      }
    },
    toLogin() {
      this.$router.push('/login');
    },
    logout() {
      localStorage.clear();
      Vue.http.headers.common['Token'] = "";
      this.$router.push('/index/home');
      this.activeIndex = '0'
      localStorage.setItem('keyPath', this.activeIndex)
      this.Token = ''
      this.$forceUpdate()
      this.$message({
        message: '登出成功',
        type: 'success',
        duration: 1000,
      });
    },
    getCarousel() {
      this.$http.get('config/list', {params: {page: 1, limit: 3}}).then(res => {
        if (res.data.code == 0) {
          this.carouselList = res.data.data.list;
        }
      });
    },
    // 轮播图跳转
    carouselClick(url) {
      if (url) {
        if (url.indexOf('https') != -1) {
          window.open(url)
        } else {
          this.$router.push(url)
        }
      }
    },
    goBackend() {
      localStorage.setItem('Token', localStorage.getItem('frontToken'));
      localStorage.setItem('role', localStorage.getItem('frontRole'));
      localStorage.setItem('sessionTable', localStorage.getItem('frontSessionTable'));
      localStorage.setItem('headportrait', localStorage.getItem('frontHeadportrait'));
      localStorage.setItem('userid', localStorage.getItem('frontUserid'));
      window.open(`${this.$config.baseUrl}admin/dist/index.html`, "_blank");
    },
    goMenu(path) {
      this.$router.push(path);
    },
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
/* ===== 顶部信息栏 ===== */
.top-bar {
  width: 100%;
  background: #fff;
  border-bottom: 1px solid #f0f0f0;
}
.top-bar-inner {
  width: 80%;
  margin: 0 auto;
  height: 54px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.site-title {
  font-size: 20px;
  font-weight: 700;
  color: #1a1a1a;
  letter-spacing: 1px;
}
.top-actions {
  display: flex;
  align-items: center;
  gap: 4px;
}
.user-avatar {
  width: 28px;
  height: 28px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 4px;
}
.username-text {
  font-size: 14px;
  color: #555;
  margin-right: 4px;
}
.top-link {
  font-size: 14px;
  color: #555;
  cursor: pointer;
  padding: 0 10px;
  height: 54px;
  line-height: 54px;
  display: inline-block;
  transition: color .2s;
  &:hover { color: #1890ff; }
}
.login-btn {
  color: #1890ff;
  font-weight: 500;
}

/* ===== 导航栏 ===== */
.nav-bar {
  width: 100%;
  background: #fff;
  border-bottom: 2px solid #f0f0f0;
  position: sticky;
  top: 0;
  z-index: 100;
  box-shadow: 0 2px 8px rgba(0,0,0,.06);
}
.nav-inner {
  width: 80%;
  margin: 0 auto;
}
.nav-menu {
  border-bottom: none !important;
  background: transparent !important;
}
.nav-menu ::v-deep .el-menu-item {
  font-size: 15px;
  color: #333 !important;
  height: 50px;
  line-height: 50px;
  border-bottom: 3px solid transparent !important;
  padding: 0 20px;
  transition: all .2s;
}
.nav-menu ::v-deep .el-menu-item:hover {
  background: transparent !important;
  color: #1890ff !important;
}
.nav-menu ::v-deep .el-menu-item.is-active {
  color: #1890ff !important;
  border-bottom-color: #1890ff !important;
  background: transparent !important;
  font-weight: 600;
}

/* ===== 底栏 ===== */
.site-footer {
  background: #1f2329;
  color: #aaa;
  text-align: center;
  padding: 32px 10%;
  margin-top: 60px;
}
.footer-content {
  font-size: 14px;
  line-height: 1.8;
  margin-bottom: 12px;
}
.footer-copy {
  font-size: 12px;
  color: #666;
}
</style>
