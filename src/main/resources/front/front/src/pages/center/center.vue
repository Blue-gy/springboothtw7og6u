<template>
  <div class="center-page">
    <!-- 页面标题 -->
    <div class="page-header">
      <span class="page-title">个人中心</span>
    </div>

    <div class="center-layout">
      <!-- 左侧用户信息卡片 -->
      <div class="user-card">
        <div class="avatar-wrap">
          <el-image
            class="avatar-img"
            :src="sessionForm.touxiang ? baseUrl + sessionForm.touxiang : require('@/assets/avator.png')"
            fit="cover">
          </el-image>
        </div>
        <div class="user-name">{{ sessionForm.xingming || sessionForm.yuangongxingming || '未设置昵称' }}</div>
        <div class="user-tag">{{ userTableName === 'huiyuan' ? '普通用户' : '员工' }}</div>
        <div class="info-list">
          <template v-if="userTableName === 'huiyuan'">
            <div class="info-row"><span class="info-label">账号</span><span class="info-val">{{ sessionForm.yonghuzhanghao }}</span></div>
            <div class="info-row"><span class="info-label">性别</span><span class="info-val">{{ sessionForm.xingbie || '未填写' }}</span></div>
            <div class="info-row"><span class="info-label">手机</span><span class="info-val">{{ sessionForm.shouji || '未填写' }}</span></div>
          </template>
          <template v-if="userTableName === 'yuangong'">
            <div class="info-row"><span class="info-label">账号</span><span class="info-val">{{ sessionForm.yuangongzhanghao }}</span></div>
            <div class="info-row"><span class="info-label">性别</span><span class="info-val">{{ sessionForm.xingbie || '未填写' }}</span></div>
            <div class="info-row"><span class="info-label">电话</span><span class="info-val">{{ sessionForm.lianxidianhua || '未填写' }}</span></div>
          </template>
        </div>
      </div>

      <!-- 右侧内容区 -->
      <div class="main-content">
        <el-tabs @tab-click="handleClick" class="center-tabs">
          <el-tab-pane label="个人资料">
            <div class="form-card">
              <el-form ref="sessionForm" :model="sessionForm" :rules="rules" label-width="90px">
                <template v-if="userTableName === 'huiyuan'">
                  <el-form-item label="用户账号" prop="yonghuzhanghao">
                    <el-input v-model="sessionForm.yonghuzhanghao" readonly></el-input>
                  </el-form-item>
                  <el-form-item label="姓名" prop="xingming">
                    <el-input v-model="sessionForm.xingming" placeholder="请输入姓名"></el-input>
                  </el-form-item>
                  <el-form-item label="性别" prop="xingbie">
                    <el-select v-model="sessionForm.xingbie" placeholder="请选择性别" style="width:100%">
                      <el-option v-for="(item, index) in dynamicProp.xingbie" :key="index" :label="item" :value="item"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="手机" prop="shouji">
                    <el-input v-model="sessionForm.shouji" placeholder="请输入手机号"></el-input>
                  </el-form-item>
                  <el-form-item label="头像" prop="touxiang">
                    <file-upload tip="点击上传头像" action="file/upload" :limit="1" :multiple="true"
                      :fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
                      @change="huiyuantouxiangHandleAvatarSuccess"></file-upload>
                  </el-form-item>
                </template>
                <template v-if="userTableName === 'yuangong'">
                  <el-form-item label="员工账号" prop="yuangongzhanghao">
                    <el-input v-model="sessionForm.yuangongzhanghao" readonly></el-input>
                  </el-form-item>
                  <el-form-item label="员工姓名" prop="yuangongxingming">
                    <el-input v-model="sessionForm.yuangongxingming" placeholder="请输入姓名"></el-input>
                  </el-form-item>
                  <el-form-item label="性别" prop="xingbie">
                    <el-select v-model="sessionForm.xingbie" placeholder="请选择性别" style="width:100%">
                      <el-option v-for="(item, index) in dynamicProp.xingbie" :key="index" :label="item" :value="item"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="联系电话" prop="lianxidianhua">
                    <el-input v-model="sessionForm.lianxidianhua" placeholder="请输入联系电话"></el-input>
                  </el-form-item>
                  <el-form-item label="家庭住址" prop="jiatingzhuzhi">
                    <el-input v-model="sessionForm.jiatingzhuzhi" placeholder="请输入家庭住址"></el-input>
                  </el-form-item>
                  <el-form-item label="头像" prop="touxiang">
                    <file-upload tip="点击上传头像" action="file/upload" :limit="1" :multiple="true"
                      :fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
                      @change="yuangongtouxiangHandleAvatarSuccess"></file-upload>
                  </el-form-item>
                </template>
                <el-form-item>
                  <el-button type="primary" class="btn-save" @click="onSubmit('sessionForm')">保存修改</el-button>
                  <el-button class="btn-logout" @click="logout">退出登录</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>

          <el-tab-pane label="修改密码">
            <div class="form-card">
              <el-form ref="passwordForm" :model="passwordForm" :rules="passwordRules" label-width="90px">
                <el-form-item label="原密码" prop="password">
                  <el-input type="password" v-model="passwordForm.password" placeholder="请输入原密码"></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="newpassword">
                  <el-input type="password" v-model="passwordForm.newpassword" placeholder="请输入新密码"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="repassword">
                  <el-input type="password" v-model="passwordForm.repassword" placeholder="请再次输入新密码"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" class="btn-save" @click="updatePassword">确认修改</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>

          <el-tab-pane v-for="(item,index) in menuList" :key="index" v-if="hasBack(item.menu)"
            :label="item.child[0].menu" :name="item.child[0].tableName"></el-tab-pane>
          <el-tab-pane label="我的收藏"></el-tab-pane>
            <router-view></router-view>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>

    <el-dialog title="用户充值" :visible.sync="dialogFormVisibleMoney" width="726px" center>
      <el-form :model="chongzhiForm">
        <el-form-item label="充值金额" label-width="120px">
          <el-input type="number" v-model="chongzhiForm.money" autocomplete="off"
                    placeholder="充值金额"></el-input>
        </el-form-item>
        <el-form-item label-width="120px">
          <el-radio-group v-model="chongzhiForm.radio">
            <el-radio style="margin-bottom: 30px" label="微信支付">
              <el-image
                  style="width: 60px; height: 60px;vertical-align: middle;"
                  :src="require('@/assets/weixin.png')"
                  fit="fill"></el-image>
              <span style="display: inline-block;margin-left: 10px">微信支付</span>
            </el-radio>
            <el-radio label="支付宝支付">
              <el-image
                  style="width: 60px; height: 60px;vertical-align: middle;"
                  :src="require('@/assets/zhifubao.png')"
                  fit="fill"></el-image>
              <span style="display: inline-block;margin-left: 10px">支付宝支付</span>
            </el-radio>
            <el-radio label="中国建设银行支付">
              <el-image
                  style="width: 120px; height: 60px;vertical-align: middle;"
                  :src="require('@/assets/jianshe.png')"
                  fit="fill"></el-image>
            </el-radio>
            <el-radio label="中国农业银行支付">
              <el-image
                  style="width: 126px; height: 60px;vertical-align: middle;"
                  :src="require('@/assets/nongye.png')"
                  fit="fill"></el-image>
            </el-radio>
            <el-radio label="中国银行支付">
              <el-image
                  style="width: 140px; height: 60px;vertical-align: middle;"
                  :src="require('@/assets/zhongguo.png')"
                  fit="fill"></el-image>
            </el-radio>
            <el-radio label="交通银行支付">
              <el-image
                  style="width: 120px; height: 60px;vertical-align: middle;"
                  :src="require('@/assets/jiaotong.png')"
                  fit="fill"></el-image>
            </el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleMoney = false">取 消</el-button>
        <el-button type="primary" @click="chongzhi">确认充值</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import config from '@/config/config'
