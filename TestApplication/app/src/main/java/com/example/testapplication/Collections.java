package com.example.testapplication;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

public class Collections {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println();

        Stack<String> stack = new Stack<String>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");
        stack.pop();

        Iterator<String> itr=stack.iterator();

        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(0);
        queue.add(3);
        System.out.println(queue.peek());
        System.out.println(queue);

        Set<String> set=new LinkedHashSet<>();
        set.add("Meetraj");
        set.add("Rahul");
        set.add("Meetraj");
        set.add("Harsh");
        System.out.println(set);

        Map<Integer, String> map=new HashMap();
        map.put(5,"five");
        map.put(4,"four");
        map.put(2,"two");
        map.put(6,"six");

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        System.out.println(entrySet);
        System.out.println(map.get(5));
        System.out.println(map);



    }
}