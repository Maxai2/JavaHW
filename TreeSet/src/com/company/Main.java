package com.company;

import lombok.Data;

public class Main {
    public static void main(String[] args) {

    }
}

@Data
class Student {
    String name;
    String surname;
    double avg;

    Student(String name, String surname, double avg) {
        this.name = name;
        this.surname = surname;
        this.avg = avg;
    }
}

interface Comparator<T> {
    boolean compare(T a, T b);
}

class TreeSet<T> {



    @Data
    class Node<T> {
        T value;

    }
}