import menu from '@/config/menu'
import Vue from 'vue'

export default {
  //数据集合
  data() {
    return {
      title: '个人中心',
      baseUrl: config.baseUrl,
      sessionForm: {},
      passwordForm: {},
      chongzhiForm: {
        money: '',
        radio: ''
      },


      passwordRules: {
        password: [
          {
            required: true,
            message: "密码不能为空",
            trigger: "blur"
          }
        ],
        newpassword: [
          {
            required: true,
            message: "新密码不能为空",
            trigger: "blur"
          }
        ],
        repassword: [
          {
            required: true,
            message: "确认密码不能为空",
            trigger: "blur"
          }
        ]
      },
      rules: {},
      menuList: [],
      disabled: false,
      uploadUrl: config.baseUrl + 'file/upload',
      imageUrl: '',
      headers: {Token: localStorage.getItem('frontToken')},
      userTableName: localStorage.getItem('UserTableName'),
      dynamicProp: {},
    }
  },
  created() {
    let menus = menu.list()
    for (let x in menus) {
      if (menus[x].tableName == this.userTableName) {
        this.menuList = menus[x].backMenu
      }
    }
    if ('huiyuan' == this.userTableName) {
      this.$set(this.sessionForm, 'yonghuzhanghao', null);
    }
    if ('huiyuan' == this.userTableName) {
      this.$set(this.sessionForm, 'xingming', null);
    }
    if ('huiyuan' == this.userTableName) {
      this.$set(this.sessionForm, 'mima', null);
    }
    if ('huiyuan' == this.userTableName) {
      this.$set(this.sessionForm, 'xingbie', null);
    }
    if ('huiyuan' == this.userTableName) {
      this.$set(this.sessionForm, 'shouji', null);
    }
    if ('huiyuan' == this.userTableName) {
      this.$set(this.sessionForm, 'touxiang', null);
    }
    if ('yuangong' == this.userTableName) {
      this.$set(this.sessionForm, 'yuangongzhanghao', null);
    }
    if ('yuangong' == this.userTableName) {
      this.$set(this.sessionForm, 'yuangongxingming', null);
    }
    if ('yuangong' == this.userTableName) {
      this.$set(this.sessionForm, 'mima', null);
    }
    if ('yuangong' == this.userTableName) {
      this.$set(this.sessionForm, 'xingbie', null);
    }
    if ('yuangong' == this.userTableName) {
      this.$set(this.sessionForm, 'lianxidianhua', null);
    }
    if ('yuangong' == this.userTableName) {
      this.$set(this.sessionForm, 'jiatingzhuzhi', null);
    }
    if ('yuangong' == this.userTableName) {
      this.$set(this.sessionForm, 'touxiang', null);
    }

    if ('huiyuan' == this.userTableName && this.rules['yonghuzhanghao']) {
      this.rules['yonghuzhanghao'].push({required: true, message: '请输入用户账号', trigger: 'blur'})
    } else if ('huiyuan' == this.userTableName && !this.rules['yonghuzhanghao']) {
      this.$set(this.rules, 'yonghuzhanghao', [{required: true, message: '请输入用户账号', trigger: 'blur'}]);
    }
    if ('huiyuan' == this.userTableName && this.rules['xingming']) {
      this.rules['xingming'].push({required: true, message: '请输入姓名', trigger: 'blur'})
    } else if ('huiyuan' == this.userTableName && !this.rules['xingming']) {
      this.$set(this.rules, 'xingming', [{required: true, message: '请输入姓名', trigger: 'blur'}]);
    }
    if ('huiyuan' == this.userTableName && this.rules['mima']) {
      this.rules['mima'].push({required: true, message: '请输入密码', trigger: 'blur'})
    } else if ('huiyuan' == this.userTableName && !this.rules['mima']) {
      this.$set(this.rules, 'mima', [{required: true, message: '请输入密码', trigger: 'blur'}]);
    }
    if ('huiyuan' == this.userTableName) {
      this.$set(this.rules, 'shouji', [{required: false, validator: this.$validate.isMobile, trigger: 'blur'}]);
    }
    if ('yuangong' == this.userTableName && this.rules['yuangongzhanghao']) {
      this.rules['yuangongzhanghao'].push({required: true, message: '请输入员工账号', trigger: 'blur'})
    } else if ('yuangong' == this.userTableName && !this.rules['yuangongzhanghao']) {
      this.$set(this.rules, 'yuangongzhanghao', [{required: true, message: '请输入员工账号', trigger: 'blur'}]);
    }
    if ('yuangong' == this.userTableName && this.rules['yuangongxingming']) {
      this.rules['yuangongxingming'].push({required: true, message: '请输入员工姓名', trigger: 'blur'})
    } else if ('yuangong' == this.userTableName && !this.rules['yuangongxingming']) {
      this.$set(this.rules, 'yuangongxingming', [{required: true, message: '请输入员工姓名', trigger: 'blur'}]);
    }
    if ('yuangong' == this.userTableName && this.rules['mima']) {
      this.rules['mima'].push({required: true, message: '请输入密码', trigger: 'blur'})
    } else if ('yuangong' == this.userTableName && !this.rules['mima']) {
      this.$set(this.rules, 'mima', [{required: true, message: '请输入密码', trigger: 'blur'}]);
    }
    if ('yuangong' == this.userTableName) {
      this.$set(this.rules, 'lianxidianhua', [{required: false, validator: this.$validate.isMobile, trigger: 'blur'}]);
    }

    this.init();
    this.sessionForm = JSON.parse(localStorage.getItem('sessionForm'))
  },
  //方法集合
  methods: {
    init() {
      if ('huiyuan' == this.userTableName) {
        this.dynamicProp.xingbie = '男,女'.split(',');
      }
      if ('yuangong' == this.userTableName) {
        this.dynamicProp.xingbie = '男,女'.split(',');
      }
    },
    setSession() {
      localStorage.setItem('sessionForm', JSON.stringify(this.sessionForm))
    },

    chongzhi() {
      if (this.chongzhiForm.money == '') {
        this.$message({
          message: '请输入充值金额',
          type: 'error',
          duration: 1500
        });
        return;
      }
      if (this.chongzhiForm.money <= 0) {
        this.$message({
          message: '请输入正确的充值金额',
          type: 'error',
          duration: 1500
        });
        return;
      }
      if (this.chongzhiForm.radio == '') {
        this.$message({
          message: '请选择充值方式',
          type: 'error',
          duration: 1500
        });
        return;
      }
      if (!this.sessionForm.money) {
        this.sessionForm.money = parseFloat(this.chongzhiForm.money)
      } else {
        this.sessionForm.money = parseFloat(this.sessionForm.money) + parseFloat(this.chongzhiForm.money);
      }

      this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
        if (res.data.code == 0) {
          this.setSession()
          this.$message({
            message: '充值成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.dialogFormVisibleMoney = false;
            }
          });
        }
      });
    },


    onSubmit(formName) {
      if (`huiyuan` == this.userTableName && this.sessionForm.touxiang != null) {
        this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl, "g"), "");
      }
      if (`yuangong` == this.userTableName && this.sessionForm.touxiang != null) {
        this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl, "g"), "");
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
            if (res.data.code == 0) {
              this.setSession()
              this.$message({
                message: '更新成功',
                type: 'success',
                duration: 1500
              });
            }
          });
        } else {
          return false;
        }
      });
    },
    huiyuantouxiangHandleAvatarSuccess(fileUrls) {
      this.sessionForm.touxiang = fileUrls;
    },
    yuangongtouxiangHandleAvatarSuccess(fileUrls) {
      this.sessionForm.touxiang = fileUrls;
    },
    handleClick(tab, event) {
      switch (event.target.outerText) {
        case '个人中心':
          tab.$router.push('/index/center');
          break;
        case '修改密码':
          this.passwordForm = {
            password: '',
            newpassword: '',
            repassword: '',
          }
          this.$forceUpdate()
          break;
        case '我的收藏':
          localStorage.setItem('storeupType', 1);
          tab.$router.push('/index/storeup');
          break;

        case '我的订单':
          tab.$router.push('/index/shop-order/order');
          break;
        case '我的地址':
          tab.$router.push('/index/shop-address/list');
          break;


        default:
          tab.$router.push(`/index/${tab.name}?centerType=1`);
      }

      this.title = event.target.outerText;
    },
    async updatePassword() {
      this.$refs["passwordForm"].validate(async valid => {
        if (valid) {
          var password = "";
          if (this.sessionForm.mima) {
            password = this.sessionForm.mima;
          } else if (this.sessionForm.password) {
            password = this.sessionForm.password;
          }
          if (this.passwordForm.password != password) {
            this.$message.error("原密码错误");
            return;
          }
          if (this.passwordForm.newpassword != this.passwordForm.repassword) {
            this.$message.error("两次密码输入不一致");
            return;
          }
          if (this.userTableName == 'huiyuan') {
          }
          this.sessionForm.password = this.passwordForm.newpassword;
          this.sessionForm.mima = this.passwordForm.newpassword;
          this.$http.post(`${this.userTableName}/update`, this.sessionForm).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: "修改密码成功,下次登录系统生效",
                type: "success",
                duration: 1500,
                onClose: () => {
                }
              });
              this.setSession()
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      })
    },
    logout() {
      localStorage.clear();
      Vue.http.headers.common['Token'] = "";
      this.$router.push('/index/home');
      this.activeIndex = '0'
      localStorage.setItem('keyPath', this.activeIndex)
      this.$forceUpdate()
      this.$message({
        message: '登出成功',
        type: 'success',
        duration: 1500,
      });
    },
    hasBack(name) {
      switch (name) {
        case '我的收藏管理':
          return false
          break;
        default:
          return true
      }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.center-page {
  width: 80%;
  margin: 30px auto 60px;
  min-height: 600px;
}

.page-header {
  padding-bottom: 16px;
  margin-bottom: 24px;
  border-bottom: 2px solid #f0f0f0;
}

.page-title {
  font-size: 22px;
  font-weight: 700;
  color: #222;
  letter-spacing: 1px;
}

.center-layout {
  display: flex;
  align-items: flex-start;
  gap: 24px;
}

/* 左侧用户卡片 */
.user-card {
  width: 220px;
  flex-shrink: 0;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0,0,0,.08);
  padding: 28px 20px 24px;
  text-align: center;
}

