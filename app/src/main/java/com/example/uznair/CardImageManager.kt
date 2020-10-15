package com.example.uznair

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.widget.ImageView

object CardImageManager {
    lateinit var front_anim : AnimatorSet
    lateinit var back_anim : AnimatorSet

    var isFront : Boolean = true

    fun showCard(cardNumber: Int, cardImage: ImageView) {
        when (cardNumber) {
            1 -> {
                cardImage.setImageResource(R.drawable.card1)
            }
            2 -> {
                cardImage.setImageResource(R.drawable.card2)
            }
            3 -> {
                cardImage.setImageResource(R.drawable.card3)
            }
            4 -> {
                cardImage.setImageResource(R.drawable.card4)
            }
            5 -> {
                cardImage.setImageResource(R.drawable.card5)
            }
            6 -> {
                cardImage.setImageResource(R.drawable.card6)
            }
            7 -> {
                cardImage.setImageResource(R.drawable.card7)
            }
            8 -> {
                cardImage.setImageResource(R.drawable.card8)
            }
            9 -> {
                cardImage.setImageResource(R.drawable.card9)
            }
            10 -> {
                cardImage.setImageResource(R.drawable.card10)
            }
        }
    }

    fun animateCard(context: Context, correctCardImage: ImageView, backCardImage: ImageView) {
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
            front_anim.setTarget(backCardImage)
            back_anim.setTarget(correctCardImage)
            back_anim.start()
            front_anim.start()
            true
        }
    }
}