package com.example.testapplication;

public class A {
    void print() {
        System.out.println("Hello world");
    }
}
interface inter {
    int a = 0;
    void pr();
    void pr2();
    void pr3();

}
abstract class  AbstractExample{
    abstract void print(String a);
    double var = 4.0;
    int [] v = {1,2,3,4};

    String s = "faf"+v;
    final void nonAbstractMethod() {
        System.out.println("ssssss"+s);
    }

    AbstractExample(){

    }
}


class Animal {

    void eat(){System.out.println("eating");}
}
class Dog extends Animal{
    void eat(){System.out.println("eating fruits");}
}
class BabyDog extends Dog{
    void eat(){System.out.println("drinking milk");}
}