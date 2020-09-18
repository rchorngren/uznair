package com.example.uznair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    lateinit var initialNumber : TextView
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
        var anotherGame = findViewById<Button>(R.id.anotherGameButton)


        while(initialRandomNumber == newRandomNumber) {
            newRandomNumber = (1..10).random()
        }

        highButton.setOnClickListener {
            guessHigher()
        }

        lowButton.setOnClickListener {
            guessLower()
        }

        anotherGame.setOnClickListener {
            anotherGame()
        }

        initialNumber = findViewById(R.id.initialNumber)

        initialNumber.text = initialRandomNumber.toString()
        answer = findViewById(R.id.answerText)
        newNumber = findViewById(R.id.newNumber)
    }

    fun guessHigher() {
        if(initialRandomNumber < newRandomNumber) {
            answer.text = "Correct"
            //increase score in Player.score
        }
        else {
            answer.text = "Wrong"
        }

        newNumber.text = newRandomNumber.toString()
    }

    fun guessLower() {
        if(initialRandomNumber > newRandomNumber) {
            answer.text = "Correct"
            //increase score in Player.score
        }
        else {
            answer.text = "Wrong"
        }

        newNumber.text = newRandomNumber.toString()
    }

    fun anotherGame() {

        initialRandomNumber = newRandomNumber

        initialNumber.text = initialRandomNumber.toString()
        newNumber.text = "To be revealed soon"

        newRandomNumber = (1..10).random()


    }

}