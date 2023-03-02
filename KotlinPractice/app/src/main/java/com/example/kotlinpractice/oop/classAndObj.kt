package com.example.kotlinpractice.oop

open class Person(
    var firstName: String,
    var lastName: String,
) {

    constructor(first: String): this(first,"Last name"){
        print("Hey")
    }
}

open class Parent{

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

class Child: Parent {
    var nameChild: String
    constructor(name: String) : super("mkm"){
        this.nameChild = name
    }
}

fun main() {
    var person = Person("Meetraj")
    println(person.firstName)
    println(person.lastName)

    var child = Child("a")
}