package com.spyderrsh.cricut.binarytree

/**
 * Interface describing a binary tree, or a tree composed of [BinaryNode]s
 * which each will have 0,1, or 2 children
 */
interface BinaryTree {
    /**
     * The root node of the tree or `null` if no root node is found
     */
    val root: BinaryNode?

    /**
     * Insert the given value into the tree
     */
    fun insert(value: Int)

    /**
     * Remove the given value from the tree
     *
     * @return true if value was removed, false if value was not found or could not be removed
     */
    fun remove(value: Int): Boolean

    /**
     * @return the count of nodes in this tree
     */
    fun count(): Int
}