package com.spyderrsh.cricut.tictactoeanalyzer

/**
 * State of the tic-tac-toe board with the values indexed as:
 *
 * 0 | 1 | 2
 * ---------
 * 3 | 4 | 5
 * ---------
 * 6 | 7 | 8
 */
class TicTacToeBoard(
    p0: GridValue = GridValue.EMPTY,
    p1: GridValue = GridValue.EMPTY,
    p2: GridValue = GridValue.EMPTY,
    p3: GridValue = GridValue.EMPTY,
    p4: GridValue = GridValue.EMPTY,
    p5: GridValue = GridValue.EMPTY,
    p6: GridValue = GridValue.EMPTY,
    p7: GridValue = GridValue.EMPTY,
    p8: GridValue = GridValue.EMPTY
) {
    private val state = arrayOf(p0, p1, p2, p3, p4, p5, p6, p7, p8)

    /**
     * Checks the given board to determine whether it has a winner.
     *
     * @return [GridValue.X] if "X"s are the winner, [GridValue.O] if "O"s are the winner, [GridValue.EMPTY]
     * if there is no winner
     */
    fun winner(): GridValue {
        // Since there aren't many combinations, we can manually iterate through all of them
        return checkWinner(0, 1, 2) ?: checkWinner(3, 4, 5) ?: checkWinner(6, 7, 8) ?: checkWinner(0, 3, 6)
        ?: checkWinner(1, 4, 7) ?: checkWinner(2, 5, 8) ?: checkWinner(0, 4, 8) ?: checkWinner(2, 4, 6)
        ?: GridValue.EMPTY
    }

    /**
     * Checks whether the given combination on the board has a winner
     * @return [GridValue.X] if "X"s are the winner, [GridValue.O] if "O"s are the winner,
     * `null` if there is no winner so other combinations can be tried in succession
     */
    private fun checkWinner(pos1: Int, pos2: Int, pos3: Int): GridValue? {
        return when (state[pos1] + state[pos2] + state[pos3]) {
            3 * GridValue.X.summationValue -> GridValue.X
            3 * GridValue.O.summationValue -> GridValue.O
            else -> null // return null so we keep trying
        }
    }
}

enum class GridValue(val summationValue: Int) {
    X(1),
    O(-1),
    EMPTY(0);

    operator fun plus(other: GridValue): Int = this.summationValue + other.summationValue
}

private operator fun Int.plus(other: GridValue): Int = this + other.summationValue
