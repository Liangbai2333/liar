<template>
  <div class="login">
    <div class="login-container">
      <h1 class="title">管理员登录</h1>
      <form @submit.prevent="handleLogin" class="login-form">
        <div class="error-message" v-if="authStore.error">
          {{ authStore.error }}
        </div>
        <div class="form-group">
          <label for="username">用户名</label>
          <input 
            type="text" 
            id="username"
            v-model="username"
            :disabled="authStore.loading"
            placeholder="请输入用户名"
            required
          >
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input 
            type="password" 
            id="password"
            v-model="password"
            :disabled="authStore.loading"
            placeholder="请输入密码"
            required
          >
        </div>
        <button 
          type="submit" 
          class="login-btn"
          :disabled="authStore.loading"
        >
          {{ authStore.loading ? '登录中...' : '登录' }}
        </button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useAuthStore } from '../../stores/auth'

const authStore = useAuthStore()
const username = ref('')
const password = ref('')

const handleLogin = async () => {
  if (!username.value || !password.value) return
  await authStore.loginAction(username.value, password.value)
}
</script>

<style scoped>
.login {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to right, var(--bg-primary), var(--bg-secondary));
}

.login-container {
  background: var(--card-bg);
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  border: 1px solid var(--border-color);
}

.title {
  text-align: center;
  color: var(--text-primary);
  margin-bottom: 2rem;
  font-size: 1.5rem;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.form-group label {
  color: var(--text-primary);
  font-size: 0.9rem;
  font-weight: 500;
}

.form-group input {
  padding: 0.75rem 1rem;
  border: 1px solid var(--border-color);
  border-radius: 6px;
  background: var(--bg-primary);
  color: var(--text-primary);
  font-size: 1rem;
  transition: all 0.3s ease;
}

.form-group input:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 0 2px var(--primary-color-light);
}

.form-group input:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.login-btn {
  background: var(--primary-color);
  color: white;
  padding: 0.75rem;
  border: none;
  border-radius: 6px;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
}

.login-btn:hover:not(:disabled) {
  background: var(--primary-color-dark);
}

.login-btn:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.error-message {
  background: var(--error-bg, #fef2f2);
  color: var(--error-text, #dc2626);
  padding: 0.75rem 1rem;
  border-radius: 6px;
  font-size: 0.875rem;
  margin-bottom: 1rem;
  border: 1px solid var(--error-border, #fecaca);
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.error-message::before {
  content: "⚠";
  font-size: 1rem;
}

@media (max-width: 768px) {
  .login-container {
    margin: 1rem;
    padding: 1.5rem;
  }
}
</style> 