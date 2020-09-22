package com.example.uznair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_game.*

class HighScoreActivity : AppCompatActivity() {

    var playerScore : Int = 0
    var playerName : String = ""

    var highScoreList = mutableListOf<Player>(
        Player("First Player", 5),
        Player("Second Player", 10),
        Player("Third Player", 1),
        Player("Forth Player", 50),
        Player("Fifth Player", 7),
        Player("Last Player", 23)
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

        playerName = intent.extras!!.getString("playerName").toString()
        playerScore = intent.extras!!.getInt("playerScore")

        highScoreList.add(Player(playerName, playerScore))

        var sortedList = highScoreList.sortedByDescending { it.score }




        val recyclerView = findViewById<RecyclerView>(R.id.highScoreRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val highScoreAdapter = HighScoreRecyclerAdapter(this, sortedList)

        recyclerView.adapter = highScoreAdapter
    }
}