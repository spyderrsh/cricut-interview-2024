package com.spyderrsh.cricut.binarytree

interface BinaryTree {
    val root: BinaryNode?

    fun insert(value: Int)

    fun remove(value: Int): Boolean

    fun count(): Int
}