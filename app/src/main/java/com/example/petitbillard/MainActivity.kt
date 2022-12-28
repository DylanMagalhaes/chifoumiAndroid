package com.example.petitbillard

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var rockButton: Button
    lateinit var paperButton: Button
    lateinit var cutButton: Button

    lateinit var iaHandLabel: TextView
    lateinit var playerHandLabel: TextView
    lateinit var gameResultLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rockButton = findViewById(R.id.rockButton)
        paperButton = findViewById(R.id.paperButton)
        cutButton = findViewById(R.id.cutButton)
        iaHandLabel = findViewById(R.id.iaHandLabel)
        playerHandLabel = findViewById(R.id.playerHandLabel)
        gameResultLabel = findViewById(R.id.gameResultLabel)


        rockButton.setOnClickListener {
            onPlayHand(Hand.ROCK)
        }

        paperButton.setOnClickListener {
            onPlayHand(Hand.PAPER)
        }

        cutButton.setOnClickListener {
            onPlayHand(Hand.CUT)
        }
    }

    private fun onPlayHand(playerHand: Hand) {
        val iaHand = randomHand()

        showPlayerHand(playerHand)
        showIaHand(iaHand)
        val hasPlayerWon = hasPlayerWon(iaHand, playerHand)
        showGameResult(hasPlayerWon)
    }

    private fun showIaHand(hand: Hand) {
        iaHandLabel.text = hand.name
    }

    private fun showPlayerHand(hand: Hand) {
        playerHandLabel.text = hand.name
    }

    fun hasPlayerWon(iaHand: Hand, playerHand: Hand): Boolean? =
        when (playerHand) {
            Hand.ROCK ->
                when (iaHand) {
                    Hand.ROCK -> null
                    Hand.PAPER -> false
                    Hand.CUT -> true
                }

            Hand.PAPER ->
                when (iaHand) {
                    Hand.ROCK -> true
                    Hand.PAPER -> null
                    Hand.CUT -> false
                }

            Hand.CUT ->
                when (iaHand) {
                    Hand.ROCK -> false
                    Hand.PAPER -> true
                    Hand.CUT -> null
                }
        }


    private fun showGameResult(hasPlayerWon: Boolean?) {
        when (hasPlayerWon) {
            false -> {
                gameResultLabel.text = "You lost"
                gameResultLabel.setTextColor(Color.RED)
            }
            true -> {
                gameResultLabel.text = "You win"
                gameResultLabel.setTextColor(Color.GREEN)
            }
            null -> {
                gameResultLabel.text = "Draw"
                gameResultLabel.setTextColor(Color.BLACK)
            }
        }
    }

    private fun randomHand(): Hand = Hand.values().random()


}

