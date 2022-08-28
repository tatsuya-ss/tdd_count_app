package com.example.tdd_count_app

interface UnreadCountRepository {
    suspend fun fetchUnreadCount(): UnreadCount
}

class UnreadCountRepositoryImpl : UnreadCountRepository {
    override suspend fun fetchUnreadCount(): UnreadCount {
        return UnreadCount(1, 1)
    }
}