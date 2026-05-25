<template>
  <div class="main-containers">
    <div class="body-containers"
         :style='{"minHeight":"100vh","padding":"0","margin":"0","position":"relative","background":"#fff"}'>
      <div class="top-container"
           :style='{"boxShadow":"0 0px 0px rgba(64, 158, 255, .3)","padding":"0 10%","borderColor":"#eee","margin":"0 0 90px","alignItems":"center","color":"#666","textAlign":"right","display":"flex","justifyContent":"flex-end","top":"0","left":"0","background":"#e9e9e9","borderWidth":"0px","width":"100%","fontSize":"14px","position":"relative","borderStyle":"solid","height":"30px","zIndex":"1002"}'>
        <!-- info -->
        <div
            :style='{"margin":"0px 0 0","position":"absolute","top":"60px","float":"left","left":"10%","display":"block"}'>
          <span
              :style='{"padding":"0 0 0 12px","lineHeight":"30px","fontSize":"36px","color":"#666","float":"left","fontWeight":"600"}'>校园场地与设备预约管理系统</span>
        </div>

        <div v-if="false"
             :style='{"margin":"0 10px","fontSize":"inherit","color":"inherit","display":"inline-block"}'></div>

        <el-button v-if="Token" class="btn-shop" @click.native="goMenu('/index/cart')">
          <span class="icon iconfont icon-wuliu8" :style='{"color":"inherit","fontSize":"inherit","display":"none"}'></span>
          购物车
        </el-button>

        <img v-if="headportrait&&Token" :style='{"width":"24px","margin":"0","borderRadius":"50%","height":"24px"}'
             :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
        <div v-if="Token"
             :style='{"padding":"0 6px","fontSize":"inherit","lineHeight":"30px","color":"inherit","display":"inline-block","height":"30px"}'>
          {{ username }}
        </div>
        <div v-if="Token && notAdmin"
             :style='{"cursor":"pointer","padding":"0 12px","color":"inherit","display":"inline-block","fontSize":"inherit","lineHeight":"30px","height":"30px"}'
             @click="goMenu('/index/center')">个人中心
        </div>
        <el-button v-if="!Token" @click="toLogin()"
                   :style='{"border":"0","padding":"0 20px","margin":"0px 0 0","color":"inherit","borderRadius":"2px","background":"none","display":"inline-block","fontSize":"14px","lineHeight":"30px","height":"30px"}'>
          登录/注册
        </el-button>
        <el-button v-if="Token" @click="logout"
                   :style='{"border":"0px solid #666","padding":"0 20px","margin":"0px 0 0","color":"inherit","borderRadius":"2px","background":"none","display":"inline-block","fontSize":"14px","lineHeight":"30px","height":"30px"}'>
          退出
        </el-button>
      </div>


      <div class="menu-preview"
           :style='{"padding":"0","borderColor":"#efefef","margin":"0 auto","background":"none","borderWidth":"0 0 0px 0","width":"80%","borderStyle":"solid","height":"auto"}'>
        <el-scrollbar wrap-class="scrollbar-wrapper-horizontal">
          <el-menu class="el-menu-horizontal-demo"
                   :style='{"border":"0","padding":"0","listStyle":"none","margin":"0","alignItems":"center","flexWrap":"wrap","background":"#333","display":"flex","height":"50px"}'
                   :default-active="activeMenu" :unique-opened="true" mode="horizontal" :router="true"
                   @select="handleSelect">
            <div class="userinfo" :style='{"width":"84px","padding":"6px 10px 0","display":"none","height":"auto"}'>
              <el-image
                  :style='{"width":"100%","objectFit":"cover","borderRadius":"20px","display":"block","height":"32px"}'
                  :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')" fit="cover"></el-image>
              <div :style='{"fontSize":"12px","lineHeight":"1.5","color":"#333","textAlign":"center"}'>{{ username }}
              </div>
            </div>
            <el-menu-item class="home" index="/index/home" @click.native="goMenu('/index/home')">
              <span
                  :style='{"padding":"0 10px","margin":"0","color":"inherit","display":"none","width":"18px","lineHeight":"auto","fontSize":"18px","height":"auto"}'
                  class="icon iconfont icon-shouye-zhihui"></span>
              <span
                  :style='{"padding":"0 24px","lineHeight":"auto","fontSize":"18px","color":"inherit","height":"auto"}'>首页</span>
            </el-menu-item>
            <el-menu-item class="item" v-for="(menu, index) in menuList" :index="menu.url" :key="index"
                          @click.native="goMenu(menu.url)">
              <i :style='{"padding":"0 10px","margin":"0","color":"inherit","display":"none","width":"18px","lineHeight":"auto","fontSize":"18px","height":"auto"}'
                 :class="iconArr[index]"></i>
              <span
                  :style='{"padding":"0 24px","lineHeight":"auto","fontSize":"18px","color":"inherit","height":"auto"}'>{{ menu.name }}</span>
            </el-menu-item>
            <el-menu-item class="user" index="/index/center" v-if="Token && notAdmin"
                          @click.native="goMenu('/index/center')">
              <span
                  :style='{"padding":"0 10px","margin":"0","color":"inherit","display":"none","width":"14px","lineHeight":"auto","fontSize":"14px","height":"auto"}'
                  class="icon iconfont icon-shouye-zhihui"></span>
              <span
                  :style='{"padding":"0 10px","lineHeight":"auto","fontSize":"16px","color":"inherit","height":"auto"}'>我的资料</span>
            </el-menu-item>
          </el-menu>
        </el-scrollbar>
      </div>


      <div class="swiper3" :style='{"width":"100%","padding":"0 10%","margin":"10px auto","height":"auto"}'>
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

      <div class="bottom-preview" :style='{"width":"100%","height":"auto"}'>
        <div
            :style='{"minHeight":"150px","padding":"20px 7%","overflow":"hidden","color":"#fff","textAlign":"center","background":"#333","width":"100%","fontSize":"14px","height":"auto"}'>
          <div v-html="bottomContent"></div>
        </div>
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
.menu-preview {
  .el-scrollbar {
    height: 100%;

    & /deep/ .scrollbar-wrapper-vertical {
      overflow-x: hidden;
    }

    & /deep/ .scrollbar-wrapper-horizontal {
      overflow-y: hidden;

      .el-scrollbar__view {
        white-space: nowrap;
      }
    }
  }
}


