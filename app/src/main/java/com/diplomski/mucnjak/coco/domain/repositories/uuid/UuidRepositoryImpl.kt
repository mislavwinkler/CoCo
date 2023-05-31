package com.diplomski.mucnjak.coco.domain.repositories.uuid

import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

private const val UUID_SHARED_PREF_KEY = "UUID_SHARED_PREF_KEY"

@Singleton
class UuidRepositoryImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences
) : UuidRepository {

    private var uuid: UUID? = null

    override fun getUuid(): UUID = uuid
        ?: sharedPrefs.getString(UUID_SHARED_PREF_KEY, null)?.let {
            uuid = UUID.fromString(it)
            return@let uuid
        }
        ?: generateAndStoreUuid()

    private fun generateAndStoreUuid(): UUID {
        val newUuid = UUID.randomUUID()
        sharedPrefs.edit().putString(UUID_SHARED_PREF_KEY, newUuid.toString()).apply()
        uuid = newUuid
        return newUuid
    }
}