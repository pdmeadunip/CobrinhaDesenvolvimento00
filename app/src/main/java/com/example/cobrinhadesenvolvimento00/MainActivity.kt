package com.example.cobrinhadesenvolvimento00

import android.R.attr.onClick
import android.R.attr.text
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cobrinhadesenvolvimento00.ui.theme.CobrinhaDesenvolvimento00Theme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CobrinhaDesenvolvimento00Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        JogoCobrinha()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CobrinhaDesenvolvimento00Theme {
        Greeting("Android")
    }
}


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun JogoCobrinha() {
//  variáveis
    var jogoRodando by remember { mutableStateOf(false) }
    val game = remember{Game()}
    var texto = ""
//Controle
    if (jogoRodando) {
        LaunchedEffect(game) {
            game.roda()
        }
    }
    //Tela
BoxWithConstraints(){
    Column() {
        texto = "Jogo da Cobrinha ${game.contador}  "
        Text(text = texto)

        BoxWithConstraints() {
            Box(
                Modifier
                    .size(maxWidth)
                    .background(Color.White)
                    .border(2.dp, Color.Green)
            ) {
            }

            Box(
                modifier = Modifier
                    .offset(x = game.contador.dp , y = 30.dp)
                    .size(30.dp)
                    .background(
                        Color.DarkGray,
                        Shapes().small
                    )
            )
        }

        Button(
            onClick = { jogoRodando = !jogoRodando },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (jogoRodando) "Pause" else "Start")
        }
    }
  }
}


