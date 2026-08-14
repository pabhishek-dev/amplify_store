package com.abhi.amplify_store.extensions

fun String.limit(count: Int): String {
    val words = this.trim().split("[\\s-]+".toRegex()).filter { it.isNotEmpty() }
    return if (words.size > count) {
        words.take(count).joinToString(" ") + "…"
    } else {
        this
    }
}