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
import kotlinx.android.synthetic.main.fragment_correct.*


class GameActivity : AppCompatActivity() {

    lateinit var initialNumber : TextView
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

        while(initialRandomNumber == newRandomNumber) {
            newRandomNumber = (1..10).random()
        }

        highButton.setOnClickListener {
            guessHigher()
        }

        lowButton.setOnClickListener {
            guessLower()
        }

        initialNumber = findViewById(R.id.initialNumber)
        initialNumber.text = initialRandomNumber.toString()
        newNumber = findViewById(R.id.newNumber)
        score = findViewById(R.id.score)
        score.text = playerScore.toString()
    }

    fun guessHigher() {
        if(initialRandomNumber < newRandomNumber) {
            playerScore++
            score.text = playerScore.toString()
            displayCorrectFragment()
        }
        else {
            gameOver()
        }
        newNumber.text = newRandomNumber.toString()
    }

    fun guessLower() {
        if(initialRandomNumber > newRandomNumber) {
            playerScore++
            score.text = playerScore.toString()
            displayCorrectFragment()
        }
        else {
            gameOver()
        }
        newNumber.text = newRandomNumber.toString()
    }

    fun displayCorrectFragment() {

        val correctFragment = CorrectFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.resultContainer, correctFragment, "correctFragment")
        transaction.commit()

    }

    fun anotherGame() {
        initialRandomNumber = newRandomNumber
        initialNumber.text = initialRandomNumber.toString()
        newNumber.text = getString(R.string.card_backside)
        newRandomNumber = (1..10).random()
        while(initialRandomNumber == newRandomNumber) {
            newRandomNumber = (1..10).random()
        }
    }

    fun gameOver() {

        val incorrectFragment = IncorrectFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.resultContainer, incorrectFragment, "incorrectFragment")
        transaction.commit()
    }

    fun goToHighScore() {
        var highScoreIntent = Intent(this, HighScoreActivity::class.java)
        val extras = Bundle()
        extras.putString("playerName", playerName)
        extras.putInt("playerScore", playerScore)
        highScoreIntent.putExtras(extras)
        removeIncorrectFragment()
        startActivity(highScoreIntent)
    }

    fun removeCorrectFragment() {
        val correctFragment = supportFragmentManager.findFragmentByTag("correctFragment")
        if(correctFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(correctFragment)
            transaction.commit()
        }
    }

    fun removeIncorrectFragment() {
        val incorrectFragment = supportFragmentManager.findFragmentByTag("incorrectFragment")
        if(incorrectFragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(incorrectFragment)
            transaction.commit()
        }
    }

}