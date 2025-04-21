package kr.co.kurly.common.model

import java.io.IOException

class CommonException @JvmOverloads constructor(
    val code: Int? = null,
    message: String? = null,
    cause: Throwable? = null,
) : IOException(null, cause) {
    override val message: String = message ?: "error=${cause?.localizedMessage}:code=${code}"
}