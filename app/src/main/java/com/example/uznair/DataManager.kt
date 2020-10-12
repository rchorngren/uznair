package com.example.uznair

import android.provider.ContactsContract
import android.util.Log
import com.google.firebase.database.*

object DataManager {
    // lateinit var ref : DatabaseReference
    //lateinit var dbHighScore : MutableList<HighScore>

    //lateinit var dbScore : MutableIterable<ContactsContract.Data>

    val highScore = mutableListOf<Player>()
/*
    init {
        //dbHighScore = mutableListOf()
        //val highScore = mutableListOf<Player>()
        //createHighScoreData()
        connectToDb()
        Log.d("!!!", "running DataManager init")
    }

    fun connectToDb() {
        Log.d("!!!", "running connectToDb")
        ref = FirebaseDatabase.getInstance().getReference("highscore")

        ref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("!!!", "new data was detected")
                if(p0!!.exists()) {
                    //Log.d("!!!", p0.toString())
                    var data = p0.children
                    Log.d("!!!", data.toString())

                    for(i in data) {
                        Log.d("!!!", "i: $i")
                        val highScoreEntry = i.getValue(HighScore::class.java)
                        Log.d("!!!", "highScoreEntry: ${highScoreEntry!!.name}")
                        if (highScoreEntry != null) {
                            Log.d("!!!", "highScoreEntry in IF: ${highScoreEntry!!.name}")
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



/*
    fun createHighScoreData() {
        highScore.add(Player("Player One", 10))
        highScore.add(Player("Player Two", 15))
        highScore.add(Player("Player Three", 5))
        highScore.add(Player("Player Four", 20))
        highScore.add(Player("Player Five", 3))
    }

 */

 */
}