.avatar-wrap {
  width: 80px;
  height: 80px;
  margin: 0 auto 12px;
  border-radius: 50%;
  overflow: hidden;
  border: 3px solid #e8f5e9;
  box-shadow: 0 2px 8px rgba(0,0,0,.12);
}

.avatar-img {
  width: 80px;
  height: 80px;
  display: block;
}

.user-name {
  font-size: 16px;
  font-weight: 600;
  color: #222;
  margin-bottom: 6px;
}

.user-tag {
  display: inline-block;
  padding: 2px 12px;
  border-radius: 20px;
  background: #e8f5e9;
  color: #2e7d32;
  font-size: 12px;
  margin-bottom: 20px;
}

.info-list {
  text-align: left;
  border-top: 1px solid #f5f5f5;
  padding-top: 16px;
}

.info-row {
  display: flex;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px dashed #f0f0f0;
  font-size: 13px;
}

.info-label {
  color: #999;
  width: 36px;
  flex-shrink: 0;
  margin-right: 8px;
}

.info-val {
  color: #333;
  flex: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

/* 右侧主内容 */
.main-content {
  flex: 1;
  min-width: 0;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0,0,0,.08);
  overflow: hidden;
}

.form-card {
  padding: 32px 40px 24px;
}

/* Tabs 样式 */
.center-tabs ::v-deep .el-tabs__header {
  background: #fafafa;
  border-bottom: 1px solid #eee;
  margin: 0;
  padding: 0 20px;
}

