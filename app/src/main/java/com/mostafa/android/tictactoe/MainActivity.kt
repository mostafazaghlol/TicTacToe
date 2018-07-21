package com.mostafa.android.tictactoe

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var gameBoard: Array<CharArray> = Array(3) {
        kotlin.CharArray(3)
    }
    var turn = 'X'
    var tableLayout: TableLayout? = null
    var turnTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        turnTextView = findViewById(R.id.turnTextView)
        tableLayout = findViewById(R.id.tablelayout)
        startnewGame(true)


    }

    private fun startnewGame(setClickListener: Boolean) {
        turn = 'X'
        turnTextView?.text = String.format(resources.getString(R.string.turn), turn)
        for (i in 0 until gameBoard.size) {
            for (j in 0 until gameBoard[i].size) {
                gameBoard[i][j] = ' '
                val cell = (tableLayout?.getChildAt(i) as
                        TableRow).getChildAt(j) as TextView
                cell.text = ""
                if (setClickListener) {
                    cell.setOnClickListener { cellClickListener(i, j) }
                }

            }
        }
    }

    private fun isBoardFull(Board: Array<CharArray>): Boolean {
        for (i in 0 until Board.size) {
            for (j in 0 until Board[i].size) {
                if (Board[i][j] == ' ') {
                    return false
                }
            }
        }
        return true
    }

    private fun isWinner(Board: Array<CharArray>, w: Char): Boolean {
        for (i in 0 until gameBoard.size) {
            if (gameBoard[i][0] == w && gameBoard[i][1] == w &&
                    gameBoard[i][2] == w) {
                return true
            }
            if (gameBoard[0][i] == w && gameBoard[1][i] == w &&
                    gameBoard[2][i] == w) {
                return true
            }
        }
        if ((gameBoard[0][0] == w && gameBoard[1][1] == w && gameBoard[2]
                [2] == w) ||
                (gameBoard[0][2] == w && gameBoard[1][1] == w &&
                        gameBoard[2][0] == w)) {
            return true
        }
        return false
    }

    private fun checkGameStatus() {
        var state: String? = null
        if (isWinner(gameBoard, 'X')) {
            state = String.format(resources.getString(R.string.winner), 'X')
        } else if (isWinner(gameBoard, 'O')) {
            state = String.format(resources.getString(R.string.winner), 'O')
        } else {
            if (isBoardFull(gameBoard)) {
                state = resources.getString(R.string.draw)
            }
        }
        if (state != null) {
            turnTextView?.text = state
            val builder = AlertDialog.Builder(this)
            builder.setMessage(state)
            builder.setPositiveButton(android.R.string.ok, { dialog, id ->
                startnewGame(false)
            })
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun cellClickListener(row: Int, column: Int) {
        gameBoard[row][column] = turn
        ((tableLayout?.getChildAt(row) as TableRow).getChildAt(column) as TextView).text =
                turn.toString()
        turn = if ('X' == turn) 'O' else 'X'
        turnTextView?.text = String.format(resources.getString(R.string.turn), turn)
        checkGameStatus()
    }

}
