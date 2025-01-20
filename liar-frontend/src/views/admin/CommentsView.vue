<template>
  <div class="comments-manage">
    <!-- 顶部操作栏 -->
    <div class="action-bar">
      <div class="left">
        <select
          v-model="filterStatus"
          class="filter-select"
        >
          <option value="all">全部评论</option>
          <option value="pending">待审核</option>
          <option value="approved">已通过</option>
          <option value="rejected">已拒绝</option>
        </select>
      </div>
      <div class="right">
        <div class="search-box">
          <input 
            type="text" 
            v-model="searchQuery" 
            placeholder="搜索评论..."
            @input="handleSearch"
          >
        </div>
      </div>
    </div>

    <!-- 评论列表 -->
    <div class="comments-table">
      <table>
        <thead>
          <tr>
            <th style="width: 60px">ID</th>
            <th style="width: 120px">作者</th>
            <th>内容</th>
            <th style="width: 120px">文章</th>
            <th style="width: 100px">状态</th>
            <th style="width: 180px">创建时间</th>
            <th style="width: 200px">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="comment in comments" :key="comment.id">
            <td>{{ comment.id }}</td>
            <td>
              <div class="author-cell">
                <span class="name">{{ comment.author }}</span>
                <span class="email text-secondary">{{ comment.email }}</span>
              </div>
            </td>
            <td>
              <div class="content-cell">{{ comment.content }}</div>
            </td>
            <td>
              <router-link
                :to="{ name: 'article-detail', params: { id: comment.articleId }}"
                class="article-link"
              >
                查看文章
              </router-link>
            </td>
            <td>
              <span
                :class="{
                  'status-tag': true,
                  'pending': comment.status === 'pending',
                  'approved': comment.status === 'approved',
                  'rejected': comment.status === 'rejected'
                }"
              >
                {{ getStatusText(comment.status) }}
              </span>
            </td>
            <td>{{ formatDate(comment.createdAt) }}</td>
            <td>
              <div class="actions">
                <template v-if="comment.status === 'pending'">
                  <button 
                    class="action-btn approve" 
                    @click="handleApprove(comment.id)"
                  >通过</button>
                  <button 
                    class="action-btn reject" 
                    @click="handleReject(comment.id)"
                  >拒绝</button>
                </template>
                <button 
                  class="action-btn delete" 
                  @click="handleDelete(comment.id)"
                >删除</button>
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
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// 搜索和筛选
const searchQuery = ref('')
const filterStatus = ref('all')
const handleSearch = () => {
  currentPage.value = 1
  fetchComments()
}

// 分页
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const totalPages = computed(() => Math.ceil(total.value / pageSize.value))

// 评论列表
const comments = ref([
  {
    id: 1,
    author: '张三',
    email: 'zhangsan@example.com',
    content: '这篇文章写得很好，学到了很多！',
    createdAt: '2024-03-15 10:30:00',
    articleId: 1,
    status: 'approved'
  },
  {
    id: 2,
    author: '李四',
    email: 'lisi@example.com',
    content: '文章内容很详细，但是有一些地方还可以补充...',
    createdAt: '2024-03-15 11:20:00',
    articleId: 1,
    status: 'pending'
  },
  {
    id: 3,
    author: '王五',
    email: 'wangwu@example.com',
    content: '期待更多类似的文章！',
    createdAt: '2024-03-15 14:15:00',
    articleId: 2,
    status: 'pending'
  }
])

// 格式化日期
const formatDate = (date) => {
  return new Date(date).toLocaleString()
}

// 获取状态文本
const getStatusText = (status) => {
  const statusMap = {
    pending: '待审核',
    approved: '已通过',
    rejected: '已拒绝'
  }
  return statusMap[status] || status
}

// 通过评论
const handleApprove = async (id) => {
  const comment = comments.value.find(c => c.id === id)
  if (comment) {
    comment.status = 'approved'
  }
}

// 拒绝评论
const handleReject = async (id) => {
  const comment = comments.value.find(c => c.id === id)
  if (comment) {
    comment.status = 'rejected'
  }
}

// 删除评论
const handleDelete = async (id) => {
  if (!confirm('确定要删除这条评论吗？')) return
  comments.value = comments.value.filter(comment => comment.id !== id)
}

// 获取评论列表
const fetchComments = async () => {
  // 实现获取评论列表的逻辑
}
</script>

<style scoped>
.comments-manage {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.filter-select {
  padding: 8px 12px;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  background-color: var(--card-bg);
  color: var(--text-primary);
  font-size: 14px;
  min-width: 120px;
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

.comments-table {
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

.author-cell {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.author-cell .email {
  font-size: 12px;
  color: var(--text-secondary);
}

.content-cell {
  max-width: 400px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.article-link {
  color: var(--primary-color);
  text-decoration: none;
  font-size: 14px;
}

.article-link:hover {
  text-decoration: underline;
}

.status-tag {
  display: inline-block;
  padding: 4px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.status-tag.pending {
  background-color: #fef3c7;
  color: #92400e;
}

.status-tag.approved {
  background-color: #d1fae5;
  color: #065f46;
}

.status-tag.rejected {
  background-color: #fee2e2;
  color: #991b1b;
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

.action-btn.approve {
  background-color: rgba(16, 185, 129, 0.1);
  color: #10b981;
}

.action-btn.reject {
  background-color: rgba(245, 158, 11, 0.1);
  color: #f59e0b;
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

@media (max-width: 768px) {
  .action-bar {
    flex-direction: column;
    gap: 12px;
  }

  .search-box input {
    width: 100%;
  }

  .comments-table {
    overflow-x: auto;
  }

  .content-cell {
    max-width: 200px;
  }

  .bottom-bar {
    flex-direction: column;
    align-items: center;
    gap: 12px;
  }
}
</style> 