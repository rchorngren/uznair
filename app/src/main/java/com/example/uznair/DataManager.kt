package com.example.uznair

object DataManager {
    val highScore = mutableListOf<Player>()

    init {
        createHighScoreData()
    }

    fun createHighScoreData() {
        highScore.add(Player("Player One", 10))
        highScore.add(Player("Player Two", 15))
        highScore.add(Player("Player Three", 5))
        highScore.add(Player("Player Four", 20))
        highScore.add(Player("Player Five", 3))
    }

}