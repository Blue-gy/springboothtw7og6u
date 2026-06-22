<template>
<div class="msg-page">
  <!-- 发留言卡片 -->
  <div class="msg-card submit-card">
    <div class="card-title">
      <span class="title-bar"></span>
      在线留言
    </div>
    <el-form :model="form" :rules="rules" ref="form">
      <el-form-item prop="content">
        <el-input
          type="textarea"
          v-model="form.content"
          :rows="5"
          placeholder="请输入您的留言内容..."
          resize="none"
          class="msg-textarea"
        ></el-input>
      </el-form-item>
      <el-form-item label="附图" class="upload-item">
        <file-upload
          tip="点击上传图片（选填）"
          action="file/upload"
          :limit="1"
          :multiple="true"
          :fileUrls="form.cpicture?form.cpicture:''"
          @change="cpictureUploadChange"
        ></file-upload>
      </el-form-item>
      <el-form-item class="btn-row">
        <el-button type="primary" class="btn-submit" @click="submitForm('form')">发表留言</el-button>
        <el-button class="btn-reset" @click="resetForm('form')">清空</el-button>
      </el-form-item>
    </el-form>
  </div>

  <!-- 留言列表 -->
  <div class="msg-list" v-if="infoList.length">
    <div class="list-header">全部留言 <span class="total-count">共 {{ total }} 条</span></div>
    <div class="msg-item" v-for="item in infoList" :key="item.id">
      <div class="item-head">
        <el-avatar :size="40" :src="item.avatarurl ? $config.baseUrl + item.avatarurl : require('@/assets/touxiang.png')"></el-avatar>
        <div class="item-meta">
          <span class="item-username">{{ item.username }}</span>
          <span class="item-time">{{ item.addtime }}</span>
        </div>
      </div>
      <div class="item-content" v-html="item.content"></div>
      <div v-if="item.cpicture" class="item-img">
        <img :src="$config.baseUrl + item.cpicture">
      </div>
      <div class="item-reply" v-if="item.reply">
        <span class="reply-tag">官方回复</span>
        <span v-html="item.reply"></span>
        <div v-if="item.rpicture" class="item-img" style="margin-top:8px">
          <img :src="$config.baseUrl + item.rpicture">
        </div>
      </div>
    </div>
  </div>
  <div class="empty-tip" v-else>暂无留言，快来发表第一条吧~</div>

  <el-pagination
    background
    id="pagination" class="pagination"
    :pager-count="7"
    :page-size="pageSize"
    :hide-on-single-page="false"
    :layout='["total","prev","pager","next"].join()'
    :total="total"
    @current-change="curChange"
    @prev-click="prevClick"
    @next-click="nextClick"
  ></el-pagination>
</div>
</template>

<script>
  export default {
    //数据集合
    data() {
      return {
		  layouts: '',
        form: {
          content: '',
          userid: localStorage.getItem('frontUserid'),
          username: localStorage.getItem('username')
        },
        total: 1,
        pageSize: 20,
		pageSizes: [10,20,30,50],
        totalPage: 1,
        rules: {
          content: [
            { required: true, message: '请输入内容', trigger: 'blur' }
          ]
        },
        infoList: []
      }
    },
    created() {
      this.getInfo(1);
    },
    //方法集合
    methods: {
      getInfo(page) {
        this.$http.get('messages/list', {params: {page, limit: this.pageSize,sort:"addtime", order:"desc"}}).then(res => {
          if (res.data.code == 0) {
            this.infoList = res.data.data.list;
            this.total = res.data.data.total;
            this.pageSize = res.data.data.pageSize;this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
            this.totalPage = res.data.data.totalPage;
          }
        });
      },
      curChange(page) {
        this.getInfo(page);
      },
      prevClick(page) {
        this.getInfo(page);
      },
      nextClick(page) {
        this.getInfo(page);
      },
      cpictureUploadChange(fileUrls) {
          this.form.cpicture = fileUrls;
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.form.cpicture!=null) {
               this.form.cpicture = this.form.cpicture.replace(new RegExp(this.$config.baseUrl,"g"),"");
            }
            this.form.avatarurl = localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'';
            let sensitiveWords = "";
            let sensitiveWordsArr = [];
            if(sensitiveWords) {
                sensitiveWordsArr = sensitiveWords.split(",");
            }
            for(var i=0; i<sensitiveWordsArr.length; i++){
                //全局替换
                var reg = new RegExp(sensitiveWordsArr[i],"g");
                //判断内容中是否包括敏感词
                if (this.form.content.indexOf(sensitiveWordsArr[i]) > -1) {
                    // 将敏感词替换为 **
                    this.form.content = this.form.content.replace(reg,"**");
                }
            }
            this.$http.post('messages/add', this.form).then(res => {
              if (res.data.code == 0) {
                this.$message({
                  type: 'success',
                  message: '留言成功!',
                  duration: 1500,
                  onClose: () => {
                    this.form.content = '';
                    this.getInfo(1);
                  }
                });
              }
            });
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.msg-page {
  width: 80%;
  margin: 30px auto 60px;
}

/* 卡片通用 */
.msg-card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0,0,0,.07);
  padding: 28px 32px 24px;
  margin-bottom: 24px;
}

