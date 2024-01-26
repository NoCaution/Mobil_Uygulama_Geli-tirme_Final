package com.example.myapplication.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.model.GameResult
import com.example.myapplication.model.GameState
import kotlin.random.Random

class GameController {
    private var gameState by mutableStateOf(
        GameState(
            targetNumber = generateRandomNumber(),
            userGuess = 0,
            message = "1 ile 100 arasında bir sayı tahmin et..",
            attempts = 0,
            gameResult = GameResult.OnGoing
        )
    )

    fun onUserGuessChange(userGuess: Int) {
        gameState = gameState.copy(userGuess = userGuess)
    }

    fun onGuessButtonClick(){
        handleGuessResult(gameState.targetNumber, gameState.userGuess)
    }

    fun onResetButtonClick() {
        resetGame()
    }

    fun gameState(): GameState {
        return gameState
    }

    private fun handleGuessResult(targetNumber: Int, userGuess: Int) {
        println(gameState.targetNumber)

        gameState = if(gameState.attempts == 10){
            gameState.copy(
                message = "tahmin hakkınız doldu! aranan sayı: ${gameState.targetNumber}",
                gameResult = GameResult.Lose
            )
        } else {

            val isCorrect = userGuess == targetNumber

            if (isCorrect) {
                gameState.copy(
                    message = "Tebrikler! Sayıyı doğru tahmin ettiniz.",
                    gameResult = GameResult.Win
                )
            } else {
                gameState.copy(
                    message = "Yanlış! Tekrar dene.",
                    attempts = gameState.attempts + 1,
                    gameResult = GameResult.OnGoing
                )
            }
        }
    }

    private fun resetGame() {
        gameState = GameState(
            targetNumber = generateRandomNumber(),
            userGuess = 0,
            message = "1 ile 100 arasında bir sayı tahmin et..",
            attempts = 0,
            gameResult = GameResult.OnGoing
        )
    }

    private fun generateRandomNumber() = Random.nextInt(1, 101)
}

