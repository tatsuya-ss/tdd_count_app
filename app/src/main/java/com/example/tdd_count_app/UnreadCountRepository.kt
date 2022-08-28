package com.example.tdd_count_app

data class UnreadCount(
    val fromUserCount: Int,
    val fromSystemCount: Int,
)

interface UnreadCountRepository {
    suspend fun fetchUnreadCount(): UnreadCount
}

class UnreadCountRepositoryImpl : UnreadCountRepository {
    override suspend fun fetchUnreadCount(): UnreadCount {
        return UnreadCount(1, 1)
    }
}