package com.example.bankchellengemobileapp.network

import android.content.Context
import java.util.UUID

object OnboardingManager {

    private const val PREF_NAME = "onboarding_prefs"
    private const val KEY_CLIENT_UUID = "pending_client_uuid"

    fun savePendingClientUuid(context: Context, clientUuid: UUID) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_CLIENT_UUID, clientUuid.toString()).apply()
    }

    fun getPendingClientUuid(context: Context): UUID? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_CLIENT_UUID, null)?.let { UUID.fromString(it) }
    }

    fun clearPendingClientUuid(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_CLIENT_UUID).apply()
    }

}