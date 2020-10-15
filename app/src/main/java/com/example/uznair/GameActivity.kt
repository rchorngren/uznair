package com.example.uznair

import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.util.Log
import android.view.MotionEvent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.view.MotionEventCompat
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_correct.*


class GameActivity : AppCompatActivity() {
    lateinit var initialCard : ImageView

    var initialRandomNumber : Int = (1..10).random()
    var newRandomNumber : Int = (1..10).random()
    var playerScore : Int = 0
    var playerName : String = ""
    var startYPos : Int = 0
    var endYPos : Int = 0
    var fragmentActive : Boolean = false

    lateinit var score : TextView

    private var mediaPlayer : MediaPlayer? = null
    private var mediaPlayerWin : MediaPlayer? = null
    private var mediaPlayerGameOver : MediaPlayer? = null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action : Int = MotionEventCompat.getActionMasked(event)

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                if (event != null && !fragmentActive) {
                    startYPos = event.getY().toInt()
                }
                true
            }
            MotionEvent.ACTION_UP -> {
                if (event != null && !fragmentActive) {
                    endYPos = event.getY().toInt()
                }
                while(startYPos > endYPos) {
                    guessHigher()
                }

                while(startYPos < endYPos) {
                    guessLower()
                }
                true
            }
            else -> super.onTouchEvent(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        FirebaseAPI.connectToDb()
        playerName = intent.extras!!.getString("playerName").toString()
        playerScore = intent.extras!!.getInt("playerScore")
        score = findViewById(R.id.score)
        score.text = getString(R.string.score, playerScore.toString())
        mediaPlayer = MediaPlayer.create(this, R.raw.four_sided_circle)
        mediaPlayerWin = MediaPlayer.create(this, R.raw.game_win)
        mediaPlayerGameOver = MediaPlayer.create(this, R.raw.game_over)

        initialCard = findViewById(R.id.initialCard)

        while(initialRandomNumber == newRandomNumber) {
            newRandomNumber = (1..10).random()
        }

        CardImageManager.showCard(initialRandomNumber, initialCard)

        mediaPlayer?.start()
    }

    fun guessHigher() {
        startYPos = 0
        endYPos = 0
        if(initialRandomNumber < newRandomNumber) {
            playerScore++
            score.text = getString(R.string.score, playerScore.toString())
            displayCorrectFragment()
        }
        else {
            gameOver()
        }
        CardImageManager.showCard(newRandomNumber, initialCard)
    }

    fun guessLower() {
        startYPos = 0
        endYPos = 0
        if(initialRandomNumber > newRandomNumber) {
            playerScore++
            score.text = getString(R.string.score, playerScore.toString())
            displayCorrectFragment()
        }
        else {
            gameOver()
        }
        CardImageManager.showCard(newRandomNumber, initialCard)
    }

    fun displayCorrectFragment() {
        fragmentActive = true
        mediaPlayerWin = MediaPlayer.create(this, R.raw.game_win)
        mediaPlayerWin?.start()
        val correctFragment = CorrectFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.resultContainer, correctFragment, "correctFragment")
        transaction.commit()

    }

    fun anotherGame() {
        initialRandomNumber = newRandomNumber
        newRandomNumber = (1..10).random()
        while(initialRandomNumber == newRandomNumber) {
            newRandomNumber = (1..10).random()
        }
    }

    fun gameOver() {
        fragmentActive = true
        mediaPlayer?.stop()
        mediaPlayerGameOver?.start()
        val incorrectFragment = IncorrectFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.resultContainer, incorrectFragment, "incorrectFragment")
        transaction.commit()
    }

    fun goToHighScore() {
        var highScoreIntent = Intent(this, HighScoreActivity::class.java)
        var comingFrom : String = "GameActivity"
        val extras = Bundle()
        extras.putString("playerName", playerName)
        extras.putInt("playerScore", playerScore)
        extras.putString("comingFrom", comingFrom)
        highScoreIntent.putExtras(extras)
        removeIncorrectFragment()
        startActivity(highScoreIntent)
    }

    fun removeCorrectFragment() {
        fragmentActive = false
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

    override fun onBackPressed() {
        var toastMessage = getString(R.string.no_back_button)
        Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
    }

}