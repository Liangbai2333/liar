<template>
  <div class="article-edit">
    <div class="action-bar">
      <h1 class="page-title">{{ isEdit ? '编辑文章' : '创建文章' }}</h1>
      <div class="action-buttons">
        <button class="action-btn preview" @click="togglePreview">
          {{ isPreview ? '编辑' : '预览' }}
        </button>
        <button class="action-btn save" @click="saveDraft">
          保存草稿
        </button>
        <button class="action-btn publish" @click="publish">
          {{ isEdit ? '更新' : '发布' }}
        </button>
      </div>
    </div>

    <div class="edit-container">
      <div class="main-content">
        <!-- 标题输入 -->
        <div class="title-input">
          <input
            v-model="article.title"
            type="text"
            placeholder="请输入文章标题..."
            class="input-field"
          >
        </div>

        <!-- 编辑器区域 -->
        <div class="editor-container" v-show="!isPreview">
          <textarea
            v-model="article.content"
            class="markdown-editor"
            placeholder="开始创作..."
          ></textarea>
        </div>

        <!-- 预览区域 -->
        <div class="preview-container markdown-body" v-show="isPreview" v-html="renderedContent">
        </div>
      </div>

      <div class="side-panel">
        <!-- 文章设置 -->
        <div class="setting-card">
          <h3 class="card-title">文章设置</h3>
          
          <!-- 分类选择 -->
          <div class="form-item">
            <label>分类</label>
            <select v-model="article.categoryId" class="select-field">
              <option value="">请选择分类</option>
              <option v-for="category in categories" :key="category.id" :value="category.id">
                {{ category.name }}
              </option>
            </select>
          </div>

          <!-- 标签选择 -->
          <div class="form-item">
            <label>标签</label>
            <div class="tags-select">
              <div
                v-for="tag in tags"
                :key="tag.id"
                :class="['tag-item', { active: article.tags.includes(tag.id) }]"
                @click="toggleTag(tag.id)"
              >
                {{ tag.name }}
              </div>
            </div>
          </div>

          <!-- 文章摘要 -->
          <div class="form-item">
            <label>摘要</label>
            <textarea
              v-model="article.summary"
              class="textarea-field"
              rows="3"
              placeholder="请输入文章摘要..."
            ></textarea>
          </div>

          <!-- 封面图片 -->
          <div class="form-item">
            <label>封面图片</label>
            <div class="cover-upload">
              <div v-if="article.cover" class="cover-preview">
                <img :src="article.cover" alt="封面预览">
                <button class="remove-cover" @click="removeCover">×</button>
              </div>
              <div v-else class="upload-placeholder" @click="uploadCover">
                <span>点击上传封面</span>
              </div>
            </div>
          </div>

          <!-- 其他设置 -->
          <div class="form-item">
            <label>其他设置</label>
            <div class="checkbox-group">
              <label class="checkbox-item">
                <input type="checkbox" v-model="article.allowComment">
                允许评论
              </label>
              <label class="checkbox-item">
                <input type="checkbox" v-model="article.isTop">
                置顶文章
              </label>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { marked } from 'marked'

const route = useRoute()
const router = useRouter()
const isEdit = computed(() => !!route.params.id)
const isPreview = ref(false)

// 文章数据
const article = ref({
  title: '',
  content: '',
  categoryId: '',
  tags: [],
  summary: '',
  cover: '',
  allowComment: true,
  isTop: false
})

// 分类和标签数据
const categories = ref([
  { id: 1, name: '技术' },
  { id: 2, name: '生活' },
  { id: 3, name: '随笔' }
])

const tags = ref([
  { id: 1, name: 'JavaScript' },
  { id: 2, name: 'Vue' },
  { id: 3, name: 'React' },
  { id: 4, name: 'Node.js' }
])

// 切换预览
const togglePreview = () => {
  isPreview.value = !isPreview.value
}

