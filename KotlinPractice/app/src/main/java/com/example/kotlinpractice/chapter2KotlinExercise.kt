package com.example.kotlinpractice

import java.lang.Thread.sleep

//-------------------------------------------------Functions-------------------------------------------------
//Implement the following functions. The divides function returns true if a is divisible by b and false otherwise.
// The countDivisors function should use the divides function to return the number of divisors of number.
// The isPrime function should use the countDivisors function to determine if number is prime.
fun divides(dividend: Int, divisor: Int): Boolean {
    return dividend % divisor == 0
}

fun countDivisors(number: Int): Int {
    var divisorCount = 0
    //loop to count the number of divisors
    for (value in 1..number) {
        if (divides(number, value)) {
            divisorCount += 1
        }
    }
    return divisorCount
}

fun isPrime(number: Int): Boolean {
    return countDivisors(number) == 2
}

//Write a function named countdown that takes a number N.
// The function should print the numbers from N to 1 with a one second pause in between and then write GO! in the end.
// To make the computer wait for one second call thesleep function from the standard library.
// The sleep function takes one parameter, the number of seconds to sleep.

fun countDown(countFrom: Int) {
    var count = countFrom
    //loop for count down from N to 1
    do {
        println(count)
        sleep(1000)
        count -= 1
    } while (count > 0)
    println("GO!")
}

//A queue is a data structure that can perform two operations:
//• push which takes a value and adds it at the end of the queue
//• pop which returns the value from the start of the queue and removes it from the queue
//Your task is to implement the push and pop operations. The most simple way to represent a queue is using an array. Here are some example operations.
//// here we define an empty queue
//var queue: [Int] = []
//
//// add 1 in the queue
//push(1, &queue) // queue = [1]
//
//// add 2 in the queue
//push(2, &queue) // queue = [1, 2]
//
//// pop the first element
//pop(&queue) // 1, queue = [2, 3]
//
//// add 3 in the queue
//push(3, &queue) // queue = [2, 3]
//
//// pop the first element
//pop(&queue) // 2, queue = [3]
//
//// pop the first element
//pop(&queue) // 3, queue = []
//
//// pop the first element
//pop(&queue) // returns nil because there are no elements in the queue
//// queue = []
//The push function should take two parameters, the number and the queue as an inout parameter.
//Push Function Definition
//The pop function should take queue as an inout parameter and return the first number from the queue after removing it. If the queue is empty it should return nil – the result type should be an optional integer(Int?).
//Pop Function Definition

class Queue<T> {
    private var element = arrayListOf<T>()

    fun isEmpty(): Boolean {
        return element.isEmpty()
    }

    fun push(pushElement: T) {
        element.add(pushElement)
    }

    fun pop(): T? {
        if (isEmpty()) {
            return null
        }
        return element.removeFirst()
    }

    fun printQueue() {
        println(element)
    }
}

//-------------------------------------------------Lambda-------------------------------------------------

//Write a function named applyKTimes that takes an integer K and a closure and calls the closure K times.
// The closure will not take any parameters and will not have a return value.

fun applyKTimes(Ktimes: Int, recurringLambda: () -> Unit) {
    var repeatTill = Ktimes
    do {
        recurringLambda()
        repeatTill -= 1
    } while (repeatTill > 0)
}

//--------------------------------------------------------------------------------------------------
//protocol bank getSimple Interest
//1 base class sbi hdfc
//user input sbi , sbi interest function called

interface Bank {
    var duration: Int
    var principalAmount: Int
    var rate: Int

    fun getSimpleInterest()
}

class Sbi(
    override var duration: Int,
    override var principalAmount: Int,
    override var rate: Int
) : Bank {
    override fun getSimpleInterest() {
        println("${(principalAmount * rate * duration) / 100}")
    }
}

class Hdfc(
    override var duration: Int,
    override var principalAmount: Int,
    override var rate: Int
) : Bank {
    override fun getSimpleInterest() {
        println("${(principalAmount * rate * duration) / 100}")
    }
}

fun main() {
    println(divides(4, 2))
    println(countDivisors(5))
    println(isPrime(2))

    //countDown(5)

    var queue1 = Queue<Int>()
    queue1.push(2)
    queue1.push(1)
    queue1.push(3)
    queue1.printQueue()
    queue1.pop()
    queue1.pop()
    queue1.printQueue()

    applyKTimes(3) {
        println("from Lambda")
    }

    val sbiUser = Sbi(1, 3000, 8)
    sbiUser.getSimpleInterest()

    val hdfcUser = Hdfc(1, 3000, 10)
    hdfcUser.getSimpleInterest()
}