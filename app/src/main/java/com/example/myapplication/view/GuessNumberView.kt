package com.example.myapplication.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.myapplication.controller.GameController
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.GameResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessNumberView (gameController: GameController){
    val gameState by remember { derivedStateOf { gameController.gameState() }}
    val textColor = when (gameState.gameResult) {
        GameResult.Win -> {
            Color.Green
        }
        GameResult.Lose -> {
            Color.Red
        }
        else -> {
            Color.Black
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box (
            modifier = Modifier.width(400.dp).height(180.dp)
        ){
            Text(
                text = gameState.message,
                style = TextStyle(
                    color = textColor,
                    fontSize = 40.sp
                ),
                modifier = Modifier.padding(20.dp).align(alignment = Alignment.Center)
            )
        }

        OutlinedTextField(
            value = gameState.userGuess.toString(),
            onValueChange = {
                if (it.isNotEmpty()) {
                    gameController.onUserGuessChange(it.toInt())
                }
            },
            label = { Text("tahmin gir..") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Send
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp, top = 40.dp),
            readOnly = when(gameState.gameResult) {
                GameResult.OnGoing -> {
                    false
                }
                else -> { true }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { gameController.onGuessButtonClick() },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
            ) {
                Text(text = "tahmin et")
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(start = 10.dp)
                    )
            }

            Column (
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = "denemeler: ${gameState.attempts}"
                )
                Text(
                    text = "kalan: ${10-gameState.attempts}"
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.padding(top = 60.dp)
        ) {
            Button(
                onClick = { gameController.onResetButtonClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)

            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(text ="oyunu yenile")
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

            }
        }

    }
}