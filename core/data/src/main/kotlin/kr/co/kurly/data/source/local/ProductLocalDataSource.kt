package kr.co.kurly.data.source.local

import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    fun fetchLikedIds(): Flow<Set<Long>>

    suspend fun saveLikedId(id: Long)
}