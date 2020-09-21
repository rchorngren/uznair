package com.example.uznair

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class IncorrectFragment : Fragment() {

    lateinit var totalScore : TextView
    // lateinit var highScoreButton : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_incorrect, container, false)
        totalScore = view.findViewById(R.id.totalScoreView)
        var playerScore = (activity as GameActivity).playerScore.toString()
        totalScore.text = getString(R.string.total_score, playerScore)

        var highScoreButton = view.findViewById<Button>(R.id.gameOverHighScoreButton)

        highScoreButton.setOnClickListener {
            (activity as GameActivity).goToHighScore()
        }

        return view
    }


}