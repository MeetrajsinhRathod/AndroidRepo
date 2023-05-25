package com.example.modal

data class Medicine(
    val name: String,
    val type: String,
    val quantity: String,
    val imageId: Int,
    val beforeDeductibleAmount: String,
    val deductibleAmountMet: String
)