package com.example.orderservice.entity;

public class Emp {
    int id;
    String name;
    static double salary;

    public static void  main(String[] args){
        Emp e1 = new Emp();
        e1.id = 10;
        e1.name = "arpit";
        e1.salary = 50000;

        Emp e2 = new Emp();
        e2.name = "gupta";
        e2.id = 441;
        e2.salary = 60000;

        System.out.println(e1.salary);
        System.out.println(e2.salary);

    }
}
