package ru.nsu.programming.students_books;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Пётр", "Иванов"));
        students.add(new Student("Денис", "Сайков"));
        students.add(new Student("Андрей", "Махин"));

        for (Student student : students){
            student.giveBook(new Book(1865, "Война и Мир", "Лев Толстой"));

            student.giveBook(new Book(2005,
                                      "Сборник задач и упражнений по математическому анализу",
                                      "Демидович Борис Павлович"));
        }

        Student denis = students.get(1);
        denis.giveBook(new Magazine(2019, "Моя любимая дача", "Конлига Медиа",
                                    10, "Садоводство"));

        for (Student student : students){
            System.out.println(student);
            for (int i = 0; i < student.getNumberOfBooks(); i++) { System.out.println(student.getBookByNumber(i)); }
        }
    }
}
