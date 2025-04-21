package kr.co.kurly.common.ext

import java.text.NumberFormat

fun Number.toNumberFormat(): String {
    return try {
        NumberFormat.getInstance().format(this)
    } catch (e: NumberFormatException) {
        "0"
    }
}