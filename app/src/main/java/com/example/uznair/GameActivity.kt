package com.example.uznair

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.TypedArrayUtils.getString


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
            answer.text = getString(R.string.correct_guess)
            playerScore++
            score.text = playerScore.toString()
        }
        else {
            answer.text = getString(R.string.incorrect_guess)
            gameOver()
        }
        newNumber.text = newRandomNumber.toString()
    }

    fun guessLower() {
        if(initialRandomNumber > newRandomNumber) {
            answer.text = getString(R.string.correct_guess)
            playerScore++
            score.text = playerScore.toString()
        }
        else {
            answer.text = getString(R.string.incorrect_guess)
            gameOver()
        }
        newNumber.text = newRandomNumber.toString()
    }

    fun anotherGame() {
        initialRandomNumber = newRandomNumber
        initialNumber.text = initialRandomNumber.toString()
        newNumber.text = getString(R.string.card_backside)
        answer.text = getString(R.string.waiting_for_result)
        newRandomNumber = (1..10).random()
        while(initialRandomNumber == newRandomNumber) {
            newRandomNumber = (1..10).random()
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