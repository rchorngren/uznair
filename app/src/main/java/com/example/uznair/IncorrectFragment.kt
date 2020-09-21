package com.example.uznair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class IncorrectFragment : Fragment() {

    lateinit var totalScore : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_incorrect, container, false)
        totalScore = view.findViewById(R.id.totalScoreView)
        var playerScore = (activity as GameActivity).playerScore.toString()
        totalScore.text = getString(R.string.total_score, playerScore)

        return view
    }

   /* fun gameOver() {
        var highScoreIntent = Intent(this, HighScoreActivity::class.java)
        val extras = Bundle()
        extras.putString("playerName", playerName)
        extras.putInt("playerScore", playerScore)
        highScoreIntent.putExtras(extras)
        startActivity(highScoreIntent)
    }
    */
}