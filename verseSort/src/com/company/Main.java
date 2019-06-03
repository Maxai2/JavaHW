package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> verse = new ArrayList<>();
    static ArrayList<String> verseSorted = new ArrayList<>();
    private static BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
    static boolean ready = false;
    static boolean start = false;
    static String sortInfo = "";

//    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        newVerse();

        while (true) {
            if (ready) {
                System.out.println("-------------------------------------------");
                System.out.println("Your text is sorted " + sortInfo);
                System.out.print("Show?(y/n): ");
                Character ans = null;
                try {
                    ans = (char)System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (ans.equals('y')) {
                    threadAns(verseSorted);
                    ready = false;
                }
            }

//            System.out.println("-------------------------------------------");
//            newVerse();

            System.out.println("\nChoose method of sort verse:");
            System.out.println("1) Quick sort");
            System.out.println("2) Bubble sort");
            System.out.print("Your choice(1/2): ");
            int ch = 0;
            try {
                ch = Integer.parseInt(scan.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n1) Low to high");
            System.out.println("2) High to low");
            System.out.print("Your choice(1/2): ");
            int rev = 0;
            try {
                rev = Integer.parseInt(scan.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (ch == 1) {
                if (rev == 1) {
                    Thread t = new quickSortCl(verse.clone(), true, (verseNew)->{
                        sortInfo = "Quick sort | Low to high";
                        verseSorted = verseNew;
                        ready = true;
                    });
                } else if (rev == 2) {
                    new quickSortCl(verse.clone(), false, (verseNew)->{
                        sortInfo = "Quick sort | High to low";
                        verseSorted = verseNew;
                        ready = true;
                    });
                }
            } else if (ch == 2) {
                if (rev == 1) {
                    new bubbleSortCl(verse.clone(), true, (verseNew)->{
                        sortInfo = "Bubble sort | Low to high";
                        verseSorted = verseNew;
                        ready = true;
                    });
                } else if (rev == 2) {
                    new bubbleSortCl(verse.clone(), false, (verseNew)->{
                        sortInfo = "Bubble sort | High to low";
                        verseSorted = verseNew;
                        ready = true;
                    });
                }
            } else {
                System.out.println("Choose correct!");

            }
        }
    }

    public static void newVerse() {
        System.out.println("Paste the verse:");
        verse.clear();
        while (true) {

            String str = null;
            try {
                str = scan.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (str.equals("")) {
                break;
            }
            verse.add(str);
        }
    }

    public static void threadAns(ArrayList<String> newVerse) {
        System.out.println("\n");
        for (String row : newVerse) {
            System.out.println(row);
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
