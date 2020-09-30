package com.example.uznair

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_correct, container, false)
        var closeCorrectFragmentButton = view.findViewById<Button>(R.id.closeCorrectFragmentButton)

        closeCorrectFragmentButton.setOnClickListener {
            (activity as GameActivity).removeCorrectFragment()
            (activity as GameActivity).anotherGame()
        }

        correctCardImage = view.findViewById(R.id.correctFragmentCard)
        var cardNumber = (activity as GameActivity).newRandomNumber

        showCard(cardNumber)

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
    }
}