// 渲染 Markdown
const renderedContent = computed(() => {
  return marked(article.value.content || '')
})

// 切换标签
const toggleTag = (tagId) => {
  const index = article.value.tags.indexOf(tagId)
  if (index === -1) {
    article.value.tags.push(tagId)
  } else {
    article.value.tags.splice(index, 1)
  }
}

// 上传封面
const uploadCover = () => {
  // 实现上传逻辑
}

// 移除封面
const removeCover = () => {
  article.value.cover = ''
}

// 保存草稿
const saveDraft = async () => {
  // 实现保存草稿逻辑
  console.log('保存草稿:', article.value)
}

// 发布文章
const publish = async () => {
  // 实现发布逻辑
  console.log('发布文章:', article.value)
}
</script>

<style scoped>
.article-edit {
  min-height: 100vh;
  background-color: var(--bg-primary);
  padding: 20px;
}

.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.page-title {
  font-size: 24px;
  font-weight: bold;
  color: var(--text-primary);
}

.action-buttons {
  display: flex;
  gap: 12px;
}

.action-btn {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.action-btn.preview {
  background-color: var(--hover-bg);
  color: var(--text-primary);
}

.action-btn.save {
  background-color: var(--bg-secondary);
  color: var(--text-primary);
}

.action-btn.publish {
  background-color: var(--primary-color);
  color: white;
}

.action-btn:hover {
  opacity: 0.9;
}

.edit-container {
  display: grid;
  grid-template-columns: 1fr 300px;
  gap: 20px;
}

.main-content {
  background-color: var(--card-bg);
  border: 1px solid var(--border-color);
  border-radius: 8px;
  overflow: hidden;
}

.title-input {
  padding: 20px;
  border-bottom: 1px solid var(--border-color);
}

.input-field {
  width: 100%;
  padding: 12px;
  font-size: 20px;
  border: none;
  background: none;
  color: var(--text-primary);
}

.input-field:focus {
  outline: none;
}

.editor-container,
.preview-container {
  height: calc(100vh - 200px);
  padding: 20px;
  overflow-y: auto;
}

.markdown-editor {
  width: 100%;
  height: 100%;
  border: none;
  resize: none;
  background: none;
  color: var(--text-primary);
  font-size: 16px;
  line-height: 1.6;
}

.markdown-editor:focus {
  outline: none;
}

.side-panel {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.setting-card {
  background-color: var(--card-bg);
  border: 1px solid var(--border-color);
  border-radius: 8px;
  padding: 20px;
}

.card-title {
  font-size: 16px;
  font-weight: bold;
  color: var(--text-primary);
  margin-bottom: 16px;
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

.select-field {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--bg-primary);
  color: var(--text-primary);
  font-size: 14px;
}

.tags-select {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag-item {
  padding: 4px 12px;
  border-radius: 4px;
  background-color: var(--hover-bg);
  color: var(--text-primary);
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.tag-item.active {
  background-color: var(--primary-color);
  color: white;
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

.cover-upload {
  border: 1px dashed var(--border-color);
  border-radius: 4px;
  overflow: hidden;
}

.cover-preview {
  position: relative;
  width: 100%;
  padding-top: 56.25%;
}

.cover-preview img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.remove-cover {
  position: absolute;
  top: 8px;
  right: 8px;
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
  padding: 40px 0;
  text-align: center;
  color: var(--text-secondary);
  cursor: pointer;
}

.checkbox-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.checkbox-item {
  display: flex;
  align-items: center;
  gap: 8px;
  color: var(--text-primary);
  font-size: 14px;
  cursor: pointer;
}

@media (max-width: 768px) {
  .edit-container {
    grid-template-columns: 1fr;
  }

  .action-bar {
    flex-direction: column;
    gap: 12px;
    align-items: flex-start;
  }

  .action-buttons {
    width: 100%;
  }

  .action-btn {
    flex: 1;
  }
}
</style> 