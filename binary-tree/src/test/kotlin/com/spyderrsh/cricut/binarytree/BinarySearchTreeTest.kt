package com.spyderrsh.cricut.binarytree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {

    lateinit var underTest: BinarySearchTree

    @BeforeEach
    fun setup() {
        underTest = BinarySearchTree()
    }

    @Test
    fun `root is null before adding any values`() {
        assertNull(underTest.root)
    }

    @Test
    fun `first insert sets root to non-null`() {
        underTest.insert(10)
        assertNotNull(underTest.root)
        assertEquals(10, underTest.root!!.value)
        assertEquals(1, underTest.count())
    }

    @Test
    fun `inserts smaller values left and larger values right`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(12)

        // Expected:
        //   10
        //  /  \
        // 8    12

        assertEquals(10, underTest.root!!.value)
        assertEquals(8, underTest.root!!.left!!.value)
        assertEquals(12, underTest.root!!.right!!.value)
        assertEquals(3, underTest.count())
    }

    @Test
    fun `inserts values to subtrees`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(12)
        underTest.insert(9)
        underTest.insert(11)
        underTest.insert(7)
        underTest.insert(13)

        // Expected:
        //     10
        //    /  \
        //   8    12
        //  / \  /  \
        // 7   9 11  13

        assertEquals(10, underTest.root!!.value)
        assertEquals(8, underTest.root!!.left!!.value)
        assertEquals(12, underTest.root!!.right!!.value)
        assertEquals(9, underTest.root!!.left!!.right!!.value)
        assertEquals(11, underTest.root!!.right!!.left!!.value)
        assertEquals(7, underTest.root!!.left!!.left!!.value)
        assertEquals(13, underTest.root!!.right!!.right!!.value)
        assertEquals(7, underTest.count())
    }

    @Test
    fun `Remove Nodes with no children`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(12)
        underTest.insert(9)
        underTest.insert(11)
        underTest.insert(7)
        underTest.insert(13)

        // Expected:
        //     10
        //    /  \
        //   8    12
        //  / \  /  \
        // 7   9 11  13

        assertTrue(underTest.remove(7))

        // Expected:
        //     10
        //    /  \
        //   8    12
        //    \  /  \
        //     9 11  13

        assertEquals(10, underTest.root!!.value)
        assertEquals(8, underTest.root!!.left!!.value)
        assertEquals(12, underTest.root!!.right!!.value)
        assertEquals(9, underTest.root!!.left!!.right!!.value)
        assertEquals(11, underTest.root!!.right!!.left!!.value)
        assertNull(underTest.root!!.left!!.left)
        assertEquals(13, underTest.root!!.right!!.right!!.value)
        assertEquals(6, underTest.count())

        assertTrue(underTest.remove(9))
        assertTrue(underTest.remove(11))
        // Double check that we can't remove a non-existent node
        assertFalse(underTest.remove(7))
        assertTrue(underTest.remove(13))

        // Expected:
        //   10
        //  /  \
        // 8    12

        assertEquals(10, underTest.root!!.value)
        assertEquals(8, underTest.root!!.left!!.value)
        assertEquals(12, underTest.root!!.right!!.value)
        assertNull(underTest.root!!.left!!.left)
        assertNull(underTest.root!!.left!!.right)
        assertNull(underTest.root!!.right!!.left)
        assertNull(underTest.root!!.right!!.right)
        assertEquals(3, underTest.count())

        assertTrue(underTest.remove(8))
        assertTrue(underTest.remove(12))

        // Expected:
        //    10

        assertEquals(10, underTest.root!!.value)
        assertNull(underTest.root!!.left)
        assertNull(underTest.root!!.right)
        assertEquals(1, underTest.count())

    }

    @Test
    fun `Remove root node`() {
        underTest.insert(1)
        assertEquals(1, underTest.root!!.value)

        assertEquals(1, underTest.count())

        underTest.remove(1)
        assertNull(underTest.root)
        assertEquals(0, underTest.count())
    }

    @Test
    fun `Remove node with one child on right`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(12)
        underTest.insert(9)
        underTest.insert(11)
        underTest.insert(13)

        // Expected:
        //     10
        //    /  \
        //   8    12
        //    \  /  \
        //     9 11  13
        //
        // Remove 8

        underTest.remove(8)

        // Expected:
        //     10
        //    /  \
        //   9    12
        //       /  \
        //       11  13

        assertEquals(9, underTest.root!!.left!!.value)
        assertEquals(5, underTest.count())
    }

    @Test
    fun `Remove node with one child on left`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(12)
        underTest.insert(11)
        underTest.insert(7)
        underTest.insert(13)

        // Expected:
        //     10
        //    /  \
        //   8    12
        //  /    /  \
        // 7     11  13
        //
        // Remove 8

        underTest.remove(8)

        // Expected:
        //     10
        //    /  \
        //   7    12
        //       /  \
        //       11  13

        // Expected:
        //     10
        //    /  \
        //   9    12
        //       /  \
        //       11  13

        assertEquals(7, underTest.root!!.left!!.value)
        assertEquals(5, underTest.count())
    }

    @Test
    fun `Remove root with single child on right`() {
        underTest.insert(10)
        underTest.insert(12)
        underTest.insert(11)
        underTest.insert(13)

        // Expected:
        //     10
        //       \
        //        12
        //       /  \
        //       11  13
        // Remove 10

        underTest.remove(10)

        // Expected
        //    12
        //   /  \
        //  11  13

        assertEquals(12, underTest.root!!.value)
        assertEquals(11, underTest.root!!.left!!.value)
        assertEquals(13, underTest.root!!.right!!.value)
        assertEquals(3, underTest.count())
    }

    @Test
    fun `Remove root with single child on left`() {

        underTest.insert(4)
        underTest.insert(2)
        underTest.insert(1)
        underTest.insert(3)

        // Expected:
        //     4
        //    /
        //   2
        //  / \
        // 1   3
        // Remove 4

        underTest.remove(4)

        // Expected
        //   2
        //  / \
        // 1   3

        assertEquals(2, underTest.root!!.value)
        assertEquals(1, underTest.root!!.left!!.value)
        assertEquals(3, underTest.root!!.right!!.value)
        assertEquals(3, underTest.count())
    }

    @Test
    fun `Remove root with two children`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(12)
        underTest.insert(9)
        underTest.insert(11)
        underTest.insert(7)
        underTest.insert(13)

        // Expected:
        //     10
        //    /  \
        //   8    12
        //  / \  /  \
        // 7   9 11  13

        underTest.remove(10)

        // Expected:
        //     11
        //    /  \
        //   8    12
        //  / \     \
        // 7   9     13

        assertEquals(11, underTest.root!!.value)
        assertEquals(8, underTest.root!!.left!!.value)
        assertEquals(12, underTest.root!!.right!!.value)
        assertEquals(13, underTest.root!!.right!!.right!!.value)
        assertNull(underTest.root!!.right!!.left)
        assertEquals(6, underTest.count())
    }

    @Test
    fun `Remove root with two children and predecessor has children`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(7)
        underTest.insert(9)
        underTest.insert(15)
        underTest.insert(11)
        underTest.insert(16)
        underTest.insert(13)
        underTest.insert(12)
        underTest.insert(14)

        // Expected:
        //     10
        //    /  \
        //   8    15
        //  / \  /  \
        // 7   9 11  16
        //        \
        //        13
        //       /  \
        //      12  14

        underTest.remove(10)

        // Expected:
        //     11
        //    /   \
        //   8     15
        //  / \   /  \
        // 7   9 13  16
        //       / \
        //      12 14

        assertEquals(11, underTest.root!!.value)
        assertEquals(8, underTest.root!!.left!!.value)
        assertEquals(15, underTest.root!!.right!!.value)
        assertEquals(16, underTest.root!!.right!!.right!!.value)
        assertEquals(13, underTest.root!!.right!!.left!!.value)
        assertEquals(12, underTest.root!!.right!!.left!!.left!!.value)
        assertEquals(14, underTest.root!!.right!!.left!!.right!!.value)
        assertEquals(9, underTest.count())
    }

    @Test
    fun `Remove right with two children and predecessor has children`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(7)
        underTest.insert(9)
        underTest.insert(12)
        underTest.insert(11)
        underTest.insert(18)
        underTest.insert(13)
        underTest.insert(16)
        underTest.insert(15)
        underTest.insert(17)
        underTest.insert(20)

        // Expected:
        //     10
        //    /  \
        //   8    12
        //  / \  /  \
        // 7   9 11  18
        //          /  \
        //        13    20
        //          \
        //          16
        //         /  \
        //       15    17

        underTest.remove(12)


        // Expected:
        //     10
        //    /  \
        //   8    13
        //  / \  /  \
        // 7   9 11  18
        //          /  \
        //         16   20
        //        /  \
        //      15    17

        assertEquals(10, underTest.root!!.value)
        assertEquals(8, underTest.root!!.left!!.value)
        assertEquals(13, underTest.root!!.right!!.value)
        assertEquals(18, underTest.root!!.right!!.right!!.value)
        assertEquals(11, underTest.root!!.right!!.left!!.value)
        assertEquals(16, underTest.root!!.right!!.right!!.left!!.value)
        assertEquals(20, underTest.root!!.right!!.right!!.right!!.value)
        assertEquals(15, underTest.root!!.right!!.right!!.left!!.left!!.value)
        assertEquals(17, underTest.root!!.right!!.right!!.left!!.right!!.value)
        assertEquals(11, underTest.count())
    }

    @Test
    fun `Remove left with two children and predecessor has children`() {
        underTest.insert(10)
        underTest.insert(12)
        underTest.insert(11)
        underTest.insert(13)
        underTest.insert(2)
        underTest.insert(1)
        underTest.insert(9)
        underTest.insert(5)
        underTest.insert(7)
        underTest.insert(6)
        underTest.insert(8)

        // Expected:
        //     10
        //    /  \
        //   2    12
        //  / \  /  \
        // 1   9 11  13
        //    /
        //   5
        //    \
        //     7
        //    / \
        //   6   8

        underTest.remove(2)


        // Expected:
        //     10
        //    /  \
        //   5    12
        //  / \  /  \
        // 1   9 11  13
        //    /
        //   7
        //  / \
        // 6   8

        assertEquals(10, underTest.root!!.value)
        assertEquals(12, underTest.root!!.right!!.value)
        assertEquals(5, underTest.root!!.left!!.value)
        assertEquals(1, underTest.root!!.left!!.left!!.value)
        assertEquals(9, underTest.root!!.left!!.right!!.value)
        assertEquals(7, underTest.root!!.left!!.right!!.left!!.value)
        assertEquals(6, underTest.root!!.left!!.right!!.left!!.left!!.value)
        assertEquals(8, underTest.root!!.left!!.right!!.left!!.right!!.value)
        assertEquals(10, underTest.count())
    }

    @Test
    fun `Remove right with two children and predecessor is direct descendant and has children`() {
        underTest.insert(10)
        underTest.insert(8)
        underTest.insert(7)
        underTest.insert(9)
        underTest.insert(12)
        underTest.insert(11)
        underTest.insert(13)
        underTest.insert(16)
        underTest.insert(15)
        underTest.insert(17)

        // Expected:
        //     10
        //    /  \
        //   8    12
        //  / \  /  \
        // 7   9 11  13
        //             \
        //             16
        //            /  \
        //          15    17

        underTest.remove(12)


        // Expected:
        //     10
        //    /  \
        //   8    13
        //  / \  /  \
        // 7   9 11 16
        //         /  \
        //       15    17
        //
        //

        assertEquals(10, underTest.root!!.value)
        assertEquals(8, underTest.root!!.left!!.value)
        assertEquals(13, underTest.root!!.right!!.value)
        assertEquals(16, underTest.root!!.right!!.right!!.value)
        assertEquals(11, underTest.root!!.right!!.left!!.value)
        assertEquals(15, underTest.root!!.right!!.right!!.left!!.value)
        assertEquals(17, underTest.root!!.right!!.right!!.right!!.value)
        assertEquals(9, underTest.count())
    }

    @Test
    fun `Remove left with two children and predecessor is direct descendant and has children`() {
        underTest.insert(10)
        underTest.insert(12)
        underTest.insert(11)
        underTest.insert(13)
        underTest.insert(5)
        underTest.insert(1)
        underTest.insert(6)
        underTest.insert(8)
        underTest.insert(7)
        underTest.insert(9)

        // Expected:
        //     10
        //    /  \
        //   5    12
        //  / \  /  \
        // 1   6 11  13
        //      \
        //       8
        //      / \
        //     7   9

        underTest.remove(5)


        // Expected:
        //     10
        //    /  \
        //   6    12
        //  / \  /  \
        // 1   8 11  13
        //    / \
        //   7   9

        assertEquals(10, underTest.root!!.value)
        assertEquals(12, underTest.root!!.right!!.value)
        assertEquals(6, underTest.root!!.left!!.value)
        assertEquals(1, underTest.root!!.left!!.left!!.value)
        assertEquals(8, underTest.root!!.left!!.right!!.value)
        assertEquals(7, underTest.root!!.left!!.right!!.left!!.value)
        assertEquals(9, underTest.root!!.left!!.right!!.right!!.value)
        assertEquals(9, underTest.count())
    }
}