<template>
  <div class="categories-manage">
    <!-- 顶部操作栏 -->
    <div class="action-bar">
      <div class="left">
        <button class="primary-btn" @click="createCategory">
          <span class="icon">📁</span>
          新建分类
        </button>
      </div>
      <div class="right">
        <div class="search-box">
          <input 
            type="text" 
            v-model="searchQuery" 
            placeholder="搜索分类..."
            @input="handleSearch"
          >
        </div>
      </div>
    </div>

    <!-- 分类列表 -->
    <div class="categories-table">
      <table>
        <thead>
          <tr>
            <th style="width: 60px">ID</th>
            <th>名称</th>
            <th style="width: 120px">文章数量</th>
            <th style="width: 120px">排序</th>
            <th style="width: 180px">创建时间</th>
            <th style="width: 150px">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="category in categories" :key="category.id">
            <td>{{ category.id }}</td>
            <td>
              <div class="category-name-cell">
                <span class="name">{{ category.name }}</span>
                <span class="path text-secondary">{{ category.path }}</span>
              </div>
            </td>
            <td>{{ category.articleCount }}</td>
            <td>{{ category.sort }}</td>
            <td>{{ formatDate(category.createTime) }}</td>
            <td>
              <div class="actions">
                <button class="action-btn edit" @click="editCategory(category)">编辑</button>
                <button class="action-btn delete" @click="deleteCategory(category)">删除</button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 底部分页 -->
    <div class="bottom-bar">
      <div class="pagination">
        <button 
          :disabled="currentPage === 1" 
          @click="currentPage--"
        >上一页</button>
        <span class="page-info">{{ currentPage }} / {{ totalPages }}</span>
        <button 
          :disabled="currentPage === totalPages" 
          @click="currentPage++"
        >下一页</button>
      </div>
    </div>

    <!-- 分类编辑对话框 -->
    <div v-if="showDialog" class="dialog-overlay" @click="closeDialog">
      <div class="dialog" @click.stop>
        <h3 class="dialog-title">{{ editingCategory ? '编辑分类' : '新建分类' }}</h3>
        <form @submit.prevent="handleSubmit" class="dialog-content">
          <div class="form-item">
            <label>分类名称</label>
            <input 
              v-model="categoryForm.name" 
              type="text" 
              required
              placeholder="请输入分类名称"
            >
          </div>
          <div class="form-item">
            <label>访问路径</label>
            <input 
              v-model="categoryForm.path" 
              type="text" 
              required
              placeholder="请输入访问路径，如：/tech"
            >
          </div>
          <div class="form-item">
            <label>排序</label>
            <input 
              v-model="categoryForm.sort" 
              type="number" 
              required
              placeholder="数字越小越靠前"
            >
          </div>
          <div class="form-item">
            <label>描述</label>
            <textarea 
              v-model="categoryForm.description" 
              rows="3"
              placeholder="请输入分类描述"
            ></textarea>
          </div>
          <div class="dialog-footer">
            <button type="button" class="btn-cancel" @click="closeDialog">取消</button>
            <button type="submit" class="btn-confirm">确定</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// 搜索
const searchQuery = ref('')
const handleSearch = () => {
  currentPage.value = 1
  fetchCategories()
}

// 分页
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const totalPages = computed(() => Math.ceil(total.value / pageSize.value))

// 分类列表
const categories = ref([
  {
    id: 1,
    name: '技术',
    path: '/tech',
    description: '技术相关文章',
    sort: 1,
    articleCount: 12,
    createTime: '2024-03-15T08:00:00.000Z'
  },
  {
    id: 2,
    name: '生活',
    path: '/life',
    description: '生活随笔',
    sort: 2,
    articleCount: 8,
    createTime: '2024-03-14T10:30:00.000Z'
  }
])

// 格式化日期
const formatDate = (date) => {
  if (!date) return ''
  return new Date(date).toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  })
}

// 对话框控制
const showDialog = ref(false)
const editingCategory = ref(null)
const categoryForm = ref({
  name: '',
  path: '',
  description: '',
  sort: 0
})

const createCategory = () => {
  editingCategory.value = null
  categoryForm.value = {
    name: '',
    path: '',
    description: '',
    sort: 0
  }
  showDialog.value = true
}

const editCategory = (category) => {
  editingCategory.value = category
  categoryForm.value = { ...category }
  showDialog.value = true
}

const closeDialog = () => {
  showDialog.value = false
  editingCategory.value = null
}

const handleSubmit = async () => {
  // 实现保存逻辑
  console.log('保存分类:', categoryForm.value)
  closeDialog()
}

const deleteCategory = async (category) => {
  if (confirm(`确定要删除分类"${category.name}"吗？删除后该分类下的文章将变为未分类。`)) {
    // 实现删除逻辑
    console.log('删除分类:', category)
  }
}

// 获取分类列表
const fetchCategories = async () => {
  // 实现获取分类列表的逻辑
}
</script>

<style scoped>
.categories-manage {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.primary-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px 16px;
  background-color: var(--primary-color);
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.primary-btn:hover {
  opacity: 0.9;
}

.search-box input {
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  width: 240px;
  font-size: 14px;
  color: var(--text-primary);
  background-color: var(--card-bg);
}

.categories-table {
  background-color: var(--card-bg);
  border: 1px solid var(--border-color);
  border-radius: 8px;
  overflow: hidden;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid var(--border-color);
}

th {
  background-color: var(--bg-secondary);
  color: var(--text-secondary);
  font-weight: normal;
  font-size: 14px;
}

.category-name-cell {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.category-name-cell .path {
  font-size: 12px;
  color: var(--text-secondary);
}

.actions {
  display: flex;
  gap: 8px;
}

.action-btn {
  padding: 4px 8px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
  transition: all 0.3s ease;
}

.action-btn.edit {
  background-color: var(--hover-bg);
  color: var(--primary-color);
}

.action-btn.delete {
  background-color: rgba(245, 108, 108, 0.1);
  color: #f56c6c;
}

.action-btn:hover {
  opacity: 0.8;
}

.bottom-bar {
  display: flex;
  justify-content: flex-end;
  padding: 12px 0;
}

.pagination {
  display: flex;
  align-items: center;
  gap: 12px;
}

.pagination button {
  padding: 6px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--card-bg);
  color: var(--text-primary);
  cursor: pointer;
}

.pagination button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.page-info {
  color: var(--text-secondary);
}

/* 对话框样式 */
.dialog-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.dialog {
  background-color: var(--card-bg);
  border-radius: 8px;
  width: 90%;
  max-width: 500px;
  padding: 20px;
}

.dialog-title {
  font-size: 18px;
  color: var(--text-primary);
  margin-bottom: 20px;
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

.form-item input,
.form-item textarea {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--bg-primary);
  color: var(--text-primary);
  font-size: 14px;
}

.form-item textarea {
  resize: vertical;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 20px;
}

.btn-cancel,
.btn-confirm {
  padding: 6px 16px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
}

.btn-cancel {
  background-color: var(--bg-secondary);
  color: var(--text-secondary);
  border: 1px solid var(--border-color);
}

.btn-confirm {
  background-color: var(--primary-color);
  color: white;
  border: none;
}

.btn-cancel:hover,
.btn-confirm:hover {
  opacity: 0.9;
}

@media (max-width: 768px) {
  .action-bar {
    flex-direction: column;
    gap: 12px;
  }

  .search-box input {
    width: 100%;
  }

  .categories-table {
    overflow-x: auto;
  }

  .bottom-bar {
    flex-direction: column;
    align-items: center;
    gap: 12px;
  }
}
</style> 