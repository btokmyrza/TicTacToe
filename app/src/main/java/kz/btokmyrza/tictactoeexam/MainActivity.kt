package kz.btokmyrza.tictactoeexam

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var gameActive = true

    // Player representation
    // 0 - X
    // 1 - O
    private var activePlayer = 0
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    // State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    // put all win positions in a 2D array
    private var winPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )
    var counter = 0

    lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        status = findViewById(R.id.tv_player_turn)
        status.text = "Ходит: крестик"

        findViewById<ImageButton>(R.id.imgbtn_top_left).setOnClickListener {
            playerTap(it)
        }

        findViewById<ImageButton>(R.id.imgbtn_top).setOnClickListener {
            playerTap(it)
        }

        findViewById<ImageButton>(R.id.imgbtn_top_right).setOnClickListener {
            playerTap(it)
        }

        findViewById<ImageButton>(R.id.imgbtn_left).setOnClickListener {
            playerTap(it)
        }

        findViewById<ImageButton>(R.id.imgbtn_center).setOnClickListener {
            playerTap(it)
        }

        findViewById<ImageButton>(R.id.imgbtn_right).setOnClickListener {
            playerTap(it)
        }

        findViewById<ImageButton>(R.id.imgbtn_bottom_left).setOnClickListener {
            playerTap(it)
        }

        findViewById<ImageButton>(R.id.imgbtn_bottom).setOnClickListener {
            playerTap(it)
        }

        findViewById<ImageButton>(R.id.imgbtn_bottom_right).setOnClickListener {
            playerTap(it)
        }

    }

    // this function will be called every time a
    // players tap in an empty box of the grid
    private fun playerTap(view: View) {
        val img: ImageButton = view as ImageButton
        val tappedImage: Int = img.tag.toString().toInt()

        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            resetGame()
        }

        // if the tapped image is empty
        if (gameState[tappedImage] == 2) {
            // increase the counter after every tap
            counter++

            // check if its the last box
            if (counter == 9) {
                // reset the game
                gameActive = false
            }

            // mark this position
            gameState[tappedImage] = activePlayer

            // change the active player from 0 to 1, or 1 to 0
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.btn_pressed_x)
                activePlayer = 1
                status.text = "Ходит: нолик"
            } else {
                img.setImageResource(R.drawable.btn_pressed_o)
                activePlayer = 0
                status.text = "Ходит: крестик"
            }
        }

        var flag = 0
        // Check if any player has won
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                flag = 1

                // Somebody has won! - Find out who!
                // game reset function be called
                gameActive = false
                val winnerStr: String = if (gameState[winPosition[0]] == 0) {
                    "крестик"
                } else {
                    "нолик"
                }

                GameResultDialogFragment(winnerStr).show(supportFragmentManager, null)
            }
        }
        // set the status if the match draw
        if (counter == 9 && flag == 0) {
            GameResultDialogFragment(null).show(supportFragmentManager, null)
        }
    }

    fun resetGame() {
        gameActive = true
        activePlayer = 0
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        // remove all the images from the boxes inside the grid
        (findViewById<View>(R.id.imgbtn_top_left) as ImageButton).setImageResource(0)
        (findViewById<View>(R.id.imgbtn_top) as ImageButton).setImageResource(0)
        (findViewById<View>(R.id.imgbtn_top_right) as ImageButton).setImageResource(0)
        (findViewById<View>(R.id.imgbtn_left) as ImageButton).setImageResource(0)
        (findViewById<View>(R.id.imgbtn_center) as ImageButton).setImageResource(0)
        (findViewById<View>(R.id.imgbtn_right) as ImageButton).setImageResource(0)
        (findViewById<View>(R.id.imgbtn_bottom_left) as ImageButton).setImageResource(0)
        (findViewById<View>(R.id.imgbtn_bottom) as ImageButton).setImageResource(0)
        (findViewById<View>(R.id.imgbtn_bottom_right) as ImageButton).setImageResource(0)
        status.text = "Ходит: крестик"
    }

}