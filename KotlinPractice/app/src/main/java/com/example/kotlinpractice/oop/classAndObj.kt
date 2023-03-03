package com.example.kotlinpractice.oop

open class Person(
    var firstName: String,
    var lastName: String,
) {

    constructor(first: String) : this(first, "Last name") {
        print("Hey")
    }
}

open class Parent {

    var name: String = ""

    constructor(name: String, id: Int) {
        println("this executes first")
        println("Name = ${name}")
        println("Id = ${id}")
    }

    constructor(name: String) {
        this.name = name
    }

    constructor()
}

class Child : Parent {
    var nameChild: String

    constructor(name: String) : super("mkm") {
        this.nameChild = name
    }
}

open class Shape(var type: String, open var color: String, var size: Int) {
    open fun describeShape() {
        print("It has a $type shape, it is of $color color. It has size of : $size")
    }

    //getter setter with backing field
    open var property: String = "property"
        get() = field.uppercase()
        set(value) {
            field = value
        }

}

class Circle(var name: String, color: String, size: Int) : Shape("Circle", color, size) {

    init {
        println("From init block")
    }
    constructor(name: String) : this(name, "Black", 1) {
        println("From secondary block")
    }
    override fun describeShape() {
        println("This is a $name")
        super.describeShape()
    }

    override var property: String
        get() = super.property
        set(value) {}

    fun returnInt(): Int {
        println("Hello")
        return 2
    }

    val pi by lazy {
        return@lazy 3.14
    }

     var pi2 = lazy {
         println("RUN")
         return@lazy 3
     }



}

fun main() {

    var person = Person("Meetraj")
    println(person.firstName)
    println(person.lastName)

    var child = Child("a")

    var circle = Circle("donut")
    circle.property = "new value"

    println(circle.property)

    println("lazy : " + circle.pi)
    println("lazy : " + circle.pi2.value)
    circle.pi2 = lazy { 4 }
    println("lazy : " + circle.pi2.value)
}