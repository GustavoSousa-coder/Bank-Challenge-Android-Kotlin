package com.example.bankchellengemobileapp.network

import android.content.Context
import java.util.UUID

object TokenManager {

    private const val PREF_NAME = "auth_prefs"
    private const val KEY_TOKEN = "jwt_token"
    private const val KEY_CLIENT_UUID = "client_uuid"

    fun saveSession(context: Context, token: String, ClientUuid: UUID) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(KEY_TOKEN, token)
            .putString(KEY_CLIENT_UUID, ClientUuid.toString())
            .apply()
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
        prefs.edit()
            .remove(KEY_TOKEN)
            .apply()
    }

}