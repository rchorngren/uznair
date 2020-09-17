package com.example.uznair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    lateinit var nameTest : TextView
    lateinit var answer : TextView
    lateinit var newNumber : TextView
    var initialRandomNumber : Int = (1..10).random()
    var newRandomNumber : Int = (1..10).random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var playerName = intent.getStringExtra("playerName")
        var highButton = findViewById<Button>(R.id.upButton)
        var lowButton = findViewById<Button>(R.id.downButton)

        highButton.setOnClickListener {
            guessHigher()
        }

        lowButton.setOnClickListener {
            guessLower()
        }

        nameTest = findViewById(R.id.nameTest)

        nameTest.text = initialRandomNumber.toString()
        answer = findViewById(R.id.answerText)
        newNumber = findViewById(R.id.newNumber)
    }

    fun guessHigher() {

        if(initialRandomNumber > newRandomNumber) {
            answer.text = "Correct"
        }
        else {
            answer.text = "Wrong"
        }

        newNumber.text = newRandomNumber.toString()
    }

    fun guessLower() {
        if(initialRandomNumber < newRandomNumber) {
            answer.text = "Correct"
        }
        else {
            answer.text = "Wrong"
        }

        newNumber.text = newRandomNumber.toString()
    }

}