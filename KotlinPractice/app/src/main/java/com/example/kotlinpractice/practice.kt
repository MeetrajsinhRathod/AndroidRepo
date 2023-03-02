package com.example.kotlinpractice

import java.util.Scanner

const val constantVal: String = "string"

fun main() {

    var str1: String? = "string"
    var newString = str1 as String
    println(newString::class.simpleName)

    //variables constants
    val immutableValue = 10
    var mutableVal = 9
    mutableVal = 20

    // range operator , downTo
    if (immutableValue in 1..mutableVal + 1) {
        println("Is in range")
    }

    val valWithDecimalPoint = 3.14

    //get datatype
    println(valWithDecimalPoint::class.simpleName)

    //type conversion
    val floatVal = valWithDecimalPoint.toFloat()
    println(floatVal::class.simpleName)

    //user input
    // using Scanner
    val read = Scanner(System.`in`)
    println("Enter value for input : ")
    var getInput = read.nextInt()
    // using readln() // readLine()
    println("Enter value for input : ")
    var input: Int? = readLine()?.toIntOrNull()

    //array
    var array: Array<Any>
    array = Array(5, { i -> i })

    //array = arrayOf<Int>(0,1,2,3,4)
    array.forEach { element -> print("$element ") }
    println()
    println(array[0])
    println(array.get(2))
    array.set(0, 5)
    println(array.size)
    println(array.component1())

    // Control flow

    //if else
    val num1 = 10
    val num2 = 20
    val result = if (num1 > num2) {
        "$num1 is greater than $num2"
    } else {
        "$num1 is smaller than $num2"
    }
    println(result)

    //when, can also have range
    var dayOfWeek = 1
    when (dayOfWeek) {
        1 -> println("Monday")
        2 -> println("Tuesday")
        3 -> println("Wednesday")
        4 -> println("Thursday")
        5 -> println("Friday")
        6 -> println("Saturday")
        7 -> println("Sunday")
        else -> println("Other days")
    }

    //for loop
    for (item in 3..9 step 2) {
        print("$item ")
    }
    println()

    // while
    while (mutableVal > 0) {
        print("$mutableVal ")
        mutableVal--
    }
    println()

    //functions
    var name: String?
    name = null

    fun printName(name: String?): Unit {
        if (name != null) {
            println(name)
        } else {
            println("String is null")
        }
    }
    //name = "Meetraj"
    printName(name)

    //variable input
    fun variableInputs(vararg input: Int) {

        println(input::class.simpleName)
        input.forEach { item -> print("$item ") }
        println()
    }
    variableInputs(1, 2, 3, 4, 5)

    //lambda
    var escape: (Int, Int) -> Int

    fun addTwoNumbers(num1: Int, num2: Int, addLambda: (Int, Int) -> Int): (Int, Int) -> Int {
        //escap = addLambda
        println("Sum of two numbers: " + addLambda(num1, num2))
        return addLambda
    }
    addTwoNumbers(num1, num2) { n1, n2 -> n1 + n2 }
    escape = addTwoNumbers(num1, num2) { n1, n2 -> n1 + n2 }
    println("from escape" + escape(1, 2))

    //null safety
    val notNullString = "Hello"
    val nullableString: String? = null
    println(notNullString.length)

    //safe call
    println(nullableString?.length)

    //elvis operator
    //val lengthOfString: Int = if (nullableString?.length != null) nullableString.length else -1
    var lengthOfString = nullableString?.length ?: -1

    //not null asssertion (!!)
    //lengthOfString = nullableString!!.length //will give error

    class B {
        var c = 1
    }

    class A {
        var b: B? = null
    }

    var a: A? = A()
    println(a?.b?.c ?: 0)

    // scoping functions
    //let
    var str: String? = "Hi there"
    str?.let { println(" ${it.length} ") }
    str = null
    str?.let { println(" ${it} ") }

    //run
    str.run { str += str }
    println(str)

    //also
    var m = 1
    println(m.also { it + 1 })

    var infixExampleObj = InfixExample()
    infixExampleObj printNumber 1

}

class InfixExample {
    infix fun printNumber(num: Int) {
        println("Number is: $num")
    }

}