package com.example.uznair

import android.util.Log

class Player(
    var name : String,
    var score : Int
) {
    fun Logging() {
        Log.d("!!!", "playerName: $name, score: $score")
    }
}