package com.movies;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RentManager {

    public static void main(String[] args) {

        Person p1 = new Person("Git", "Áron", Gender.MALE, 5555);
        Person p2 = new Person("Külö","Nóra",Gender.FEMALE, 4444);
        Person p3 = new Person("Lassú","ANet(t)",Gender.FEMALE, 3586);
//        Person p4 = new Person("Kér","Ede",Gender.MALE, 4538);
//        Person p5 = new Person("Koaxk","Ábel",Gender.MALE, 5387);
//        Person p6 = new Person("Elektrom","Ágnes",Gender.FEMALE, 5198);
//        Person p7 = new Person("Bármi","Áron",Gender.MALE, 5089);
//        Person p8 = new Person("Har","Mónika",Gender.FEMALE, 4869);
//
//
//        Movie m1 = new Movie("Inception", Genre.SCI_FI, 200, 4.8, 50, p1);
//        Movie m2 = new Movie("TheMatrix", Genre.SCI_FI, 190, 4.9, 55,p1);
//        m1.getCast().add(p1);
//        m1.getCast().add(p4);
//        m2.getCast().add(p5);
//        m2.getCast().add(p8);
////        System.out.println("MOVIES:");
////        System.out.printf("Investment on %s: %d\n", m1.getTitle(),m1.getInvestment());
////        System.out.printf("Investment on %s: %d\n", m2.getTitle(),m2.getInvestment());
//
//        Game g1 = new Game("The Witcher", true, 80, p4,2016);
//        Game g2 = new Game("Call of Duty", false, 70, p5,2016);
//        g1.getStaff().add(p2);
//        g1.getStaff().add(p3);
//        g2.getStaff().add(p6);
//        g2.getStaff().add(p7);
////        System.out.println("GAMES:");
////        System.out.printf("Investment on %s: %d\n", g1.getTitle(),g1.getInvestment());
////        System.out.printf("Investment on %s: %d\n", g2.getTitle(),g2.getInvestment());
//
//        Book b1 = new Book("Python 3",p8,p2);
//        Book b2 = new Book("Java SE 7",p7,p3);
////        System.out.println("BOOKS:");
////        System.out.printf("Investment on %s: %d\n", b1.getTitle(),b1.getInvestment());
////        System.out.printf("Investment on %s: %d\n", b2.getTitle(),b2.getInvestment());
//
////        List<Buyable> a = Arrays.asList(g2);
////
////        System.out.printf("\nSum of prices: %d",sumPrices(a));


        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            socket = new Socket("localhost", 4444);
            System.out.println("Connected to: " + socket.getInetAddress());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Object o;
        while (true) {
            try {

                userInput = scanner.nextLine();


                if (userInput.toUpperCase().equals("GET")) {
                    oos.writeObject(Command.GET);
                    System.out.println(ois.readObject().toString());

                } else if (userInput.toUpperCase().equals("PUT")) {
                    oos.writeObject(Command.PUT);

                    oos.writeObject(p1);

                } else if (userInput.toUpperCase().equals("EXIT")) {
                    oos.writeObject(Command.EXIT);
                    break;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }







    public static long sumPrices(List<Buyable> listOfObjects){
        long result=0;
        for (Buyable o: listOfObjects){
            result+=o.getPrice();}
        return result;
    }
}
