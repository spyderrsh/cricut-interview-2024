package com.spyderrsh.cricut.binarytree

/**
 * Represents a node of a binary tree with a given value and references to left and right children
 * nodes if they are present.
 *
 * Note: for the sake of this exercise, I left the nodes mutable, but it may be worthwhile to hide their
 * mutability
 */
data class BinaryNode(val value: Int, var left: BinaryNode? = null, var right: BinaryNode? = null) {
    // Helper functions for comparing nodes
    operator fun compareTo(other: BinaryNode): Int = value.compareTo(other.value)
    operator fun compareTo(other: Int): Int = value.compareTo(other)
}

/**
 * Recursively count the number of children a node has.
 *
 * @return 0 if this node is `null`, or the count of this node and its children
 */
fun BinaryNode?.count(): Int = this?.run { 1 + left.count() + right.count() } ?: 0


