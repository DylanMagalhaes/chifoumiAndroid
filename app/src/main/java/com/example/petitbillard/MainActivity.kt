package com.example.petitbillard

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var rockButton: Button
    private lateinit var paperButton: Button
    private lateinit var cutButton: Button

    private lateinit var iaHandImage: ImageView
    private lateinit var playerHandImage: ImageView
    private lateinit var gameResultLabel: TextView
    private lateinit var iaScoreLabel: TextView
    private lateinit var playerScoreLabel: TextView

    private var iaScore = 0
    private var playerScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rockButton = findViewById(R.id.rockButton)
        paperButton = findViewById(R.id.paperButton)
        cutButton = findViewById(R.id.cutButton)
        iaHandImage = findViewById(R.id.iaHandImage)
        playerHandImage = findViewById(R.id.playerHandImage)
        gameResultLabel = findViewById(R.id.gameResultLabel)
        iaScoreLabel = findViewById(R.id.iaScore)
        playerScoreLabel = findViewById((R.id.playerScore))


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
        updateScoreCount(hasPlayerWon)
    }

    private fun updateScoreCount(hasPlayerWon: Boolean?) {

        when (hasPlayerWon) {
            null -> Unit
            true -> {
                playerScore++
                playerScoreLabel.text = "$playerScore"
            }
            false -> {
                iaScore++
                iaScoreLabel.text = "$iaScore"
            }
        }
    }

    private fun showIaHand(hand: Hand) {
        val drawableRes = when (hand) {
            Hand.ROCK -> R.drawable.ic_rock
            Hand.PAPER -> R.drawable.ic_paper
            Hand.CUT -> R.drawable.ic_scissors
        }

        val image = ContextCompat.getDrawable(this, drawableRes)

        iaHandImage.setImageDrawable(image)
    }

    private fun showPlayerHand(hand: Hand) {
        val drawableRes = when (hand) {
            Hand.ROCK -> R.drawable.ic_rock
            Hand.PAPER -> R.drawable.ic_paper
            Hand.CUT -> R.drawable.ic_scissors
        }

        val image = ContextCompat.getDrawable(this, drawableRes)

        playerHandImage.setImageDrawable(image)
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

