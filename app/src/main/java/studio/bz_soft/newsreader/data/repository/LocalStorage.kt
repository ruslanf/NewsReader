package studio.bz_soft.newsreader.data.repository

import android.content.SharedPreferences

class LocalStorage(
    private val preferences: SharedPreferences
) : LocalStorageInterface