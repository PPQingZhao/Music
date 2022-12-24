package com.pp.library_base.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.collect

private const val THEME_SETTING = "theme_setting"
private val Context.themeDataStore by preferencesDataStore(name = THEME_SETTING)

enum class PreferenceTheme {
    SIMPLE_DEFAULT,
    SIMPLE_NIGHT,
    SIMPLE_BLUE,
}

private val themeKey by lazy { intPreferencesKey("theme") }

suspend fun Context.setPreferenceTheme(theme: PreferenceTheme) {
    themeDataStore.edit {
        it[themeKey] = theme.ordinal
    }

}

suspend fun Context.getPreferenceTheme(callBack: (theme: Int?) -> Unit) {
    themeDataStore.data.collect {
        val theme = it[themeKey]
        callBack.invoke(theme)
    }
}