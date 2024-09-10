package com.kantinku.domain.repo

interface HistoryRepository {
    fun getHistory(): List<String>
}