<template>
  <div class="about">
    <header class="page-header">
      <div class="header-content">
        <div class="profile-header">
          <div class="avatar-container">
            
          </div>
          <h1 class="name">靓白</h1>
          <p class="motto">" 保持热爱，奔赴山海 "</p>
        </div>
      </div>
    </header>

    <main class="content">
      <div class="about-grid">
        <!-- 个人简介 -->
        <section class="about-section">
          <h2>👋 关于我</h2>
          <div class="section-content">
            <p>你好！我是靓白，一名热爱技术的后端开发工程师。</p>
            <p>目前专注于 Java 后端开发，同时也对前端技术充满兴趣。</p>
            <p>热衷于探索新技术，喜欢分享技术心得和学习经验。</p>
          </div>
        </section>

        <!-- 技术栈 -->
        <section class="about-section">
          <h2>💻 技术栈</h2>
          <div class="section-content">
            <div class="skills-grid">
              <div class="skill-category">
                <h3>后端开发</h3>
                <div class="skill-tags">
                  <span class="skill-tag">Java</span>
                  <span class="skill-tag">Spring Boot</span>
                  <span class="skill-tag">Spring Cloud</span>
                  <span class="skill-tag">MyBatis</span>
                  <span class="skill-tag">MySQL</span>
                  <span class="skill-tag">Redis</span>
                </div>
              </div>
              <div class="skill-category">
                <h3>前端开发</h3>
                <div class="skill-tags">
                  <span class="skill-tag">Vue.js</span>
                  <span class="skill-tag">JavaScript</span>
                  <span class="skill-tag">HTML/CSS</span>
                </div>
              </div>
              <div class="skill-category">
                <h3>开发工具</h3>
                <div class="skill-tags">
                  <span class="skill-tag">Git</span>
                  <span class="skill-tag">Docker</span>
                  <span class="skill-tag">Linux</span>
                  <span class="skill-tag">IDEA</span>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!-- 兴趣爱好 -->
        <section class="about-section">
          <h2>🌟 兴趣爱好</h2>
          <div class="section-content">
            <div class="interests-grid">
              <div class="interest-item">
                <span class="interest-icon">📚</span>
                <span class="interest-name">阅读</span>
              </div>
              <div class="interest-item">
                <span class="interest-icon">🎮</span>
                <span class="interest-name">游戏</span>
              </div>
              <div class="interest-item">
                <span class="interest-icon">🎵</span>
                <span class="interest-name">音乐</span>
              </div>
              <div class="interest-item">
                <span class="interest-icon">✍️</span>
                <span class="interest-name">写作</span>
              </div>
            </div>
          </div>
        </section>

        <!-- 联系方式 -->
        <section class="about-section">
          <h2>📫 联系我</h2>
          <div class="section-content">
            <div class="contact-list">
              <a href="https://github.com/liangbai2333" target="_blank" class="contact-item">
                <span class="contact-icon">🐱</span>
                <span class="contact-text">GitHub</span>
              </a>
              <div class="contact-item">
                <span class="contact-icon">📧</span>
                <span class="contact-text">liangbai2333@gmail.com</span>
              </div>
              <div class="contact-item">
                <span class="contact-icon">💬</span>
                <span class="contact-text">QQ: 2544704967</span>
              </div>
            </div>
          </div>
        </section>
      </div>
    </main>
  </div>
</template>

<style scoped>
.about {
  min-height: 100vh;
}

.page-header {
  background: linear-gradient(to right, var(--bg-primary), var(--bg-secondary));
  padding: 4rem 2rem;
  text-align: center;
}

.profile-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
}

.avatar-container {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  overflow: hidden;
  border: 4px solid var(--card-bg);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.avatar {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.name {
  font-size: 2.5rem;
  color: var(--text-primary);
  margin: 0;
}

.motto {
  font-size: 1.2rem;
  color: var(--text-secondary);
  font-style: italic;
}

.content {
  max-width: 1000px;
  margin: 0 auto;
  padding: 2rem;
}

.about-grid {
  display: grid;
  gap: 2rem;
}

.about-section {
  background: var(--card-bg);
  border-radius: 12px;
  padding: 2rem;
  border: 1px solid var(--border-color);
}

.about-section h2 {
  font-size: 1.5rem;
  color: var(--text-primary);
  margin-bottom: 1.5rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.section-content {
  color: var(--text-secondary);
  line-height: 1.6;
}

.section-content p {
  margin-bottom: 1rem;
}

.section-content p:last-child {
  margin-bottom: 0;
}

.skills-grid {
  display: grid;
  gap: 2rem;
}

.skill-category h3 {
  font-size: 1.1rem;
  color: var(--text-primary);
  margin-bottom: 1rem;
}

.skill-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
}

.skill-tag {
  background: var(--hover-bg);
  color: var(--primary-color);
  padding: 0.25rem 0.75rem;
  border-radius: 16px;
  font-size: 0.9rem;
}

.interests-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
  gap: 1.5rem;
  text-align: center;
}

.interest-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
}

.interest-icon {
  font-size: 2rem;
}

.interest-name {
  color: var(--text-secondary);
}

.contact-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.contact-item {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  color: var(--text-secondary);
  text-decoration: none;
  transition: color 0.3s ease;
}

a.contact-item:hover {
  color: var(--primary-color);
}

.contact-icon {
  font-size: 1.25rem;
}

@media (max-width: 768px) {
  .page-header {
    padding: 3rem 1rem;
  }

  .avatar-container {
    width: 120px;
    height: 120px;
  }

  .name {
    font-size: 2rem;
  }

  .content {
    padding: 1rem;
  }

  .about-section {
    padding: 1.5rem;
  }

  .interests-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style> 