.center-tabs ::v-deep .el-tabs__nav-wrap::after {
  display: none;
}

.center-tabs ::v-deep .el-tabs__item {
  height: 48px;
  line-height: 48px;
  font-size: 14px;
  color: #666;
  padding: 0 18px;
}

.center-tabs ::v-deep .el-tabs__item:hover {
  color: #2e7d32;
}

.center-tabs ::v-deep .el-tabs__item.is-active {
  color: #2e7d32;
  font-weight: 600;
}

.center-tabs ::v-deep .el-tabs__active-bar {
  background-color: #2e7d32;
  height: 3px;
  border-radius: 3px;
}

.center-tabs ::v-deep .el-tabs__content {
  padding: 0;
}

/* 表单样式 */
.form-card .el-form-item ::v-deep .el-form-item__label {
  color: #555;
  font-size: 14px;
  font-weight: 500;
}

.form-card .el-input ::v-deep .el-input__inner {
  border-radius: 8px;
  border: 1px solid #ddd;
  height: 40px;
  font-size: 14px;
  color: #333;
  transition: border-color .2s;
  max-width: 360px;
}

.form-card .el-input ::v-deep .el-input__inner:focus {
  border-color: #4caf50;
}

.form-card .el-select ::v-deep .el-input__inner {
  border-radius: 8px;
  border: 1px solid #ddd;
  height: 40px;
  max-width: 360px;
}

/* 按钮 */
.btn-save {
  background: #4caf50 !important;
  border-color: #4caf50 !important;
  color: #fff !important;
  border-radius: 8px !important;
  padding: 0 28px !important;
  height: 38px !important;
  font-size: 14px !important;
}

.btn-save:hover {
  background: #388e3c !important;
  border-color: #388e3c !important;
}

.btn-logout {
  border-radius: 8px !important;
  padding: 0 28px !important;
  height: 38px !important;
  font-size: 14px !important;
  color: #999 !important;
  border-color: #ddd !important;
}

.btn-logout:hover {
  color: #f44336 !important;
  border-color: #f44336 !important;
}
</style>
