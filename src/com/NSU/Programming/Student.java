package com.NSU.Programming;

import java.util.ArrayList;

public class Student{
    private String firstName;
    private String lastName;
    private ArrayList<Book> Books = new ArrayList<>();

    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getNumberOfBooks(){ return Books.size(); }
    public Book getBookByNumber(int i){ return Books.get(i); }

    public void giveBook(Book book){ Books.add(book); }



    @Override
    public String toString(){
        return "Имя - " + firstName + ", фамилия - " + lastName +
               " кол-во книг - " + Books.size() + ".";
    }
}
