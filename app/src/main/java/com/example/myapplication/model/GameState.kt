package com.example.myapplication.model

data class GameState (
    val targetNumber: Int,
    val userGuess: Int,
    val message : String,
    val attempts: Int,
    val gameResult: GameResult
)