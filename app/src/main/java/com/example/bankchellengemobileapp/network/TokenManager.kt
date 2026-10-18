package com.example.bankchellengemobileapp.network

import android.annotation.SuppressLint
import android.content.Context
import java.util.UUID
import androidx.core.content.edit

object TokenManager {

    private const val PREF_NAME = "auth_prefs"
    private const val KEY_TOKEN = "jwt_token"
    private const val KEY_CLIENT_UUID = "uuid"
    private const val KEY_ACCOUNT_ID = "account_id"

    fun saveSession(context: Context, token: String, uuid: UUID) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(KEY_TOKEN, token)
                .putString(KEY_CLIENT_UUID, uuid.toString())
                .remove(KEY_ACCOUNT_ID)
        }
    }

    fun saveAccountId(context: Context, accountId: UUID) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(KEY_ACCOUNT_ID, accountId.toString())
        }
    }

    fun getAccountId(context: Context): UUID? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_ACCOUNT_ID, null)?.let { UUID.fromString(it) }
    }

    fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_TOKEN, null)
    }

    fun getClientUuid(context: Context): UUID? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val uuidString = prefs.getString(KEY_CLIENT_UUID, null)
        return uuidString?.let { UUID.fromString(it) }
    }

    fun clearToken(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            remove(KEY_TOKEN)
                .remove(KEY_CLIENT_UUID)
                .remove(KEY_ACCOUNT_ID)
        }
    }

}