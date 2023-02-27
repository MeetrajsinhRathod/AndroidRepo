package com.example.testapplication;

class InvalidAgeException  extends Exception
{
    public InvalidAgeException (String str) {
        super(str);
    }
}

public class ExceptionHandling {

    static void validate (int age) throws InvalidAgeException{
        if(age < 18){
            throw new InvalidAgeException("Age is not valid to vote");
        }
        else {
            System.out.println("You can vote");
        }
    }

    public static void main(String[] args) {
        try {
            validate(13);
        } catch (InvalidAgeException ex) {
            System.out.println("Exception occurred: " + ex);
        }
        System.out.println("rest of the code...");
    }
}

