package com.example.design.model

data class CardData(
    var multiplicationNumber1: Int = 0,
    var multiplicationNumber2: Int = 0,
    val imageList: ArrayList<CardImageData> = arrayListOf(),
    val spinnerNumberList: ArrayList<CardSpinnerData> = arrayListOf()
) {

    private fun getTotalAdditionNumbers(): Int = spinnerNumberList.fold(0) { result: Int, spinner: CardSpinnerData -> spinner.selectedNumber + result }

    fun getResult(): Int = (multiplicationNumber1 * multiplicationNumber2) + getTotalAdditionNumbers()
}
