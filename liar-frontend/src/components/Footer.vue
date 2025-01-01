<script setup>
const currentYear = new Date().getFullYear()
</script>

<template>
  <footer class="footer">
    <div class="footer-content">
      <div class="footer-info">
        <p class="copyright">
          © {{ currentYear }} Liangbai2333. All Rights Reserved.
        </p>
        <p class="icp">
          <a href="https://beian.miit.gov.cn/" target="_blank" rel="noopener">
            闽ICP备XXXXXXXX号-1
          </a>
        </p>
      </div>
      <div class="footer-links">
        <a href="#" class="footer-link">隐私政策</a>
        <span class="divider">|</span>
        <a href="#" class="footer-link">使用条款</a>
        <span class="divider">|</span>
        <a href="#" class="footer-link">联系我们</a>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: var(--bg-secondary);
  padding: 1.5rem 0;
  margin-top: auto;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  text-align: center;
}

.footer-info {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  color: var(--text-secondary);
  font-size: 0.875rem;
}

.icp a {
  color: var(--text-secondary);
  transition: color 0.3s ease;
}

.icp a:hover {
  color: var(--primary-color);
}

.footer-links {
  display: flex;
  gap: 1rem;
  align-items: center;
}

.footer-link {
  color: var(--text-secondary);
  font-size: 0.875rem;
  transition: color 0.3s ease;
}

.footer-link:hover {
  color: var(--primary-color);
}

.divider {
  color: var(--text-secondary);
  font-size: 0.875rem;
}

@media (max-width: 768px) {
  .footer-content {
    padding: 0 1rem;
  }

  .footer-links {
    flex-wrap: wrap;
    justify-content: center;
  }
}
</style> 