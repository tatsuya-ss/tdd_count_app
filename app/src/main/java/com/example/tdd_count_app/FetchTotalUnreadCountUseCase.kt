package com.example.tdd_count_app

class FetchTotalUnreadCountUseCase {
    private val unreadCountRepository: UnreadCountRepository = UnreadCountRepositoryImpl()

    suspend fun invoke(): UnreadCount {
        return unreadCountRepository.fetchUnreadCount()
    }
}