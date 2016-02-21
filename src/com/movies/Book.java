package com.movies;

public class Book extends Product{
    Person author;

    public Book(String title, Person author,Person person) {
        this.title = title;
        this.author = author;
        this.person = person;
    }

    public Person getAuthor() {
        return author;
    }
    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public long getInvestment(){
        return author.getSalary();
    }
}
