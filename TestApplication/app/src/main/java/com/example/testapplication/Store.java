package com.example.testapplication;

import java.util.Scanner;

public class Store extends Cart{

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Store store = new Store();

        System.out.println("Enter your choice \n 1.Mobile \n 2.Accessories");
        int choice = userInput.nextInt();
        store.buy(choice);
    }
}
