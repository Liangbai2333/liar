<script setup>
import { usePostsStore } from '../stores/posts'
import PostCard from '../components/PostCard.vue'

const postsStore = usePostsStore()


</script>

<template>
  <div class="home">
    <header class="blog-header">
      <div class="header-content">
        <h1 class="main-title">探索创意与技术的交汇</h1>
        <p class="main-subtitle">在这里，我们分享关于设计、技术和创意的思考</p>
      </div>
    </header>

    <main class="content">
      <div class="posts-grid">
        <PostCard v-for="post in postsStore.posts.slice(0, 3)" 
                 :key="post.title" 
                 :post="post" />
      </div>
    </main>
  </div>
</template>

<style scoped>
.home {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.blog-header {
  padding: 8rem 2rem 4rem;
  background: linear-gradient(to right, var(--bg-primary), var(--bg-secondary));
  text-align: center;
}

.header-content {
  max-width: 800px;
  margin: 0 auto;
}

.main-title {
  font-size: 3rem;
  font-weight: 800;
  letter-spacing: -1px;
  margin-bottom: 1rem;
  line-height: 1.2;
}

.main-subtitle {
  font-size: 1.25rem;
  color: var(--text-secondary);
}

.content {
  flex: 1;
  max-width: 1200px;
  margin: 4rem auto;
  padding: 0 2rem;
  width: 100%;
}

.posts-grid {
  display: grid;
  gap: 2rem;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
}

@media (max-width: 768px) {
  .blog-header {
    padding: 6rem 1rem 3rem;
  }

  .main-title {
    font-size: 2rem;
  }

  .content {
    margin: 2rem auto;
    padding: 0 1rem;
  }
}
</style> 