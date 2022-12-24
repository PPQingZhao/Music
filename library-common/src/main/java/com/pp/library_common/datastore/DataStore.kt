package com.pp.library_base.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val USER_PREFERENCES = "user_preferences"
val Context.userDataStore by preferencesDataStore(name = USER_PREFERENCES)