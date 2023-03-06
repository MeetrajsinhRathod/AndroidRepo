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


fun main() {

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