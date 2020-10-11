package com.example.uznair

import android.util.Log
import com.google.firebase.database.*
import kotlin.properties.Delegates

object DataManager {
    lateinit var ref : DatabaseReference
    //lateinit var dbHighScore : MutableList<HighScore>

    val highScore = mutableListOf<Player>()

    init {
        //dbHighScore = mutableListOf()
        //val highScore = mutableListOf<Player>()



        //createHighScoreData()
        connectToDb()
    }
/*
    fun dbHighScore() {
        for(h in dbHighScore) {
            Log.d("!!!", dbHighScore.toString())
        }
    }

 */

    fun connectToDb() {
        ref = FirebaseDatabase.getInstance().getReference("highscore")

        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()) {
                    //Log.d("!!!", p0.toString())
                    var data = p0.children
                    Log.d("!!!", data.toString())
                    /*
                    for(i in data.value) {
                        highScore.add(Player(name = i.name, score = i.score))
                    }

                     */
/*
                    for(i in data) {
                        val highScoreEntry = i.getValue(HighScore::class.java)
                        if (highScoreEntry != null && highScoreEntry.score != 0) {

                            Log.d("!!!", "name: ${highScoreEntry.name}")
                            Log.d("!!!", "score ${highScoreEntry.score}")

                            highScore.add(Player(name = highScoreEntry.name, score = highScoreEntry.score))
                        }
                    }

 */
                    data.forEach() {
                        val highScoreEntry = it.getValue(HighScore::class.java)
                        //Log.d("!!!", highScoreEntry.toString())
                        if (highScoreEntry != null) {

                            Log.d("!!!", "name: ${highScoreEntry.name}")
                            Log.d("!!!", "score ${highScoreEntry.score}")

                            highScore.add(Player(name = highScoreEntry.name, score = highScoreEntry.score))
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

    fun createHighScoreData() {
        highScore.add(Player("Player One", 10))
        highScore.add(Player("Player Two", 15))
        highScore.add(Player("Player Three", 5))
        highScore.add(Player("Player Four", 20))
        highScore.add(Player("Player Five", 3))
    }
}