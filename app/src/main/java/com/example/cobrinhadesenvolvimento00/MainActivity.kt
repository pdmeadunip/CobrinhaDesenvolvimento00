package com.example.cobrinhadesenvolvimento00

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cobrinhadesenvolvimento00.ui.theme.CobrinhaDesenvolvimento00Theme

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
    //val game = remember{Game(onAndaPara = { jogoRodando = it })}
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
    val tamBotaoMod = Modifier.size(64.dp)
    val corpoCobra = game.cobra
    var posicao:Pair<Dp,Dp>
    var dimensaoPonto=maxWidth/16
    var comidaAtual=game.comida

    Column( modifier = Modifier.background(Color.LightGray),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        if (game.gameOver) {
            texto = "Game Over"
            jogoRodando = false
        } else {
            texto = "Jogo da Cobrinha ${corpoCobra.size} ${corpoCobra} "
        }
        Text(text = texto)

//        posicao=Pair(
//            dimensaoPonto*game.posicaoAtual.first,
//            dimensaoPonto*game.posicaoAtual.second)

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
                    .offset(x = dimensaoPonto*comidaAtual.first, y = dimensaoPonto*comidaAtual.second)
                    .size(dimensaoPonto)
                    .background(Color.Red, Shapes().small))
            {}
            corpoCobra.forEach { corpo ->
                Box(
                    modifier = Modifier
                        .offset(x = dimensaoPonto * corpo.first, y = dimensaoPonto * corpo.second)
                        .size(dimensaoPonto)
                        .background(
                            Color.DarkGray,
                            Shapes().small
                        )
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)) {
            Button(
                onClick = { game.direcaoAtual = Pair(0, -1) },
                modifier = tamBotaoMod,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_seta_cima),
                    contentDescription = "cima"
                )
               }
            Row {
               Button(
                    onClick = { game.direcaoAtual = Pair(-1, 0) },
                    modifier = tamBotaoMod,
                    shape = RoundedCornerShape(16.dp)
               ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_seta_esquerda),
                        contentDescription = "esquerda"
                    )
               }
                Spacer(modifier = tamBotaoMod)
                Button(
                    onClick = { game.direcaoAtual = Pair(1, 0) },
                    modifier = tamBotaoMod,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_seta_direita),
                        contentDescription = "direita"
                    )
                }
            }
            Button(
                onClick = { game.direcaoAtual=Pair(0,1) },
                modifier = tamBotaoMod,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_seta_baixo),
                    contentDescription = "baixo"
                )
            }

        }
        Button(
            onClick = {
                        game.gameOver=false
                        game.reset()
                        jogoRodando = !jogoRodando},
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (jogoRodando) "Reset" else "Start")
        }
    }
  } 
}


