package com.homework.lovedog.utils

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import java.util.*


object MMKVUtils {
    private const val CRYPT_KEY = "*Dog-&Encrypt_@Key"
    private const val DOG_MMAP_ID = "dog_mmap_id"

    private val kv by lazy {
        // an unencrypted kv instance
        MMKV.mmkvWithID(DOG_MMAP_ID, MMKV.SINGLE_PROCESS_MODE, CRYPT_KEY)
    }


    fun encode(key: String?, `object`: Any?) {
        when (`object`) {
            is String -> kv?.encode(key, `object`)
            is Int -> kv?.encode(key, `object`)
            is Boolean -> kv?.encode(key, `object`)
            is Float -> kv?.encode(key, `object`)
            is Long -> kv?.encode(key, `object`)
            is Double -> kv?.encode(key, `object`)
            is ByteArray -> kv?.encode(key, `object`)
            else -> kv?.encode(key, `object`?.toString())
        }
    }

    fun <T : Parcelable> encode(key: String, t: T?) {
        if (t == null) {
            return
        }
        kv?.encode(key, t)
    }

    fun encode(key: String, sets: Set<String>?) {
        if (sets == null) {
            return
        }
        kv?.encode(key, sets)
    }


    private fun decodeInt(key: String): Int? {
        return kv?.decodeInt(key, 0)
    }

    private fun decodeDouble(key: String): Double? {
        return kv?.decodeDouble(key, 0.00)
    }

    private fun decodeLong(key: String): Long? {
        return kv?.decodeLong(key, 0L)
    }

    private fun decodeBoolean(key: String, default: Boolean): Boolean? {
        return kv?.decodeBool(key, default)
    }

    private fun decodeBoolean(key: String): Boolean? {
        return decodeBoolean(key, false)
    }

    private fun decodeFloat(key: String): Float? {
        return kv?.decodeFloat(key, 0F)
    }

    private fun decodeByteArray(key: String): ByteArray? {
        return kv?.decodeBytes(key)
    }

    private fun decodeString(key: String): String? {
        return kv?.decodeString(key)
    }

    private fun decodeString(key: String, default: String?): String? {
        return kv?.decodeString(key, default)
    }

    private fun <T : Parcelable> decodeParcelable(key: String, tClass: Class<T>): T? {
        return kv?.decodeParcelable(key, tClass)
    }

    private fun decodeStringSet(key: String): Set<String>? {
        return kv?.decodeStringSet(key, Collections.emptySet())
    }

    fun removeForKey(key: String) {
        kv?.removeValueForKey(key)
    }

    fun clearAll() {
        kv?.clearAll()
    }


    fun saveDbPath(mddPath: String?) {
        encode("db_path", mddPath)
    }

    fun getDbPath(): String? {
        return decodeString("db_path")
    }


}