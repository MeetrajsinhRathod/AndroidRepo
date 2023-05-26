package com.example.modal

import com.example.design.R

class UserData {
    private var experience = arrayListOf(
        Experience(
            "UX Intern",
            "Spotify",
            "San Jose, US",
            "Dec 20 - Feb 21",
            R.drawable.ic_company
        ),
        Experience(
            "UX Intern",
            "Spotify",
            "San Jose, US",
            "Dec 20 - Feb 21",
            R.drawable.ic_company
        ),
        Experience(
            "UX Intern",
            "Spotify",
            "San Jose, US",
            "Dec 20 - Feb 21",
            R.drawable.ic_company
        ),
        Experience("UX Intern", "Spotify", "San Jose, US", "Dec 20 - Feb 21", R.drawable.ic_company)
    )
    private var education = arrayListOf(
        Education(
            "Computer Science",
            "Bachelor | Caltech",
            "Pasadena",
            "2017 - 2020",
            R.drawable.ic_college
        ),
        Education(
            "Computer Science",
            "Bachelor | Caltech",
            "Pasadena",
            "2017 - 2020",
            R.drawable.ic_college
        ),
        Education(
            "Computer Science",
            "Bachelor | Caltech",
            "Pasadena",
            "2017 - 2020",
            R.drawable.ic_college
        ),
        Education(
            "Computer Science",
            "Bachelor | Caltech",
            "Pasadena",
            "2017 - 2020",
            R.drawable.ic_college
        )
    )
    private var portfolio = arrayListOf(
        Portfolio(R.drawable.portfolio1),
        Portfolio(R.drawable.portfolio2),
        Portfolio(R.drawable.portfolio1),
        Portfolio(R.drawable.portfolio2),
        Portfolio(R.drawable.portfolio1),
        Portfolio(R.drawable.portfolio2)
    )

    fun getExperienceList(): ArrayList<Experience> {
        return experience
    }

    fun getEducationList(): ArrayList<Education> {
        return education
    }

    fun getPortfolioList(): ArrayList<Portfolio> {
        return portfolio
    }
}