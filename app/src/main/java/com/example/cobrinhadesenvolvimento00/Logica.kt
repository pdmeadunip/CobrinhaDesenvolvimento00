package com.example.cobrinhadesenvolvimento00

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class Game(){
    var tamanho by mutableStateOf(3)
    var comida by mutableStateOf(Pair(5,5))
    var cobra by mutableStateOf(
        mutableListOf(Pair(7,7),Pair(7,6),Pair(7,5))
    )
    var direcaoAtual by mutableStateOf(Pair(0,1))

    var posicaoAtual by mutableStateOf(Pair(7,7))
        private set
    var posicaoX by mutableStateOf(posicaoAtual.first)
    var posicaoY by mutableStateOf(posicaoAtual.second)
    suspend fun roda(){
        while(true){
            posicaoX = (posicaoX+direcaoAtual.first+16)%16
            posicaoY = (posicaoY+direcaoAtual.second+16)%16
            posicaoAtual=Pair(posicaoX,posicaoY)
            delay(500)
            cobra.add(0,posicaoAtual)
            if(posicaoAtual==comida){
                comida=Pair((1..16).random(),(1..16).random())
                tamanho++
            }
            cobra=cobra.take(tamanho).toMutableList()
        }
    }
}
