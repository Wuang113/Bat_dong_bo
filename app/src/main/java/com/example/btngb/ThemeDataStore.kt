package com.example.btngb

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "theme_prefs")

object ThemeDataStore {
    private val COLOR_KEY = longPreferencesKey("theme_color")  // 🔁 Dùng Long thay vì Int

    suspend fun saveColor(context: Context, color: ULong) {
        context.dataStore.edit { prefs ->
            prefs[COLOR_KEY] = color.toLong()  // Ép sang Long để lưu
        }
    }

    fun getColor(context: Context): Flow<ULong?> {
        return context.dataStore.data.map { prefs ->
            prefs[COLOR_KEY]?.toULong()  // Ép ngược về ULong
        }
    }
}
