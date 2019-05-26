package com.company;

import lombok.Data;

public class Main {
    static TreeSet<Student> studTree;

    void add(Student stud) {

    }

    public static void main(String[] args) {
        studTree = new TreeSet<Student>((a, b) -> {
            if (a.name.compareTo(b.name) == -1) {

            } else if (a.name.compareTo(b.name) == 2) {

            } else if (a.name.compareTo(b.name) == 0) {

            }

            return true;
        });
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
    private Node<T> root;

    Comparator<T> comp;

    TreeSet(Comparator<T> comp) {
        this.comp = comp;
    }

    class Node<T> {
        private Node<T> right;
        private Node<T> left;
        private T value;

        public Node (T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }


    }
}