package com.spyderrsh.cricut.binarytree

data class BinaryNode(val value: Int, var left: BinaryNode? = null, var right: BinaryNode? = null) {
    operator fun compareTo(other: BinaryNode): Int = value.compareTo(other.value)
    operator fun compareTo(other: Int): Int = value.compareTo(other)
}

fun BinaryNode?.count(): Int = this?.run { 1 + left.count() + right.count() } ?: 0


