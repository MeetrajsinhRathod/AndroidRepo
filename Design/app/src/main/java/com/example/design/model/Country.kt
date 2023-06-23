package com.example.design.model

import java.io.Serializable

data class Country(val countryName: String, val countryFlag: Int, var collapsed: Boolean = false, var learnMoreButtonText: String = "See more")
