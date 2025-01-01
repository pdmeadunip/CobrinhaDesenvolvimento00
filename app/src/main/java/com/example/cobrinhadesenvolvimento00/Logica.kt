package com.example.cobrinhadesenvolvimento00

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
//class Game(onAndaPara:(Boolean) -> Unit){
class Game(){
//    var onAndaPara by mutableStateOf(onAndaPara)
    var gameOver by mutableStateOf(false)
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

            if(cobra.contains(posicaoAtual)){
                gameOver=true
        //        onAndaPara(false)
                reset()
            }else{
            cobra.add(0,posicaoAtual)
                if(posicaoAtual==comida){
                    comida=Pair((1..15).random(),(1..15).random())
                    tamanho++
                }
                cobra=cobra.take(tamanho).toMutableList()
            }

            delay(500)
        }
    }
    fun reset(){
        tamanho = 3
        cobra = mutableListOf(Pair(7,7),Pair(7,6),Pair(7,5))
        direcaoAtual = Pair(0,1)
        posicaoAtual = Pair(7,7)
        posicaoX = (posicaoAtual.first)
        posicaoY = (posicaoAtual.second)
    }
}
