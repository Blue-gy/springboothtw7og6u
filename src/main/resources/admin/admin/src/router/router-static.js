import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import yuangong from '@/views/modules/yuangong/list'
    import discussshebeizulin from '@/views/modules/discussshebeizulin/list'
    import discussbisai from '@/views/modules/discussbisai/list'
    import shebeiweixiu from '@/views/modules/shebeiweixiu/list'
    import changdiweixiu from '@/views/modules/changdiweixiu/list'
    import changdiweixiujilu from '@/views/modules/changdiweixiujilu/list'
    import baominghuodong from '@/views/modules/baominghuodong/list'
    import shebeizulin from '@/views/modules/shebeizulin/list'
    import kechengyuyue from '@/views/modules/kechengyuyue/list'
    import news from '@/views/modules/news/list'
    import bisai from '@/views/modules/bisai/list'
    import huiyuan from '@/views/modules/huiyuan/list'
    import zulinjilu from '@/views/modules/zulinjilu/list'
    import huodongxinxi from '@/views/modules/huodongxinxi/list'
    import changdixinxi from '@/views/modules/changdixinxi/list'
    import discusshuodongxinxi from '@/views/modules/discusshuodongxinxi/list'
    import discusschangdixinxi from '@/views/modules/discusschangdixinxi/list'
    import discusskechengxinxi from '@/views/modules/discusskechengxinxi/list'
    import kechengxinxi from '@/views/modules/kechengxinxi/list'
    import zulinguihai from '@/views/modules/zulinguihai/list'
    import caiwuqingkuang from '@/views/modules/caiwuqingkuang/list'
    import shebeiweixiujilu from '@/views/modules/shebeiweixiujilu/list'
    import messages from '@/views/modules/messages/list'
    import bisaibaoming from '@/views/modules/bisaibaoming/list'
    import config from '@/views/modules/config/list'
    import changdiyuyue from '@/views/modules/changdiyuyue/list'
    import newstype from '@/views/modules/newstype/list'
import discussshangpinxinxi from '@/views/modules/discussshangpinxinxi/list'
import shangpinxinxi from '@/views/modules/shangpinxinxi/list'
import orders from '@/views/modules/orders/list'
import shangpinfenlei from '@/views/modules/shangpinfenlei/list'





//2.配置路由   注意：名字
export const routes = [{
    path: '/',
    name: '系统首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '系统首页',
      component: Home,
      meta: {icon:'', title:'center', affix: true}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/yuangong',
        name: '员工',
        component: yuangong
      }
      ,{
	path: '/discussshebeizulin',
        name: '设备租赁评论',
        component: discussshebeizulin
      }
      ,{
	path: '/discussbisai',
        name: '比赛评论',
        component: discussbisai
      }
      ,{
	path: '/shebeiweixiu',
        name: '设备维修',
        component: shebeiweixiu
      }
      ,{
	path: '/changdiweixiu',
        name: '场地维修',
        component: changdiweixiu
      }
      ,{
	path: '/changdiweixiujilu',
        name: '场地维修记录',
        component: changdiweixiujilu
      }
      ,{
	path: '/baominghuodong',
        name: '报名活动',
        component: baominghuodong
      }

        ,{
            path: '/shangpinfenlei',
            name: '商品分类',
            component: shangpinfenlei
        }

        ,{
            path: '/discussshangpinxinxi',
            name: '商品信息评论',
            component: discussshangpinxinxi
        }
        ,{
            path: '/shangpinxinxi',
            name: '商品信息',
            component: shangpinxinxi
        }
        ,{
            path: '/orders/:status',
            name: '订单管理',
            component: orders
        }



        ,{
	path: '/shebeizulin',
        name: '设备租赁',
        component: shebeizulin
      }
      ,{
	path: '/kechengyuyue',
        name: '课程预约',
        component: kechengyuyue
      }
      ,{
	path: '/news',
        name: '公告信息',
        component: news
      }
      ,{
	path: '/bisai',
        name: '比赛',
        component: bisai
      }
      ,{
	path: '/huiyuan',
        name: '用户',
        component: huiyuan
      }
      ,{
	path: '/zulinjilu',
        name: '租赁记录',
        component: zulinjilu
      }
      ,{
	path: '/huodongxinxi',
        name: '活动信息',
        component: huodongxinxi
      }
      ,{
	path: '/changdixinxi',
        name: '场地信息',
        component: changdixinxi
      }
      ,{
	path: '/discusshuodongxinxi',
        name: '活动信息评论',
        component: discusshuodongxinxi
      }
      ,{
	path: '/discusschangdixinxi',
        name: '场地信息评论',
        component: discusschangdixinxi
      }
      ,{
	path: '/discusskechengxinxi',
        name: '课程信息评论',
        component: discusskechengxinxi
      }
      ,{
	path: '/kechengxinxi',
        name: '课程信息',
        component: kechengxinxi
      }
      ,{
	path: '/zulinguihai',
        name: '租赁归还',
        component: zulinguihai
      }
      ,{
	path: '/caiwuqingkuang',
        name: '财务情况',
        component: caiwuqingkuang
      }
      ,{
	path: '/shebeiweixiujilu',
        name: '设备维修记录',
        component: shebeiweixiujilu
      }
      ,{
	path: '/messages',
        name: '在线留言',
        component: messages
      }
      ,{
	path: '/bisaibaoming',
        name: '比赛报名',
        component: bisaibaoming
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
      ,{
	path: '/changdiyuyue',
        name: '场地预约',
        component: changdiyuyue
      }
      ,{
	path: '/newstype',
        name: '公告信息分类',
        component: newstype
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default router;
