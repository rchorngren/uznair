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
    lateinit var front_anim : AnimatorSet
    lateinit var back_anim : AnimatorSet
    var isFront : Boolean = true

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
        val scale = context.resources.displayMetrics.density
        incorrectCardImage.cameraDistance = 8000 * scale
        backCardImage.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(context, R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(context, R.animator.back_animator) as AnimatorSet

        isFront = if(isFront){
            front_anim.setTarget(backCardImage)
            back_anim.setTarget(incorrectCardImage)
            front_anim.start()
            back_anim.start()
            false
        } else {
            front_anim.setTarget(incorrectCardImage)
            back_anim.setTarget(backCardImage)
            back_anim.start()
            front_anim.start()
            true
        }

        var playerScore = (activity as GameActivity).playerScore.toString()

        totalScore.text = getString(R.string.total_score, playerScore)

        highScoreButton = view.findViewById(R.id.gameOverHighScoreButton)

        highScoreButton.setOnClickListener {
            (activity as GameActivity).goToHighScore()
        }

        return view
    }

   /* fun showCard(cardNumber : Int) {
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
    */

}