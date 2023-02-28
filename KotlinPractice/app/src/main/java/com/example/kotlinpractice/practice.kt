package com.example.kotlinpractice

import kotlin.reflect.typeOf

const val constantVal: String = ""

//fun swap(val1: Int, val2: Int) {
//    val temp = val1
//    val1 = val2
//    val2 = temp
//}


fun main() {

    val immutableValue = 10
    var mutableVal = 9
    mutableVal = 20

    val valWithDecimalPoint = 3.14

    //get datatype
    println(valWithDecimalPoint::class.simpleName)

    //type conversion
    val floatVal = valWithDecimalPoint.toFloat()
    println(floatVal::class.simpleName)

    // conditions and range operator
    if (immutableValue in 1..mutableVal+1) {
        println("fits in range")
    }

    //loops
    for (item in 3..9 step 2) {
        println(item)
    }

    var name: String?
    name = null
    //functions
    fun printName(name: String?): Unit {
        if (name != null) {
            println(name)
        } else {
          println("String is null")
        }
    }
    //name = "Meetraj"
    printName(name)

    fun variableInputs(vararg input: Int) {
        println(input::class.simpleName)
        input.forEach { item -> println(item) }
    }
    variableInputs(1,2,3,4,5)


}
