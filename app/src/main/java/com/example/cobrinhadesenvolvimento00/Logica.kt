package com.example.cobrinhadesenvolvimento00

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class Game(){
    var contador by mutableStateOf(0)
        private set
    suspend fun roda(){
        while(contador<1000){
            delay(1000)
            contador++
        }
    }
}
