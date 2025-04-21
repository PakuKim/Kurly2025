package kr.co.kurly.common.util

interface Mapper<LEFT, RIGHT> {
    fun mapToRight(from: LEFT): RIGHT
}