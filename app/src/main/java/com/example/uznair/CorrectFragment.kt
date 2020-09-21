package com.example.uznair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class CorrectFragment : Fragment() {

    lateinit var correctCard : TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_incorrect, container, false)
        correctCard = view.findViewById(R.id.correctCard)
        var correctNumber = (activity as GameActivity).newRandomNumber.toString()
        correctCard.text = correctNumber

        return view
    }
}