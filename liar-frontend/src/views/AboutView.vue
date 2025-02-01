<template>
  <div class="about">
    <header class="page-header">
      <div class="header-content">
        <div class="profile-header" v-if="userStore.users[0]">
          <div class="avatar-container">
            <img :src="userStore.users[0].avatar" :alt="userStore.users[0].username">
          </div>
          <h1 class="name">{{ userStore.users[0].username }}</h1>
          <p class="motto">" {{ userStore.users[0].motto }} "</p>
        </div>
      </div>
    </header>

    <main class="content" v-if="userStore.users[0]">
      <div class="about-grid">
        <!-- 个人简介 -->
        <section class="about-section">
          <h2>👋 关于我</h2>
          <div class="section-content">
            <p>{{ userStore.users[0].description }}</p>
          </div>
        </section>

        <!-- 技术栈 -->
        <section class="about-section" v-if="userStore.users[0].skills?.length">
          <h2>💻 技术栈</h2>
          <div class="section-content">
            <div class="skills-grid">
              <div v-for="(skills, category) in groupedSkills" :key="category" class="skill-category">
                <h3>{{ category }}</h3>
                <div class="skill-tags">
                  <span v-for="skill in skills" :key="skill.id" class="skill-tag">
                    <span class="skill-icon">{{ skill.icon }}</span>
                    {{ skill.name }}
                  </span>
                </div>
              </div>
            </div>
          </div>
        </section>

        <!-- 兴趣爱好 -->
        <section class="about-section" v-if="userStore.users[0].interests?.length">
          <h2>🌟 兴趣爱好</h2>
          <div class="section-content">
            <div class="interests-grid">
              <div v-for="interest in userStore.users[0].interests" :key="interest.id" class="interest-item">
                <span class="interest-icon">
                  <img :src="interest.icon" class="interest-icon-img">
                </span>
                <span class="interest-name">{{ interest.name }}</span>
                <span class="interest-value">{{ interest.description }}</span>
              </div>
            </div>
          </div>
        </section>

        <!-- 联系方式 -->
        <section class="about-section" v-if="userStore.users[0].contacts?.length">
          <h2>📫 联系我</h2>
          <div class="section-content">
            <div class="contact-list">
              <a v-for="contact in userStore.users[0].contacts" 
                 :key="contact.id"
                 :href="contact.link"
                 target="_blank"
                 class="contact-item"
              >
                <span class="contact-icon">
                  <img :src="contact.icon" :alt="contact.type" class="contact-icon-img">
                </span>
                <span class="contact-name">{{ contact.type }}</span>
                <span class="contact-value">{{ contact.value }}</span>
              </a>
            </div>
          </div>
        </section>
      </div>
    </main>
  </div>
</template>

<script setup>
import { onMounted, computed } from 'vue'
import { useUserStore } from '../stores/user'

const userStore = useUserStore()

// 按分类分组技能
const groupedSkills = computed(() => {
  if (!userStore.users[0]?.skills) return {}
  return userStore.users[0].skills.reduce((groups, skill) => {
    const category = skill.category.name
    if (!groups[category]) {
      groups[category] = []
    }
    groups[category].push(skill)
    return groups
  }, {})
})

onMounted(async () => {
  await userStore.fetchUsers()
})
</script>

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
  padding: 0.5rem 1rem;
  border-radius: 16px;
  font-size: 0.9rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.skill-icon {
  font-size: 1.1rem;
}

.interests-grid {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.interest-item {
  display: grid;
  grid-template-columns: 24px auto 1fr;
  align-items: center;
  gap: 0.5rem;
  color: var(--text-secondary);
}

.interest-icon {
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.interest-icon-img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

.interest-name {
  font-weight: 500;
  color: var(--text-primary);
  margin-right: 0.5rem;
}

.interest-value {
  color: var(--text-secondary);
}

.contact-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.contact-item {
  display: grid;
  grid-template-columns: 24px auto 1fr;
  align-items: center;
  gap: 0.5rem;
  color: var(--text-secondary);
  text-decoration: none;
}

.contact-icon {
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.contact-icon-img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

.contact-name {
  font-weight: 500;
  color: var(--text-primary);
  margin-right: 0.5rem;
}

.contact-value {
  color: var(--text-secondary);
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

  .interest-item,
  .contact-item {
    grid-template-columns: 24px auto 1fr;
    gap: 0.5rem;
  }
}
</style> 