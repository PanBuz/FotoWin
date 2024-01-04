package com.example.fotowin

data class Questions(
    val quest: String,
    val answer: Int
) {

    fun displayInfo(){
        println("$quest. Ответ: $answer \n")
    }

}
