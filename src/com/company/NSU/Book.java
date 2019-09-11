package com.company.NSU;

public class Book {
    private int publicationYear;
    private String name;
    private String author;

    public Book(int publicationYear, String name, String author) {
        this.publicationYear = publicationYear;
        this.name = name;
        this.author = author;
    }


    public int getPublicationYear() { return publicationYear; }
    public String getName() { return name; }
    public String getAuthor() { return author; }


    @Override
    public String toString(){
        return "Имя - " + name + ", год публикации - " + publicationYear +
                (this instanceof Magazine ? " и главный редактор - " : ", а автор - ") + author + ".";
    }
}
