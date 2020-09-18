package com.example.uznair

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var playButton: Button
    lateinit var playerNameInputField :  TextView
    lateinit var currentPlayer : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentPlayer = Player("", 0)

        playButton = findViewById(R.id.playButton)
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
    }





    fun playButton(view: View) {
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


}