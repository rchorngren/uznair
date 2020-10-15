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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_correct, container, false)

        correctCardImage = view.findViewById(R.id.correctFragmentCard)
        backCardImage = view.findViewById(R.id.backCorrectFragmentCard)

        var cardNumber = (activity as GameActivity).newRandomNumber
        CardImageManager.showCard(cardNumber, correctCardImage)

        val context = (activity as GameActivity).applicationContext
        CardImageManager.animateCard(context, correctCardImage, backCardImage)

        var closeCorrectFragmentButton = view.findViewById<Button>(R.id.closeCorrectFragmentButton)

        closeCorrectFragmentButton.setOnClickListener {
            (activity as GameActivity).removeCorrectFragment()
            (activity as GameActivity).anotherGame()
        }
        return view
    }

}