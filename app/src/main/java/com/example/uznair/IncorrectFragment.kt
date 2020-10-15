package com.example.uznair

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
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
    lateinit var highScoreButton : Button
    lateinit var incorrectCardImage : ImageView
    lateinit var backCardImage : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_incorrect, container, false)
        totalScore = view.findViewById(R.id.totalScoreView)

        incorrectCardImage = view.findViewById(R.id.incorrectFragmentCard)
        backCardImage = view.findViewById(R.id.backIncorrectFragmentCard)

        var cardNumber = (activity as GameActivity).newRandomNumber
        CardImageManager.showCard(cardNumber, incorrectCardImage)

        val context = (activity as GameActivity).applicationContext
        CardImageManager.animateCard(context, incorrectCardImage, backCardImage)

        var playerScore = (activity as GameActivity).playerScore.toString()

        totalScore.text = getString(R.string.total_score, playerScore)

        highScoreButton = view.findViewById(R.id.gameOverHighScoreButton)

        highScoreButton.setOnClickListener {
            (activity as GameActivity).goToHighScore()
        }

        return view
    }

}