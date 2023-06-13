package com.diplomski.mucnjak.coco.extensions

fun <T> List<T>.toArrayList() = ArrayList<T>().apply {
    addAll(this@toArrayList)
}