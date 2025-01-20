import { ref } from 'vue'
import { defineStore } from 'pinia'
import image1 from '@/assets/img/1702044245889.png'
import image2 from '@/assets/img/1717562426845.png'
import image3 from '@/assets/img/4453bd510cf678d78de10216e5d0fc4472233788.jpg'

export const usePostsStore = defineStore('posts', () => {
    const posts = ref([
        {
            title: '探索 Web3.0 的未来',
            date: '2024-03-15',
            category: '技术',
            summary: '随着区块链技术的发展，Web3.0 正在重塑互联网的未来。本文将深入探讨 Web3.0 的核心概念和潜在应用。',
            readTime: '8 分钟阅读',
            image: image1
        },
        {
            title: '极简主义工作流程：提升效率的艺术',
            date: '2024-03-12',
            category: '生产力',
            summary: '在这个信息过载的时代，如何通过极简主义的方法论来提升工作效率？让我们一起探索这个话题。',
            readTime: '6 分钟阅读',
            image: image2
        },
        {
            title: '设计中的留白：简约之美',
            date: '2024-03-10',
            category: '设计',
            summary: '留白不仅仅是空间，更是设计的艺术。本文将分享如何在设计中运用留白创造出优雅的视觉效果。',
            readTime: '5 分钟阅读',
            image: image3
        }
    ])

    return { posts }
}) 