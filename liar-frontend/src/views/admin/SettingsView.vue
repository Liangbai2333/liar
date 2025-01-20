<template>
  <div class="settings-manage">
    <div class="settings-container">
      <!-- 个人信息设置 -->
      <div class="settings-card">
        <h2 class="card-title">个人信息</h2>
        <div class="form-content">
          <div class="form-item">
            <label>头像</label>
            <div class="avatar-upload">
              <div v-if="settings.avatar" class="avatar-preview">
                <img :src="settings.avatar" alt="头像预览">
                <button class="remove-avatar" @click="removeAvatar">×</button>
              </div>
              <div v-else class="upload-placeholder" @click="uploadAvatar">
                <span>点击上传头像</span>
              </div>
            </div>
          </div>

          <div class="form-item">
            <label>昵称</label>
            <input
              v-model="settings.nickname"
              type="text"
              class="input-field"
              placeholder="请输入昵称"
            >
          </div>

          <div class="form-item">
            <label>个人简介</label>
            <textarea
              v-model="settings.bio"
              class="textarea-field"
              rows="3"
              placeholder="请输入个人简介"
            ></textarea>
          </div>

          <div class="form-item">
            <label>邮箱</label>
            <input
              v-model="settings.email"
              type="email"
              class="input-field"
              placeholder="请输入邮箱"
            >
          </div>

          <div class="form-item">
            <label>社交链接</label>
            <div class="social-links">
              <div class="social-item">
                <span class="social-icon">🌐</span>
                <input
                  v-model="settings.website"
                  type="url"
                  class="input-field"
                  placeholder="个人网站"
                >
              </div>
              <div class="social-item">
                <span class="social-icon">📧</span>
                <input
                  v-model="settings.github"
                  type="url"
                  class="input-field"
                  placeholder="GitHub"
                >
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 网站设置 -->
      <div class="settings-card">
        <h2 class="card-title">网站设置</h2>
        <div class="form-content">
          <div class="form-item">
            <label>网站标题</label>
            <input
              v-model="settings.siteTitle"
              type="text"
              class="input-field"
              placeholder="请输入网站标题"
            >
          </div>

          <div class="form-item">
            <label>网站描述</label>
            <textarea
              v-model="settings.siteDescription"
              class="textarea-field"
              rows="3"
              placeholder="请输入网站描述"
            ></textarea>
          </div>

          <div class="form-item">
            <label>页脚信息</label>
            <textarea
              v-model="settings.footerInfo"
              class="textarea-field"
              rows="3"
              placeholder="请输入页脚信息"
            ></textarea>
          </div>

          <div class="form-item">
            <label>其他设置</label>
            <div class="checkbox-group">
              <label class="checkbox-item">
                <input type="checkbox" v-model="settings.enableComment">
                启用评论功能
              </label>
              <label class="checkbox-item">
                <input type="checkbox" v-model="settings.enableRSS">
                启用 RSS 订阅
              </label>
              <label class="checkbox-item">
                <input type="checkbox" v-model="settings.enableSitemap">
                启用站点地图
              </label>
            </div>
          </div>
        </div>
      </div>

      <!-- 安全设置 -->
      <div class="settings-card">
        <h2 class="card-title">安全设置</h2>
        <div class="form-content">
          <div class="form-item">
            <label>修改密码</label>
            <input
              v-model="password.old"
              type="password"
              class="input-field"
              placeholder="请输入原密码"
            >
            <input
              v-model="password.new"
              type="password"
              class="input-field mt-2"
              placeholder="请输入新密码"
            >
            <input
              v-model="password.confirm"
              type="password"
              class="input-field mt-2"
              placeholder="请确认新密码"
            >
          </div>
        </div>
      </div>

      <!-- 保存按钮 -->
      <div class="action-bar">
        <button class="action-btn save" @click="saveSettings">保存设置</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// 设置数据
const settings = ref({
  // 个人信息
  avatar: '',
  nickname: '',
  bio: '',
  email: '',
  website: '',
  github: '',

  // 网站设置
  siteTitle: '',
  siteDescription: '',
  footerInfo: '',
  enableComment: true,
  enableRSS: true,
  enableSitemap: true
})

// 密码修改
const password = ref({
  old: '',
  new: '',
  confirm: ''
})

// 上传头像
const uploadAvatar = () => {
  // 实现上传逻辑
}

// 移除头像
const removeAvatar = () => {
  settings.value.avatar = ''
}

// 保存设置
const saveSettings = async () => {
  // 实现保存逻辑
  console.log('保存设置:', settings.value)
  if (password.value.new) {
    console.log('修改密码:', password.value)
  }
}
</script>

<style scoped>
.settings-manage {
  min-height: 100vh;
  background-color: var(--bg-primary);
  padding: 20px;
}

.settings-container {
  max-width: 800px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.settings-card {
  background-color: var(--card-bg);
  border: 1px solid var(--border-color);
  border-radius: 8px;
  padding: 20px;
}

.card-title {
  font-size: 18px;
  font-weight: bold;
  color: var(--text-primary);
  margin-bottom: 20px;
  padding-bottom: 12px;
  border-bottom: 1px solid var(--border-color);
}

.form-content {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.form-item {
  margin-bottom: 16px;
}

.form-item label {
  display: block;
  color: var(--text-primary);
  margin-bottom: 8px;
  font-size: 14px;
}

.input-field {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--bg-primary);
  color: var(--text-primary);
  font-size: 14px;
}

.input-field:focus {
  outline: none;
  border-color: var(--primary-color);
}

.textarea-field {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--bg-primary);
  color: var(--text-primary);
  font-size: 14px;
  resize: vertical;
}

.textarea-field:focus {
  outline: none;
  border-color: var(--primary-color);
}

.avatar-upload {
  width: 120px;
  height: 120px;
  border: 1px dashed var(--border-color);
  border-radius: 50%;
  overflow: hidden;
}

.avatar-preview {
  position: relative;
  width: 100%;
  height: 100%;
}

.avatar-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.remove-avatar {
  position: absolute;
  top: 4px;
  right: 4px;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  border: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}

.upload-placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--text-secondary);
  cursor: pointer;
}

.social-links {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.social-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.social-icon {
  font-size: 20px;
  width: 24px;
  text-align: center;
}

.checkbox-group {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.checkbox-item {
  display: flex;
  align-items: center;
  gap: 8px;
  color: var(--text-primary);
  font-size: 14px;
  cursor: pointer;
}

.action-bar {
  display: flex;
  justify-content: flex-end;
  padding: 20px 0;
}

.action-btn {
  padding: 8px 24px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.action-btn.save {
  background-color: var(--primary-color);
  color: white;
}

.action-btn:hover {
  opacity: 0.9;
}

.mt-2 {
  margin-top: 8px;
}

@media (max-width: 768px) {
  .settings-container {
    padding: 0;
  }

  .social-links {
    flex-direction: column;
  }

  .social-item {
    width: 100%;
  }
}
</style> 