package com.example.tdd_count_app

data class UnreadCount(
    val fromUserCount: Int,
    val fromSystemCount: Int,
) {
    val totalCount = fromUserCount + fromSystemCount
}
