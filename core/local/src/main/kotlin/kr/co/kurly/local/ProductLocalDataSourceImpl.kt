package kr.co.kurly.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.co.kurly.data.source.local.ProductLocalDataSource
import javax.inject.Inject

internal class ProductLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
): ProductLocalDataSource {
    override fun fetchLikedIds(): Flow<Set<Long>> {
        return dataStore.data.map { pref ->
            pref[PRODUCT_LIKED_IDS]?.map {
                it.toLong()
            }?.toSet() ?: emptySet()
        }
    }

    override suspend fun saveLikedId(id: Long) {
        dataStore.edit { pref ->
            pref[PRODUCT_LIKED_IDS] = (pref[PRODUCT_LIKED_IDS] ?: emptySet()).toMutableSet().apply {
                add(id.toString())
            }
        }
    }

    override suspend fun deleteLikedId(id: Long) {
        dataStore.edit { pref ->
            pref[PRODUCT_LIKED_IDS] = pref[PRODUCT_LIKED_IDS]?.toMutableSet()?.apply {
                remove(id.toString())
            } ?: emptySet()
        }
    }

    companion object {
        private val PRODUCT_LIKED_IDS = stringSetPreferencesKey("productLikedIds")
    }
}