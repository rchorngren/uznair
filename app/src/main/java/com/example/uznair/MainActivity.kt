package com.example.uznair

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    lateinit var playButton : Button
    lateinit var highScoreButton : Button
    lateinit var playerNameInputField : TextView
    lateinit var currentPlayer : Player

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectToDb()

        currentPlayer = Player("", 0)

        playButton = findViewById(R.id.playButton)
        highScoreButton = findViewById(R.id.highscoreButton)

        playButton.isEnabled = false

        playerNameInputField = findViewById(R.id.playerNameInput)

        playerNameInputField.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                playButton.isEnabled = s.toString().trim{ it <= ' '}.isNotEmpty()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable) {
            }

        })

        playButton.setOnClickListener {
            playButton()
        }

        highScoreButton.setOnClickListener {
            highScoreButton()
        }

    }

    fun playButton() {
        DataManager.highScore.clear()
        currentPlayer.name = findViewById<TextView>(R.id.playerNameInput).text.toString()
        var playerName = currentPlayer.name
        var playerScore = currentPlayer.score
        var playIntent = Intent(this, GameActivity::class.java)
        val extras = Bundle()
        extras.putString("playerName", playerName)
        extras.putInt("playerScore", playerScore)
        playIntent.putExtras(extras)

        startActivity(playIntent)
    }

    fun highScoreButton() {
        var highScoreIntent = Intent(this, HighScoreActivity::class.java)
        var comingFrom : String = "MainActivity"
        highScoreIntent.putExtra("comingFrom", comingFrom)

        startActivity(highScoreIntent)
    }

    fun connectToDb() {
        ref = FirebaseDatabase.getInstance().getReference("highscore")

        ref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()) {
                    var data = p0.children
                    for(i in data) {
                        val highScoreEntry = i.getValue(HighScore::class.java)
                        if (highScoreEntry != null) {
                            DataManager.highScore.add(Player(name = highScoreEntry.name, score = highScoreEntry.score))
                        }
                    }
                }
                else {
                    Log.d("!!!", "no p0")
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}