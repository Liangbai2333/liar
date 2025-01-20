<template>
  <div class="login-container">
    <div class="login-box">
      <h1 class="login-title">管理员登录</h1>
      <div class="login-form">
        <div class="form-item">
          <label>用户名</label>
          <input
            v-model="loginForm.username"
            type="text"
            class="input-field"
            placeholder="请输入用户名"
            @keyup.enter="handleLogin"
          >
        </div>
        <div class="form-item">
          <label>密码</label>
          <input
            v-model="loginForm.password"
            type="password"
            class="input-field"
            placeholder="请输入密码"
            @keyup.enter="handleLogin"
          >
        </div>
        <div class="form-item">
          <label class="checkbox-label">
            <input type="checkbox" v-model="loginForm.remember">
            记住我
          </label>
        </div>
        <div class="form-item">
          <button class="login-btn" @click="handleLogin" :disabled="isLoading">
            {{ isLoading ? '登录中...' : '登录' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const isLoading = ref(false)
const loginForm = ref({
  username: '',
  password: '',
  remember: false
})

const handleLogin = async () => {
  if (!loginForm.value.username || !loginForm.value.password) {
    // TODO: 添加提示信息
    return
  }

  try {
    isLoading.value = true
    // TODO: 实现登录逻辑
    // const response = await login(loginForm.value)
    // if (response.success) {
    //   router.push('/admin/dashboard')
    // }
    
    // 临时模拟登录成功
    setTimeout(() => {
      router.push('/admin/dashboard')
    }, 1000)
  } catch (error) {
    console.error('登录失败:', error)
  } finally {
    isLoading.value = false
  }
}
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--bg-primary);
  padding: 20px;
}

.login-box {
  width: 100%;
  max-width: 400px;
  background-color: var(--card-bg);
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  padding: 40px;
}

.login-title {
  font-size: 24px;
  font-weight: bold;
  color: var(--text-primary);
  text-align: center;
  margin-bottom: 40px;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-item {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-item label {
  color: var(--text-primary);
  font-size: 14px;
}

.input-field {
  width: 100%;
  padding: 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--bg-primary);
  color: var(--text-primary);
  font-size: 14px;
  transition: all 0.3s ease;
}

.input-field:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 0 2px rgba(var(--primary-color-rgb), 0.1);
}

.checkbox-label {
  display: flex;
  align-items: center;
  gap: 8px;
  color: var(--text-primary);
  font-size: 14px;
  cursor: pointer;
}

.login-btn {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 4px;
  background-color: var(--primary-color);
  color: white;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
}

.login-btn:hover:not(:disabled) {
  opacity: 0.9;
}

.login-btn:disabled {
  background-color: var(--border-color);
  cursor: not-allowed;
}

@media (max-width: 480px) {
  .login-box {
    padding: 20px;
  }
}
</style> 