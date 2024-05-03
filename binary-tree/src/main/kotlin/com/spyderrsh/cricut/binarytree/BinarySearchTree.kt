package com.spyderrsh.cricut.binarytree

/**
 * An ordered (but unbalanced) binary tree that can be searched quickly to find if the given value is present
 * using [contains]
 */
class BinarySearchTree : BinaryTree {
    override var root: BinaryNode? = null
        private set

    private fun insert(binaryNode: BinaryNode) {
        assert(binaryNode.left == null && binaryNode.right == null)
        root?.also { it.insert(binaryNode) } ?: run { root = binaryNode }
    }

    override fun insert(value: Int) {
        insert(BinaryNode(value))
    }

    override fun remove(value: Int): Boolean {
        return root?.remove(value) ?: false
    }

    override fun count(): Int {
        return root.count()
    }

    /**
     * @return true if the given value is present in the tree, false otherwise
     */
    fun contains(value: Int): Boolean = root.contains(value)

    private fun BinaryNode.remove(value: Int, parent: BinaryNode? = null): Boolean {
        // Check if this node is being removed
        if (this.value == value) {
            // When I'm deleted, who do I update
            val setter: (BinaryNode?) -> Unit = when {
                this === root -> { it: BinaryNode? -> root = it }
                this === parent?.left -> { it: BinaryNode? -> parent.left = it }
                this === parent?.right -> { it: BinaryNode? -> parent.right = it }
                else -> throw IllegalStateException("Node is not related to parent and is not root")
            }
            when {
                // Case 1: Leaf node (No Children) remove!
                left == null && right == null -> setter(null)
                // Case 2: Single child, adopt children and remove
                left == null || right == null -> {
                    setter(left ?: right) // Take whichever child is non-null
                }
                // Case 3: Both children are not null find successor and use to replace deleted node
                else -> {
                    val successor = findRightSuccessorAndParent()
                    // If successor is not a child of this, we give it's right children to its parent
                    // and give this node's right tree to the successor
                    if (successor.parent !== this) {
                        successor.parent.left = successor.node.right
                        successor.node.right = this.right
                    }
                    setter(successor.node)
                    // We always give the successor the left children
                    successor.node.left = this.left
                }
            }
            return true
        }
        return if (this < value) {
            right?.remove(value, this) ?: false
        } else {
            left?.remove(value, this) ?: false
        }
    }

    private fun BinaryNode.insert(value: BinaryNode) {
        if (value > this) {
            right?.insert(value) ?: run { right = value }
        } else {
            left?.insert(value) ?: run { left = value }
        }
    }

    private fun BinaryNode?.contains(value: Int): Boolean {
        return when {
            this == null -> false
            this.value == value -> true
            this.value < value -> right.contains(value)
            else -> left.contains(value)
        }
    }

    /**
     * Finds the successor but only examining the right subtree. The successor, in this case,
     * simplifies to the minimum (left-most) node in this tree.
     *
     * This function is assumed to be called from a target site where the right
     * child has already been determined to be non-null
     */
    private fun BinaryNode.findRightSuccessorAndParent(): Successor {
        var successor = requireNotNull(this.right)
        var parent = this
        while (successor.left != null) {
            parent = successor
            successor = successor.left!! // Not thread-safe
        }
        return Successor(successor, parent)
    }

    data class Successor(val node: BinaryNode, val parent: BinaryNode)
}