package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> verse = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        while (true) {
            System.out.println("Paste the verse:");
            while (true) {
                String str = scan.nextLine();

                if (str.equals("")) {
                    break;
                }
                verse.add(str);
            }

            System.out.println("\nChoose method of sort verse:");
            System.out.println("1) Quick sort");
            System.out.println("2) Bubble sort");
            System.out.print("Your choice(1/2): ");
            byte ch = scan.nextByte();

            System.out.println("\n1) Low to high");
            System.out.println("2) High to low");
            System.out.print("Your choice(1/2): ");
            byte rev = scan.nextByte();

            if (ch == 1) {
                if (rev == 1) {
                    new quickSortCl(verse.clone(), true, (verseNew)->{
                        System.out.println("\n");
                        for (String row : verseNew) {
                            System.out.println(row);
                        }
                    });
                } else if (rev == 2) {
                    new quickSortCl(verse.clone(), false, (verseNew)->{
                        System.out.println("\n");
                        for (String row : verseNew) {
                            System.out.println(row);
                        }
                    });
                }
                break;
            } else if (ch == 2) {
                if (rev == 1) {
                    new bubbleSortCl(verse.clone(), true, (verseNew)->{
                        System.out.println("\n");
                        for (String row : verseNew) {
                            System.out.println(row);
                        }
                    });
                } else if (rev == 2) {
                    new bubbleSortCl(verse.clone(), false, (verseNew)->{
                        System.out.println("\n");
                        for (String row : verseNew) {
                            System.out.println(row);
                        }
                    });
                }
                break;
            } else {
                System.out.println("Choose correct!");
            }
        }
    }
}

interface onSortComplete {
    void onComplete(ArrayList<String> list);
}

class quickSortCl extends Thread {

    static ArrayList<String> list;
    boolean rev;
    onSortComplete comp;

    quickSortCl(Object list, boolean rev, onSortComplete comp) {
        this.list = (ArrayList<String>) list;
        this.rev = rev;
        this.comp = comp;
        start();
    }

    @Override
    public void run() {
        super.run();
        quickSort(0, this.list.size() - 1, this.rev);
        this.comp.onComplete(this.list);
    }

    public static void quickSort(int firstEnt, int lastEnt, boolean rev) {
        if (firstEnt >= lastEnt)
            return;
        int i = firstEnt, j = lastEnt;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (rev ? (list.get(i).length() <= list.get(cur).length()) : (list.get(i).length() >= list.get(cur).length()))) {
                i++;
            }
            while (j > cur && (rev ? (list.get(cur).length() <= list.get(j).length()) : (list.get(cur).length() >= list.get(j).length()))) {
                j--;
            }
            if (i < j) {
                String temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        quickSort(firstEnt, cur, rev);
        quickSort(cur + 1, lastEnt, rev);
    }

}

class bubbleSortCl extends Thread {

    static ArrayList<String> list;
    boolean rev;
    onSortComplete comp;

    bubbleSortCl(Object list, boolean rev, onSortComplete comp) {
        this.list = (ArrayList<String>) list;
        this.rev = rev;
        this.comp = comp;
        start();
    }

    @Override
    public void run() {
        super.run();
        bubbleSort(this.rev);
        this.comp.onComplete(this.list);
    }

    public static void bubbleSort(boolean rev) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (rev ? (list.get(j).length() > list.get(j + 1).length()) : (list.get(j).length() < list.get(j + 1).length())) {
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
