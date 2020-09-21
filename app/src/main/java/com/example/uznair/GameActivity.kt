package com.example.uznair

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity() {

    lateinit var initialNumber : TextView
    lateinit var answer : TextView
    lateinit var newNumber : TextView
    var initialRandomNumber : Int = (1..10).random()
    var newRandomNumber : Int = (1..10).random()
    var playerScore : Int = 0
    var playerName : String = ""
    lateinit var score : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        playerName = intent.extras!!.getString("playerName").toString()
        playerScore = intent.extras!!.getInt("playerScore")

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
        score = findViewById(R.id.score)
        score.text = playerScore.toString()
    }

    fun guessHigher() {
        if(initialRandomNumber < newRandomNumber) {
            answer.text = "Correct"
            playerScore++
            score.text = playerScore.toString()
        }
        else {
            answer.text = "Wrong"
            gameOver()
        }
        newNumber.text = newRandomNumber.toString()
    }

    fun guessLower() {
        if(initialRandomNumber > newRandomNumber) {
            answer.text = "Correct"
            playerScore++
            score.text = playerScore.toString()
        }
        else {
            answer.text = "Wrong"
            gameOver()
        }
        newNumber.text = newRandomNumber.toString()
    }

    fun anotherGame() {
        initialRandomNumber = newRandomNumber
        initialNumber.text = initialRandomNumber.toString()
        newNumber.text = "To be revealed soon"
        answer.text = "You choose..."
        newRandomNumber = (1..10).random()
        while(initialRandomNumber == newRandomNumber) {
            newRandomNumber = (1..10).random()
            Toast.makeText(this, "Disco!!", Toast.LENGTH_SHORT).show()
        }
    }

    fun gameOver() {
        var highScoreIntent = Intent(this, HighScoreActivity::class.java)
        val extras = Bundle()
        extras.putString("playerName", playerName)
        extras.putInt("playerScore", playerScore)
        highScoreIntent.putExtras(extras)

        startActivity(highScoreIntent)
    }

}