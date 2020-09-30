package com.example.uznair

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class IncorrectFragment : Fragment() {

    lateinit var totalScore : TextView
    lateinit var newCard : TextView
    lateinit var highScoreButton : Button
    lateinit var incorrectCardImage : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_incorrect, container, false)
        totalScore = view.findViewById(R.id.totalScoreView)
        newCard = view.findViewById(R.id.newCard)

        incorrectCardImage = view.findViewById(R.id.incorrectFragmentCard)
        var cardNumber = (activity as GameActivity).newRandomNumber

        showCard(cardNumber)

        var playerScore = (activity as GameActivity).playerScore.toString()

        totalScore.text = getString(R.string.total_score, playerScore)
        newCard.text = (activity as GameActivity).newRandomNumber.toString()

        highScoreButton = view.findViewById(R.id.gameOverHighScoreButton)

        highScoreButton.setOnClickListener {
            (activity as GameActivity).goToHighScore()
        }

        return view
    }

    fun showCard(cardNumber : Int) {
        when (cardNumber) {
            1 -> {
                incorrectCardImage.setImageResource(R.drawable.card1)
            }
            2 -> {
                incorrectCardImage.setImageResource(R.drawable.card2)
            }
            3 -> {
                incorrectCardImage.setImageResource(R.drawable.card3)
            }
            4 -> {
                incorrectCardImage.setImageResource(R.drawable.card4)
            }
            5 -> {
                incorrectCardImage.setImageResource(R.drawable.card5)
            }
            6 -> {
                incorrectCardImage.setImageResource(R.drawable.card6)
            }
            7 -> {
                incorrectCardImage.setImageResource(R.drawable.card7)
            }
            8 -> {
                incorrectCardImage.setImageResource(R.drawable.card8)
            }
            9 -> {
                incorrectCardImage.setImageResource(R.drawable.card9)
            }
            10 -> {
                incorrectCardImage.setImageResource(R.drawable.card10)
            }
        }
    }

}