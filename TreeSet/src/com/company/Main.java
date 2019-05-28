package com.company;

import lombok.Data;

public class Main {
    static TreeSet<Student> studTreeName;
    static TreeSet<Student> studTreeSurname;
    static TreeSet<Student> studTreeAvg;

    public static void main(String[] args) {

        studTreeName = new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return a.name.compareTo(b.name);
            }
        });

        Student stud1 = new Student("d", "b", 12.5);
        Student stud2 = new Student("a", "c", 14.45);
        Student stud3 = new Student("j", "c", 14.45);
        Student stud4 = new Student("e", "c", 14.45);

        studTreeName.add(stud1);
        studTreeName.add(stud2);
        studTreeName.add(stud3);
        studTreeName.add(stud4);


        studTreeSurname = new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return a.surname.compareTo(b.surname);
            }

        });

        studTreeAvg = new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                if (a.avg > b.avg) {
                    return -1;
                } else if (a.avg < b.avg) {
                    return 1;
                }
                return 0;
            }

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
    int compare(T a, T b);
}

class TreeSet<T> {
    private Node root;
    public short count;

    Comparator<T> comp;

    TreeSet(Comparator<T> comp) {
        this.comp = comp;
    }

    void add(T item) {
        if (this.root == null) {
            this.root = new Node(item);
        } else {
            this.root.AddElemnt(item);
        }
        this.count++;
    }


    class Node {
        private Node right;
        private Node left;
        private T value;

        public Node (T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void AddElemnt(T item){
            if(comp.compare(value, item) > 0) {
                if(this.right == null) {
                    this.right = new Node(item);
                } else {
                    this.right.AddElemnt(item);
                }
            } else if(comp.compare(value, item) < 0) {
                if(this.left == null) {
                    this.left= new Node(item);
                } else {
                    this.left.AddElemnt(item);
                }
            } else if(comp.compare(value, item) == 0) {
                return;
            }
        }
    }
}