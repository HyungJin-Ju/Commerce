package com.example.core.network

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AssetFileProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : FileProvider {

    override fun getJsonFromAsset(filePath: String): String? {
        return context.assets.open(filePath).bufferedReader().use { it.readText() }
    }
}
