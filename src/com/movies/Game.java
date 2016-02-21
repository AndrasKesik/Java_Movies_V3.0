package com.movies;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Game extends Product implements Buyable{

    boolean preOrdered;
    List<Person> staff;
    int price;
    int releaseDate;

    public Game(String title, boolean preOrdered, int price,Person person, int releaseDate) {
        this.title = title;
        this.preOrdered = preOrdered;
        this.staff = new ArrayList<Person>();
        this.price = price;
        this.person = person;
        this.releaseDate = releaseDate;
    }

    public boolean isPreOrdered() {
        return preOrdered;
    }
    public void setPreOrdered(boolean preOrdered) {
        this.preOrdered = preOrdered;
    }
    public List<Person> getStaff() {
        return staff;
    }
    public void setStaff(List<Person> staff) {
        this.staff = staff;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    @Override
    public int getPrice() {
        if (preOrdered){
            return (int)(price*0.8);
        }
        int p = (Calendar.getInstance().get(Calendar.YEAR) - releaseDate);
        if(p<10){
            return (int) Math.round(price*(1.0-(p*0.1)));}
        else{return 0;}
    }

    @Override
    public long getInvestment(){
        long result=0;
        try {
            for (Person person: staff){
                result += person.getSalary();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
