package kr.co.kurly.mockserver.core

internal interface FileProvider {
    fun getJsonFromAsset(filePath: String): String?
}
