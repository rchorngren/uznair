package com.example.uznair

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_game.*

class HighScoreActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference

    var playerScore : Int = 0
    var playerName : String = ""
    var comingFrom : String = ""
    lateinit var newGameButton : Button
    lateinit var backButton : ImageView

    private var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

        playerName = intent.extras!!.getString("playerName").toString()
        playerScore = intent.extras!!.getInt("playerScore")
        comingFrom = intent.extras!!.getString("comingFrom").toString()

        newGameButton = findViewById(R.id.newGameButton)
        newGameButton.visibility = View.GONE
        backButton = findViewById(R.id.backButtonImage)

        mediaPlayer = MediaPlayer.create(this, R.raw.high_score)

        if(comingFrom == "GameActivity") {
            DataManager.highScore.add(Player(playerName, playerScore))
            saveToFirebase(playerName, playerScore)
            newGameButton.visibility = View.VISIBLE
            backButton.visibility = View.GONE
        }
        /*
        else if(comingFrom == "MainActivity") {
            connectToDb()
        }

         */

        mediaPlayer?.start()

        var sortedList = DataManager.highScore.sortedByDescending { it.score }

        val recyclerView = findViewById<RecyclerView>(R.id.highScoreRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val highScoreAdapter = HighScoreRecyclerAdapter(this, sortedList)

        recyclerView.adapter = highScoreAdapter

        var newGameButton = findViewById<Button>(R.id.newGameButton)

        newGameButton.setOnClickListener {
            newGameButton()
        }

        backButton.setOnClickListener {
            DataManager.highScore.clear()
            finish()
        }
    }

    fun saveToFirebase(playerName : String, playerScore : Int) {
        val ref = FirebaseDatabase.getInstance().getReference("highscore")
        val highscoreId = ref.push().key
        val highscore = highscoreId?.let { HighScore(it, playerName, playerScore) }

        if (highscoreId != null) {
            ref.child(highscoreId).setValue(highscore).addOnCompleteListener {
                Toast.makeText(applicationContext, "Data saved successfully", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun newGameButton() {
        var newGameIntent = Intent(this, GameActivity::class.java)
        val extras = Bundle()

        DataManager.highScore.clear()

        playerScore = 0

        extras.putString("playerName", playerName)
        extras.putInt("playerScore", playerScore)
        newGameIntent.putExtras(extras)

        startActivity(newGameIntent)
    }

    override fun onBackPressed() {
        var toastMessage = getString(R.string.no_back_button)
        Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
    }

}