package com.example.uznair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    lateinit var nameTest : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var playerName = intent.getStringExtra("playerName")

        nameTest = findViewById(R.id.nameTest)

        nameTest.text = playerName

    }
}