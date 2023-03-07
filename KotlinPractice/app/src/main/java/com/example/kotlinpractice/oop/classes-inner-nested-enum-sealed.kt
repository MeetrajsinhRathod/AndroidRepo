package com.example.kotlinpractice.oop

enum class Colors(val r: Int, val g: Int, val b: Int) {
    Red(100, 0, 0) {
        override fun describeColor() = println("This is red color")
        fun hello() = println("Hello world")
    },
    Green(0, 100, 0) {
        override fun describeColor() = println("This is Green color")
    },
    Blue(0, 0, 100) {
        override fun describeColor() = println("This is Blue color")
    };

    abstract fun describeColor()
}

sealed class Shapes {
    class Circle(val radius: Int) : Shapes()
    class Square(val side: Int) : Shapes()
    class Rectangle(val length: Int, val breadth: Int) : Shapes()

    fun checkShape(shape: Shapes) {
        when (shape) {
            is Shapes.Circle -> println("Circle: Radius is ${shape.radius}")
            is Shapes.Square -> println("Square: Side is ${shape.side}")
            is Shapes.Rectangle -> println("Rectangle: Length is ${shape.length} and breadth is ${shape.breadth} ")
        }
    }
}

open class Outer {
    private val msg = "Will be accessible in Inner"

    class Nested {
        fun inSideNested() = println("From nested class")
    }

    inner class Inner {
        fun inSideInner() = println(msg)
    }
}

inline class Width(val value: Int)

inline class Height(val value: Int)

class Rectangle(width: Width, height: Height) {
    val width: Width = width
    val height: Height = height

    fun describe() {
        println("${this.width} ${this.height}")
    }
}



fun main() {

    fun Rectangle.area() {
        println("Area : ${this.width.value * this.height.value}")
    }

    val width = Width(100)
    val height = Height(50)
    val shape = Rectangle(width, height)
    shape.describe()
    shape.area()

    val list = listOf<Int>(1, 2)

    val set = setOf<Int>(1, 2)
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

    val arrayList = arrayListOf(1, 2, 3)

    val mutableList = mutableListOf(1, 2, 3)

    val mutableNumbersMap = mutableMapOf<String, Int>().apply { this["one"] = 2; this["two"] = 1 }
    println("Sorted map: "+mutableNumbersMap.entries.sortedBy{it.value})

    //mutableNumbersMap.toSortedMap(compareValue)
    mutableNumbersMap.iterator()

    val nestedObj = Outer.Nested()
    nestedObj.inSideNested()

    val innerObj = Outer()
    innerObj.Inner().inSideInner()

    println(Colors.values().forEach { println(it) })
    println(Colors.valueOf("Red"))

    val red = Colors.Red
    red.describeColor()

    val circle = Shapes.Circle(9)
    circle.checkShape(circle)
}