.menu-preview .el-menu-horizontal-demo .el-menu-item.home {
  cursor: pointer;
  padding: 0;
  margin: 0;
  color: #fff;
  white-space: pre-wrap;
  background: none;
  display: flex;
  font-size: 18px;
  line-height: 50px;
  position: relative;
  list-style: none;
  height: 50px;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.home:hover {
  color: #fff;
  background: #000;
  border-color: #f95927;
  border-width: 0 0 0px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.home.is-active {
  color: #fff;
  background: #ff3d00;
  border-color: #f95927;
  border-width: 0 0 0px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.user {
  cursor: pointer;
  border: 0;
  padding: 0 20px;
  color: #333;
  white-space: nowrap;
  display: none;
  font-size: 16px;
  line-height: 30px;
  background: none;
  align-items: center;
  position: relative;
  list-style: none;
  height: 30px;
  order: 3;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.user:hover {
  color: #f95927;
  border-color: #f95927;
  border-width: 0 0 2px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.user.is-active {
  color: #f95927;
  border-color: #f95927;
  border-width: 0 0 2px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.service {
  cursor: pointer;
  border: 0;
  padding: 0;
  color: #fff;
  white-space: nowrap;
  display: flex;
  font-size: 18px;
  line-height: 50px;
  background: none;
  align-items: center;
  position: relative;
  list-style: none;
  height: 50px;
  order: 4;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.service:hover {
  color: #fff;
  background: #000;
  border-color: #f95927;
  border-width: 0 0 0px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.service.is-active {
  color: #fff;
  background: #ff3d00;
  border-color: #f95927;
  border-width: 0 0 0px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.shop {
  cursor: pointer;
  border: 0;
  padding: 0 20px;
  color: #fff;
  white-space: nowrap;
  display: none;
  font-size: 16px;
  line-height: 30px;
  background: none;
  align-items: center;
  list-style: none;
  height: 30px;
  order: 5;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.shop:hover {
  color: #fff;
  border-color: #f95927;
  border-width: 0 0 0px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.shop.is-active {
  color: #fff;
  border-color: #f95927;
  border-width: 0 0 0px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.back {
  cursor: pointer;
  border: 0;
  padding: 0 20px;
  color: #333;
  white-space: nowrap;
  display: none;
  font-size: 16px;
  line-height: 30px;
  background: none;
  align-items: center;
  position: relative;
  list-style: none;
  height: 30px;
  order: 6;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.back:hover {
  color: #f95927;
  border-color: #f95927;
  border-width: 0 0 2px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.back.is-active {
  color: #f95927;
  border-color: #f95927;
  border-width: 0 0 2px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.item {
  cursor: pointer;
  padding: 0;
  margin: 0;
  color: #fff;
  white-space: nowrap;
  display: flex;
  font-size: 18px;
  border-color: #ddd;
  line-height: 50px;
  background: none;
  justify-content: center;
  border-width: 0 0px 0 0;
  align-items: center;
  position: relative;
  border-style: solid;
  list-style: none;
  text-align: center;
  height: 50px;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.item:hover {
  color: #fff;
  background: #000;
  border-color: #f95927;
  border-width: 0 0 0px;
  border-style: solid;
}

.menu-preview .el-menu-horizontal-demo .el-menu-item.item.is-active {
  color: #fff;
  background: #ff3d00;
  border-color: #f95927;
  border-width: 0 0 0px;
  border-style: solid;
}

.banner-preview {
  .el-carousel /deep/ .el-carousel__indicator button {
    width: 0;
    height: 0;
    display: none;
  }
}

.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left {
  width: 36px;
  font-size: 12px;
  height: 36px;
}

.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left:hover {
  background: red;
}

.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right {
  width: 36px;
  font-size: 12px;
  height: 36px;
}

.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right:hover {
  background: red;
}

.banner-preview .el-carousel /deep/ .el-carousel__indicators {
  padding: 0;
  margin: 0;
  z-index: 2;
  position: absolute;
  list-style: none;
}

.banner-preview .el-carousel /deep/ .el-carousel__indicators li {
  padding: 0;
  margin: 0 4px;
  background: #fff;
  display: inline-block;
  width: 12px;
  opacity: 0.4;
  transition: 0.3s;
  height: 12px;
}

.banner-preview .el-carousel /deep/ .el-carousel__indicators li:hover {
  padding: 0;
  margin: 0 4px;
  background: #fff;
  display: inline-block;
  width: 24px;
  opacity: 0.7;
  height: 12px;
}

.banner-preview .el-carousel /deep/ .el-carousel__indicators li.is-active {
  padding: 0;
  margin: 0 4px;
  background: #fff;
  display: inline-block;
  width: 24px;
  opacity: 1;
  height: 12px;
}

.chat-content {
  padding-bottom: 20px;
  width: 100%;
  margin-bottom: 10px;
  max-height: 300px;
  height: 300px;
  overflow-y: scroll;
  border: 1px solid #eeeeee;
  background: #fff;

  .left-content {
    float: left;
    margin-bottom: 10px;
    padding: 10px;
    max-width: 80%;
  }

  .right-content {
    float: right;
    margin-bottom: 10px;
    padding: 10px;
    max-width: 80%;
  }
}

.clear-float {
  clear: both;
}


.swiper3 .swiper-button-prev:after {
  display: none;
}

.swiper3 .swiper-button-next:after {
  display: none;
}

.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet {
  border-radius: 100%;
  margin: 0 4px;
  background: #000;
  display: inline-block;
  width: 14px;
  opacity: .2;
  height: 14px;
}

.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet:hover {
  background: #e70012;
  opacity: 1;
}

.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet.swiper-pagination-bullet-active {
  background: #e70012;
  opacity: 1;
}

// -------- search --------
.main-containers .search .select /deep/ .el-input__inner {
  border-radius: 20px 0 0 20px;
  padding: 0 30px 0 10px;
  color: #666;
  width: 150px;
  font-size: 14px;
  border-color: #ddd;
  border-width: 1px 0 1px 1px;
  border-style: solid;
  height: 40px;
}

.main-containers .search .input /deep/ .el-input__inner {
  border: 1px solid #ddd;
  border-radius: 0px;
  padding: 0 10px;
  color: #666;
  width: 250px;
  font-size: 14px;
  height: 40px;
}

// -------- search --------

.main-containers .btn-service {
  border: 0;
  padding: 0 8px;
  margin: 0 10px;
  color: #fff;
  background: none;
  display: none;
  width: auto;
  font-size: 14px;
  line-height: 30px;
  height: 30px;
}

.main-containers .btn-service:hover {
}

.main-containers .btn-shop {
  border: 0;
  padding: 0 8px;
  margin: 0 10px;
  color: inherit;
  background: none;
  width: auto;
  font-size: 14px;
  line-height: 30px;
  height: 30px;
}

.main-containers .btn-shop:hover {
}
</style>
