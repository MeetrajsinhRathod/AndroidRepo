package com.example.testapplication;


import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;

public class Enum {
    enum Direction {
        North,
        East,
        West,
        South;

        String getCurrentDirection() {
            return "Current Direction is "+Direction.this;
        }
    }

    enum Day {

        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;
        void dayIs()
        {
            switch (this) {
                case MONDAY:
                    System.out.println("Mondays are bad.");
                    break;
                case FRIDAY:
                    System.out.println("Fridays are better.");
                    break;
                case SATURDAY:
                case SUNDAY:
                    System.out.println("Weekends are best.");
                    break;
                default:
                    System.out.println("Midweek days are so-so.");
                    break;
            }
        }
    }

    enum Fruits {
        APPLE("RED"),
        BANANA("YELLOW"),
        GRAPES("GREEN");

        String color = "";


        Fruits(String color) {
            this.color = color;
        }

        String getColor() {
            return color;
        }

    }

    public static void main(String[] args) {
        Direction direction = Direction.North;
        System.out.println(direction.getCurrentDirection());

        Day day = Day.SUNDAY;
        day.dayIs();

        //values valueOf Ordinal
        Fruits arr[] = Fruits.values();

        for (Fruits col : arr) {
            System.out.println(col + " at index " + col.ordinal());
        }

        System.out.println(arr[1].getColor());
        System.out.println(Fruits.valueOf("APPLE"));
    }
}
