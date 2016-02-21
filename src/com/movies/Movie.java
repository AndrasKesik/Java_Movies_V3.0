package com.movies;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Product implements Buyable {

    Genre genre;
    long duration;
    double rate;
    List<Person> cast;
    int price;

    public Movie(String title, Genre genre, long duration, double rate, int price, Person person) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rate = rate;
        this.cast = new ArrayList<Person>();
        this.price = price;
        this.person = person;
    }

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public long getDuration() {
        return duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public List<Person> getCast() {
        return cast;
    }
    public void setCast(List<Person> cast) {
        this.cast = cast;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public long getInvestment() {
        long result = 0;
        try {
            for (Person person : cast) {
                result += person.getSalary();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
