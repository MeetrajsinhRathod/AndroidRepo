package com.example.model

data class CardData( var number1: Int, var number2: Int, val imageList: ArrayList<CardImageData>, val spinnerNumberList: ArrayList<CardSpinnerData>) {
    fun getTotalAdditionNumbers(): Int {
        return spinnerNumberList.fold(0) { result: Int, spinner: CardSpinnerData -> spinner.selectedNumber + result }
    }
}
