package com.example.cobrinhadesenvolvimento00

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class Game(){
    var posicaoAtual by mutableStateOf(0)
        private set
    suspend fun roda(){
        while(posicaoAtual<1000){
            delay(1000)
            posicaoAtual++
            posicaoAtual=posicaoAtual % 16
        }
    }
}
