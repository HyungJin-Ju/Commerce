package com.example.core.network

internal interface FileProvider {
    fun getJsonFromAsset(filePath: String): String?
}
