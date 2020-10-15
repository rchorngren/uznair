package com.example.uznair

import com.google.firebase.database.*

object FirebaseAPI {
    lateinit var ref : DatabaseReference

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
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun saveToDb(playerName : String, playerScore : Int) {
        val ref = FirebaseDatabase.getInstance().getReference("highscore")
        val highscoreId = ref.push().key
        val highscore = highscoreId?.let { HighScore(it, playerName, playerScore) }

        if (highscoreId != null) {
            ref.child(highscoreId).setValue(highscore).addOnCompleteListener {
            }
        }
    }
}