.card-title {
  font-size: 20px;
  font-weight: 700;
  color: #222;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 10px;
}

.title-bar {
  display: inline-block;
  width: 4px;
  height: 20px;
  background: #fb3f52;
  border-radius: 2px;
}

/* 输入框 */
.msg-textarea ::v-deep .el-textarea__inner {
  border-radius: 8px;
  border: 1px solid #e0e0e0;
  font-size: 14px;
  color: #333;
  padding: 12px 14px;
  transition: border-color .2s;
  &:focus {
    border-color: #fb3f52;
  }
}

.upload-item ::v-deep .el-form-item__label {
  color: #666;
  font-size: 14px;
}

.btn-row {
  margin-top: 4px;
}

.btn-submit {
  background: #fb3f52 !important;
  border-color: #fb3f52 !important;
  border-radius: 8px !important;
  padding: 0 28px !important;
  height: 38px !important;
  font-size: 14px !important;
}

.btn-submit:hover {
  background: #e02f42 !important;
  border-color: #e02f42 !important;
}

.btn-reset {
  border-radius: 8px !important;
  padding: 0 20px !important;
  height: 38px !important;
  font-size: 14px !important;
  color: #999 !important;
  border-color: #ddd !important;
}

/* 留言列表 */
.msg-list {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0,0,0,.07);
  padding: 24px 32px;
}

.list-header {
  font-size: 16px;
  font-weight: 600;
  color: #333;
  margin-bottom: 20px;
  padding-bottom: 14px;
  border-bottom: 1px solid #f0f0f0;
}

.total-count {
  font-size: 13px;
  color: #999;
  font-weight: 400;
  margin-left: 8px;
}

.msg-item {
  padding: 20px 0;
  border-bottom: 1px dashed #f0f0f0;
  &:last-child { border-bottom: none; }
}

.item-head {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 10px;
}

.item-meta {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.item-username {
  font-size: 14px;
  font-weight: 600;
  color: #333;
}

.item-time {
  font-size: 12px;
  color: #bbb;
}

.item-content {
  font-size: 14px;
  color: #444;
  line-height: 1.7;
  margin-left: 52px;
  margin-bottom: 8px;
}

.item-img {
  margin-left: 52px;
  margin-top: 8px;
  img {
    max-width: 280px;
    max-height: 200px;
    border-radius: 8px;
    border: 1px solid #eee;
    object-fit: cover;
  }
}

.item-reply {
  margin-left: 52px;
  margin-top: 12px;
  background: #f8f8f8;
  border-left: 3px solid #fb3f52;
  border-radius: 0 8px 8px 0;
  padding: 10px 16px;
  font-size: 13px;
  color: #555;
}

.reply-tag {
  display: inline-block;
  background: #fb3f52;
  color: #fff;
  font-size: 11px;
  padding: 1px 8px;
  border-radius: 10px;
  margin-right: 8px;
  font-weight: 600;
  vertical-align: middle;
}

.empty-tip {
  text-align: center;
  color: #bbb;
  font-size: 14px;
  padding: 60px 0;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0,0,0,.07);
  margin-bottom: 24px;
}

.pagination {
  margin-top: 20px;
  text-align: left;
}
</style>
