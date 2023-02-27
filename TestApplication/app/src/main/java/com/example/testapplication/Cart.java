package com.example.testapplication;

import java.util.Scanner;

abstract class Cart {

    void showMobileDetails(Mobile m) {
        System.out.println("Name: " + m.getName() +"\nStorage: "+ m.getStorage() +"\nProcessor: "+ m.getProcessor() +"\nRam: "+ m.getRam());
    }
    void showAccessoryDetails(Accessories a) {
        System.out.println("Brand: " + a.getBrand() +"\nColor "+ a.getColor() +"\nPrice "+ a.getPrice());
    }
    void buyMobile(int choice){
        switch (choice) {
            case 1:
                System.out.println("Apple");
                showMobileDetails(new Apple("iphone 14", "a16",128,4));
                break;
            case 2:
                System.out.println("Samsung");
                showMobileDetails(new Samsung("s23 ultra", "gen 2",256,12));
                break;
            default:
                System.out.println("Enter correct choice");
        }
    }
    void buyAccessory(int choice){
        switch (choice) {
            case 1:
                System.out.println("Cases");
                showAccessoryDetails(new Case(1000,"Spigen","Black"));
                break;
            case 2:
                System.out.println("Charger");
                showAccessoryDetails(new Charger(1500,"Anker","White"));
                break;
            case 3:
                System.out.println("Earphones");
                showAccessoryDetails(new Earphones(10000,"Apple", "White"));
                break;
            default:
                System.out.println("Enter correct choice");
        }
    }
    void buy(int choice) {
        Scanner userInput = new Scanner(System.in);

        switch (choice) {
            case  1:
                System.out.println("Enter brand \n 1.Apple \n 2.Samsung");
                choice = userInput.nextInt();
                buyMobile(choice);
                break;
            case 2:
                System.out.println("Enter accessory \n 1.Cases \n 2.Charger \n 3.Earphones");
                choice = userInput.nextInt();
                buyAccessory(choice);
                break;
            default:
                System.out.println("Enter correct choice");
        }
    }
}
