package com.example.uznair

import android.widget.ImageView

object CardImageManager {

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
}