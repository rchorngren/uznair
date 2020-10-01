package com.example.uznair

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_correct.*

class CorrectFragment : Fragment() {

    lateinit var correctCardImage : ImageView
    lateinit var backCardImage : ImageView
    lateinit var front_anim : AnimatorSet
    lateinit var back_anim : AnimatorSet
    var isFront : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_correct, container, false)

        correctCardImage = view.findViewById(R.id.correctFragmentCard)
        backCardImage = view.findViewById(R.id.backCorrectFragmentCard)
        var cardNumber = (activity as GameActivity).newRandomNumber

        showCard(cardNumber)

        var closeCorrectFragmentButton = view.findViewById<Button>(R.id.closeCorrectFragmentButton)

        closeCorrectFragmentButton.setOnClickListener {
            (activity as GameActivity).removeCorrectFragment()
            (activity as GameActivity).anotherGame()
        }
        return view
    }

    fun showCard(cardNumber : Int) {
        when (cardNumber) {
            1 -> {
                correctCardImage.setImageResource(R.drawable.card1)
            }
            2 -> {
                correctCardImage.setImageResource(R.drawable.card2)
            }
            3 -> {
                correctCardImage.setImageResource(R.drawable.card3)
            }
            4 -> {
                correctCardImage.setImageResource(R.drawable.card4)
            }
            5 -> {
                correctCardImage.setImageResource(R.drawable.card5)
            }
            6 -> {
                correctCardImage.setImageResource(R.drawable.card6)
            }
            7 -> {
                correctCardImage.setImageResource(R.drawable.card7)
            }
            8 -> {
                correctCardImage.setImageResource(R.drawable.card8)
            }
            9 -> {
                correctCardImage.setImageResource(R.drawable.card9)
            }
            10 -> {
                correctCardImage.setImageResource(R.drawable.card10)
            }
        }
        val context = (activity as GameActivity).applicationContext
        val scale = context.resources.displayMetrics.density
        correctCardImage.cameraDistance = 8000 * scale
        backCardImage.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(context, R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(context, R.animator.back_animator) as AnimatorSet

        isFront = if(isFront){
            front_anim.setTarget(backCardImage)
            back_anim.setTarget(correctCardImage)
            front_anim.start()
            back_anim.start()
            false
        } else {
            front_anim.setTarget(correctCardImage)
            back_anim.setTarget(backCardImage)
            back_anim.start()
            front_anim.start()
            true
        }
    }
}