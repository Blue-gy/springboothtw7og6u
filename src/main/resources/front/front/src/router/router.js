import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import huiyuanList from '../pages/huiyuan/list'
import huiyuanDetail from '../pages/huiyuan/detail'
import huiyuanAdd from '../pages/huiyuan/add'
import yuangongList from '../pages/yuangong/list'
import yuangongDetail from '../pages/yuangong/detail'
import yuangongAdd from '../pages/yuangong/add'
import changdixinxiList from '../pages/changdixinxi/list'
import changdixinxiDetail from '../pages/changdixinxi/detail'
import changdixinxiAdd from '../pages/changdixinxi/add'
import kechengxinxiList from '../pages/kechengxinxi/list'
import kechengxinxiDetail from '../pages/kechengxinxi/detail'
import kechengxinxiAdd from '../pages/kechengxinxi/add'
import shebeizulinList from '../pages/shebeizulin/list'
import shebeizulinDetail from '../pages/shebeizulin/detail'
import shebeizulinAdd from '../pages/shebeizulin/add'
import huodongxinxiList from '../pages/huodongxinxi/list'
import huodongxinxiDetail from '../pages/huodongxinxi/detail'
import huodongxinxiAdd from '../pages/huodongxinxi/add'
import bisaiList from '../pages/bisai/list'
import bisaiDetail from '../pages/bisai/detail'
import bisaiAdd from '../pages/bisai/add'
import changdiyuyueList from '../pages/changdiyuyue/list'
import changdiyuyueDetail from '../pages/changdiyuyue/detail'
import changdiyuyueAdd from '../pages/changdiyuyue/add'
import kechengyuyueList from '../pages/kechengyuyue/list'
import kechengyuyueDetail from '../pages/kechengyuyue/detail'
import kechengyuyueAdd from '../pages/kechengyuyue/add'
import zulinjiluList from '../pages/zulinjilu/list'
import zulinjiluDetail from '../pages/zulinjilu/detail'
import zulinjiluAdd from '../pages/zulinjilu/add'
import baominghuodongList from '../pages/baominghuodong/list'
import baominghuodongDetail from '../pages/baominghuodong/detail'
import baominghuodongAdd from '../pages/baominghuodong/add'
import bisaibaomingList from '../pages/bisaibaoming/list'
import bisaibaomingDetail from '../pages/bisaibaoming/detail'
import bisaibaomingAdd from '../pages/bisaibaoming/add'
import zulinguihaiList from '../pages/zulinguihai/list'
import zulinguihaiDetail from '../pages/zulinguihai/detail'
import zulinguihaiAdd from '../pages/zulinguihai/add'
import changdiweixiuList from '../pages/changdiweixiu/list'
import changdiweixiuDetail from '../pages/changdiweixiu/detail'
import changdiweixiuAdd from '../pages/changdiweixiu/add'
import shebeiweixiuList from '../pages/shebeiweixiu/list'
import shebeiweixiuDetail from '../pages/shebeiweixiu/detail'
import shebeiweixiuAdd from '../pages/shebeiweixiu/add'
import changdiweixiujiluList from '../pages/changdiweixiujilu/list'
import changdiweixiujiluDetail from '../pages/changdiweixiujilu/detail'
import changdiweixiujiluAdd from '../pages/changdiweixiujilu/add'
import shebeiweixiujiluList from '../pages/shebeiweixiujilu/list'
import shebeiweixiujiluDetail from '../pages/shebeiweixiujilu/detail'
import shebeiweixiujiluAdd from '../pages/shebeiweixiujilu/add'
import caiwuqingkuangList from '../pages/caiwuqingkuang/list'
import caiwuqingkuangDetail from '../pages/caiwuqingkuang/detail'
import caiwuqingkuangAdd from '../pages/caiwuqingkuang/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import discusschangdixinxiList from '../pages/discusschangdixinxi/list'
import discusschangdixinxiDetail from '../pages/discusschangdixinxi/detail'
import discusschangdixinxiAdd from '../pages/discusschangdixinxi/add'
import discusskechengxinxiList from '../pages/discusskechengxinxi/list'
import discusskechengxinxiDetail from '../pages/discusskechengxinxi/detail'
import discusskechengxinxiAdd from '../pages/discusskechengxinxi/add'
import discussshebeizulinList from '../pages/discussshebeizulin/list'
import discussshebeizulinDetail from '../pages/discussshebeizulin/detail'
import discussshebeizulinAdd from '../pages/discussshebeizulin/add'
import discusshuodongxinxiList from '../pages/discusshuodongxinxi/list'
import discusshuodongxinxiDetail from '../pages/discusshuodongxinxi/detail'
import discusshuodongxinxiAdd from '../pages/discusshuodongxinxi/add'
import discussbisaiList from '../pages/discussbisai/list'
import discussbisaiDetail from '../pages/discussbisai/detail'
import discussbisaiAdd from '../pages/discussbisai/add'

