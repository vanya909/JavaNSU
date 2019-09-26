package com.NSU.Programming;

public class Magazine extends Book{
    private int publicationNumber;
    private String mainTheme;

    public Magazine(int publicationYear, String name, String author, int publicationNumber, String mainTheme){
        super(publicationYear, name, author);
        this.publicationNumber = publicationNumber;
        this.mainTheme = mainTheme;
    }

    @Override
    public String toString(){
        return super.toString() + " Так же, номер публикации - " + publicationNumber +
                                  ", а главная тема - " + mainTheme + ".";
    }
}
