package com.example.uznair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_correct.*

class CorrectFragment : Fragment() {

    lateinit var correctCard : TextView

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

        correctCard = view.findViewById(R.id.correctCard)
        correctCard.text = (activity as GameActivity).newRandomNumber.toString()

        return view
    }
}