import AddrList from '../pages/shop-address/list'
import AddrAdd from '../pages/shop-address/addOrUpdate'
import Order from '../pages/shop-order/list'
import OrderConfirm from '../pages/shop-order/confirm'
import Cart from '../pages/shop-cart/list'
import shangpinfenleiList from '../pages/shangpinfenlei/list'
import shangpinfenleiDetail from '../pages/shangpinfenlei/detail'
import shangpinfenleiAdd from '../pages/shangpinfenlei/add'
import shangpinxinxiList from '../pages/shangpinxinxi/list'
import shangpinxinxiDetail from '../pages/shangpinxinxi/detail'
import shangpinxinxiAdd from '../pages/shangpinxinxi/add'
import discussshangpinxinxiList from '../pages/discussshangpinxinxi/list'
import discussshangpinxinxiDetail from '../pages/discussshangpinxinxi/detail'
import discussshangpinxinxiAdd from '../pages/discussshangpinxinxi/add'



const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	scrollBehavior(to, from, savedPosition) {
		return { x: 0, y: 0 };
	},
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},

				{
					path: 'shangpinfenlei',
					component: shangpinfenleiList
				},
				{
					path: 'shangpinfenleiDetail',
					component: shangpinfenleiDetail
				},
				{
					path: 'shangpinfenleiAdd',
					component: shangpinfenleiAdd
				},
				{
					path: 'shangpinxinxi',
					component: shangpinxinxiList
				},
				{
					path: 'shangpinxinxiDetail',
					component: shangpinxinxiDetail
				},
				{
					path: 'shangpinxinxiAdd',
					component: shangpinxinxiAdd
				},
				{
					path: 'discussshangpinxinxi',
					component: discussshangpinxinxiList
				},
				{
					path: 'discussshangpinxinxiDetail',
					component: discussshangpinxinxiDetail
				},
				{
					path: 'discussshangpinxinxiAdd',
					component: discussshangpinxinxiAdd
				},


				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'huiyuan',
					component: huiyuanList
				},
				{
					path: 'huiyuanDetail',
					component: huiyuanDetail
				},
				{
					path: 'huiyuanAdd',
					component: huiyuanAdd
				},
				{
					path: 'yuangong',
					component: yuangongList
				},
				{
					path: 'yuangongDetail',
					component: yuangongDetail
				},
				{
					path: 'yuangongAdd',
					component: yuangongAdd
				},
				{
					path: 'changdixinxi',
					component: changdixinxiList
				},
				{
					path: 'changdixinxiDetail',
					component: changdixinxiDetail
				},
				{
					path: 'changdixinxiAdd',
					component: changdixinxiAdd
				},
				{
					path: 'kechengxinxi',
					component: kechengxinxiList
				},
				{
					path: 'kechengxinxiDetail',
					component: kechengxinxiDetail
				},
				{
					path: 'kechengxinxiAdd',
					component: kechengxinxiAdd
				},
				{
					path: 'shebeizulin',
					component: shebeizulinList
				},
				{
					path: 'shebeizulinDetail',
					component: shebeizulinDetail
				},
				{
					path: 'shebeizulinAdd',
					component: shebeizulinAdd
				},
				{
					path: 'huodongxinxi',
					component: huodongxinxiList
				},
				{
					path: 'huodongxinxiDetail',
					component: huodongxinxiDetail
				},
				{
					path: 'huodongxinxiAdd',
					component: huodongxinxiAdd
				},
				{
					path: 'bisai',
					component: bisaiList
				},
				{
					path: 'bisaiDetail',
					component: bisaiDetail
				},
				{
					path: 'bisaiAdd',
					component: bisaiAdd
				},
				{
					path: 'changdiyuyue',
					component: changdiyuyueList
				},
				{
					path: 'changdiyuyueDetail',
					component: changdiyuyueDetail
				},
				{
					path: 'changdiyuyueAdd',
					component: changdiyuyueAdd
				},
				{
					path: 'kechengyuyue',
					component: kechengyuyueList
				},
				{
					path: 'kechengyuyueDetail',
					component: kechengyuyueDetail
				},
				{
					path: 'kechengyuyueAdd',
					component: kechengyuyueAdd
				},
				{
					path: 'zulinjilu',
					component: zulinjiluList
				},
				{
					path: 'zulinjiluDetail',
					component: zulinjiluDetail
				},
				{
					path: 'zulinjiluAdd',
					component: zulinjiluAdd
				},
				{
					path: 'baominghuodong',
					component: baominghuodongList
				},
				{
					path: 'baominghuodongDetail',
					component: baominghuodongDetail
				},
				{
					path: 'baominghuodongAdd',
					component: baominghuodongAdd
				},
				{
					path: 'bisaibaoming',
					component: bisaibaomingList
				},
				{
					path: 'bisaibaomingDetail',
					component: bisaibaomingDetail
				},
				{
					path: 'bisaibaomingAdd',
					component: bisaibaomingAdd
				},
				{
					path: 'zulinguihai',
					component: zulinguihaiList
				},
				{
					path: 'zulinguihaiDetail',
					component: zulinguihaiDetail
				},
				{
					path: 'zulinguihaiAdd',
					component: zulinguihaiAdd
				},
				{
					path: 'changdiweixiu',
					component: changdiweixiuList
				},
				{
					path: 'changdiweixiuDetail',
					component: changdiweixiuDetail
				},
				{
					path: 'changdiweixiuAdd',
					component: changdiweixiuAdd
				},
				{
					path: 'shebeiweixiu',
					component: shebeiweixiuList
				},
				{
					path: 'shebeiweixiuDetail',
					component: shebeiweixiuDetail
				},
				{
					path: 'shebeiweixiuAdd',
					component: shebeiweixiuAdd
				},
				{
					path: 'changdiweixiujilu',
					component: changdiweixiujiluList
				},
				{
					path: 'changdiweixiujiluDetail',
					component: changdiweixiujiluDetail
				},
				{
					path: 'changdiweixiujiluAdd',
					component: changdiweixiujiluAdd
				},
				{
					path: 'shebeiweixiujilu',
					component: shebeiweixiujiluList
				},
				{
					path: 'shebeiweixiujiluDetail',
					component: shebeiweixiujiluDetail
				},
				{
					path: 'shebeiweixiujiluAdd',
					component: shebeiweixiujiluAdd
				},
				{
					path: 'caiwuqingkuang',
					component: caiwuqingkuangList
				},
				{
					path: 'caiwuqingkuangDetail',
					component: caiwuqingkuangDetail
				},
				{
					path: 'caiwuqingkuangAdd',
					component: caiwuqingkuangAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'discusschangdixinxi',
					component: discusschangdixinxiList
				},
				{
					path: 'discusschangdixinxiDetail',
					component: discusschangdixinxiDetail
				},
				{
					path: 'discusschangdixinxiAdd',
					component: discusschangdixinxiAdd
				},
				{
					path: 'discusskechengxinxi',
					component: discusskechengxinxiList
				},
				{
					path: 'discusskechengxinxiDetail',
					component: discusskechengxinxiDetail
				},
				{
					path: 'discusskechengxinxiAdd',
					component: discusskechengxinxiAdd
				},
				{
					path: 'discussshebeizulin',
					component: discussshebeizulinList
				},
				{
					path: 'discussshebeizulinDetail',
					component: discussshebeizulinDetail
				},
				{
					path: 'discussshebeizulinAdd',
					component: discussshebeizulinAdd
				},
				{
					path: 'discusshuodongxinxi',
					component: discusshuodongxinxiList
				},
				{
					path: 'shop-address/list',
					component: AddrList
				},
				{
					path: 'shop-address/addOrUpdate',
					component: AddrAdd
				},
				{
					path: 'shop-order/order',
					component: Order
				},
				{
					path: 'cart',
					component: Cart
				},
				{
					path: 'shop-order/orderConfirm',
					component: OrderConfirm
				},
				{
					path: 'discusshuodongxinxiDetail',
					component: discusshuodongxinxiDetail
				},
				{
					path: 'discusshuodongxinxiAdd',
					component: discusshuodongxinxiAdd
				},
				{
					path: 'discussbisai',
					component: discussbisaiList
				},
				{
					path: 'discussbisaiDetail',
					component: discussbisaiDetail
				},
				{
					path: 'discussbisaiAdd',
					component: discussbisaiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},

	]
})
