package com.spyderrsh.cricut.tictactoeanalyzer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

val X = GridValue.X
val O = GridValue.O
val E = GridValue.EMPTY

class TicTacToeBoardTest {
    @Test
    fun `Empty Board`() {
        assertEquals(
            E,
            TicTacToeBoard(
                E, E, E,
                E, E, E,
                E, E, E
            ).winner()
        )
    }

    @Test
    fun `Full Board No Winners`() {
        assertEquals(
            E,
            TicTacToeBoard(
                X, O, X,
                O, X, O,
                O, X, O
            ).winner()
        )
    }

    @Test
    fun `X All Wins possible with empty spaces`() {
        // Horizontal
        assertEquals(
            X,
            TicTacToeBoard(
                X, X, X,
                E, E, E,
                E, E, E
            ).winner()
        )
        assertEquals(
            X,
            TicTacToeBoard(
                E, E, E,
                X, X, X,
                E, E, E
            ).winner()
        )
        assertEquals(
            X,
            TicTacToeBoard(
                E, E, E,
                E, E, E,
                X, X, X
            ).winner()
        )

        // Vertical
        assertEquals(
            X,
            TicTacToeBoard(
                X, E, E,
                X, E, E,
                X, E, E
            ).winner()
        )
        assertEquals(
            X,
            TicTacToeBoard(
                E, X, E,
                E, X, E,
                E, X, E
            ).winner()
        )
        assertEquals(
            X,
            TicTacToeBoard(
                E, E, X,
                E, E, X,
                E, E, X
            ).winner()
        )

        // Diagonals
        assertEquals(
            X,
            TicTacToeBoard(
                X, E, E,
                E, X, E,
                E, E, X
            ).winner()
        )
        assertEquals(
            X,
            TicTacToeBoard(
                E, E, X,
                E, X, E,
                X, E, E
            ).winner()
        )
    }

    @Test
    fun `O All Wins possible with empty spaces`() {
        // Horizontal
        assertEquals(
            O,
            TicTacToeBoard(
                O, O, O,
                E, E, E,
                E, E, E
            ).winner()
        )
        assertEquals(
            O,
            TicTacToeBoard(
                E, E, E,
                O, O, O,
                E, E, E
            ).winner()
        )
        assertEquals(
            O,
            TicTacToeBoard(
                E, E, E,
                E, E, E,
                O, O, O
            ).winner()
        )

        // Vertical
        assertEquals(
            O,
            TicTacToeBoard(
                O, E, E,
                O, E, E,
                O, E, E
            ).winner()
        )
        assertEquals(
            O,
            TicTacToeBoard(
                E, O, E,
                E, O, E,
                E, O, E
            ).winner()
        )
        assertEquals(
            O,
            TicTacToeBoard(
                E, E, O,
                E, E, O,
                E, E, O
            ).winner()
        )

        // Diagonals
        assertEquals(
            O,
            TicTacToeBoard(
                O, E, E,
                E, O, E,
                E, E, O
            ).winner()
        )
        assertEquals(
            O,
            TicTacToeBoard(
                E, E, O,
                E, O, E,
                O, E, E
            ).winner()
        )
    }

    @Test
    fun `Mixed Games have correct winner`() {
        assertEquals(
            X,
            TicTacToeBoard(
                X, O, X,
                O, X, E,
                X, E, O
            ).winner()
        )

        assertEquals(
            X,
            TicTacToeBoard(
                X, O, X,
                E, O, X,
                O, E, X
            ).winner()
        )

        assertEquals(
            O,
            TicTacToeBoard(
                X, O, X,
                O, O, X,
                X, O, E
            ).winner()
        )

        assertEquals(
            O,
            TicTacToeBoard(
                X, O, O,
                X, O, X,
                O, E, E
            ).winner()
        )
    }
}