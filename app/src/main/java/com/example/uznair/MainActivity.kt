package com.example.uznair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var playButton: Button
    lateinit var playerNameInputField :  TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        var playerName = findViewById<TextView>(R.id.playerNameInput).text.toString()
        Log.d("!!!", "playerName: $playerName")
    }


}