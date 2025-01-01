<script setup>
defineProps({
  post: {
    type: Object,
    required: true
  }
})
</script>

<template>
  <article class="post-card">
    <div class="post-image">
      <img :src="post.image" :alt="post.title">
    </div>
    <div class="post-content">
      <div class="post-meta">
        <span class="post-category">{{ post.category }}</span>
        <span class="post-date">{{ post.date }}</span>
      </div>
      <h2 class="post-title">{{ post.title }}</h2>
      <p class="post-summary">{{ post.summary }}</p>
      <div class="post-footer">
        <span class="read-time">{{ post.readTime }}</span>
        <a href="#" class="read-more">
          <span>阅读全文</span>
          <svg class="arrow-icon" viewBox="0 0 24 24" width="16" height="16">
            <path fill="currentColor" d="M13.172 12l-4.95-4.95 1.414-1.414L16 12l-6.364 6.364-1.414-1.414z" />
          </svg>
        </a>
      </div>
    </div>
  </article>
</template>

<style scoped>
.post-card {
  background: var(--card-bg);
  border-radius: 1rem;
  overflow: hidden;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  border: 1px solid rgba(255, 255, 255, 0.05);
}

.post-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
  border-color: var(--primary-color);
}

.post-image {
  width: 100%;
  height: 240px;
  overflow: hidden;
}

.post-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}

.post-card:hover .post-image img {
  transform: scale(1.05);
}

.post-content {
  padding: 1.5rem;
}

.post-meta {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
}

.post-category {
  color: var(--primary-color);
  font-weight: 500;
  font-size: 0.875rem;
}

.post-date {
  color: var(--text-secondary);
  font-size: 0.875rem;
}

.post-title {
  font-size: 1.5rem;
  font-weight: 700;
  margin-bottom: 1rem;
  line-height: 1.3;
  color: var(--text-primary);
}

.post-summary {
  color: var(--text-secondary);
  margin-bottom: 1.5rem;
  line-height: 1.6;
}

.post-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 1rem;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.read-time {
  color: var(--text-secondary);
  font-size: 0.875rem;
}

.read-more {
  display: inline-flex;
  align-items: center;
  gap: 0.5rem;
  color: var(--primary-color);
  font-weight: 500;
  transition: all 0.3s ease;
}

.read-more:hover {
  color: var(--hover-color);
}

.arrow-icon {
  transition: transform 0.3s ease;
}

.read-more:hover .arrow-icon {
  transform: translateX(4px);
}
</style> 