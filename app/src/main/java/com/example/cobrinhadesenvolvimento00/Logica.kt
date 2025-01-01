package com.example.cobrinhadesenvolvimento00

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class Game(){
    //variáveis
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
    //Função principal
    suspend fun roda(){
        //laço infinito
        while(true){
            //definindo a nova posição da cabeça da cobra
            posicaoX = (posicaoX+direcaoAtual.first+16)%16
            posicaoY = (posicaoY+direcaoAtual.second+16)%16
            posicaoAtual=Pair(posicaoX,posicaoY)
            // Verificando se a cobra colidiu com ela mesma
            if(cobra.contains(posicaoAtual)){
                gameOver=true
                reset()
            }else{
                //adicionando a nova posição da cabeça da cobra
                cobra.add(0,posicaoAtual)
                //verificando se a cobra comeu a comida
                if(posicaoAtual==comida){
                    comida=Pair((1..15).random(),(1..15).random())
                    tamanho++
                }
                //ajustando o tamanho da cobra
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
