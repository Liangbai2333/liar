import { ref, onMounted } from 'vue'

export function useTheme() {
  const isLightMode = ref(true)

  const applyTheme = (isLight) => {
    if (isLight) {
      document.documentElement.classList.remove('dark-mode')
      document.documentElement.classList.remove('dark')
    } else {
      document.documentElement.classList.add('dark-mode')
      document.documentElement.classList.add('dark')
    }
  }

  const toggleTheme = () => {
    isLightMode.value = !isLightMode.value
    applyTheme(isLightMode.value)
  }

  // 初始化主题
  const initTheme = () => {
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches
    isLightMode.value = !prefersDark
    applyTheme(isLightMode.value)
  }

  // 在组件挂载时初始化主题
  onMounted(() => {
    initTheme()
  })

  return {
    isLightMode,
    toggleTheme
  }
} 