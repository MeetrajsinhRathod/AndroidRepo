package com.example.kotlinpractice

class ExceptionHandling(msg: String) : Exception(msg)

fun checkAge(age: Int) {
    if (age < 18) throw ExceptionHandling("Invalid age")
    println("Valid age")
}

fun main() {
    try {
        checkAge(10)
    } catch (e: ExceptionHandling) {
        println(e.message)
    } finally {
        println("This is inside finally block")
    }
}

