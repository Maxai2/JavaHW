package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static short stickCount = 15;
    public static boolean myTurn;
    public static boolean win = false;

    public static void main(String[] args) {
        println("Welcome to the " + stickCount + " sticks game!", true);

        Scanner scan = new Scanner(System.in);

        while (true) {

            if (win) {

                println("Do you want reapeat?(y/n):", true);
                String ans = scan.next();
                if (ans == "n") {
                    return;
                } else {
                    win = false;
                }
            }
            myTurn = turn();

            while (true) {
                if (myTurn) {
                    println("Your turn!", true);

                    while (true) {
                        println(showSticks(), true);
                        println("Input count of sticks(1-3): ", false);
                        byte cnt = scan.nextByte();

                        if (stickCount - cnt == 1) {
                            println("\nUser WIN, CONGRATULATIONS!", true);
                            win = true;
                        } else if (stickCount - cnt <= 0) {
                            println("IMPOSSIBLE!", true);
                        } else if (stickCount - cnt > 1) {
                            stickCount -= cnt;
                            break;
                        }
                    }

                } else {
                    println("My turn!", true);

                    int cntB = 0;
                    if (stickCount >= 14) {
                        cntB = botTurn(stickCount, 13);
                    } else if (stickCount >= 10) {
                        cntB = botTurn(stickCount, 9);
                    } else if (stickCount >= 6) {
                        cntB = botTurn(stickCount, 5);
                    } else if (stickCount >= 2) {
                        cntB = botTurn(stickCount, 1);
                    } else  if (stickCount == 1) {
                        println("\nBot WIN, CONGRATULATIONS!", true);
                        win = true;
                    }

                    stickCount -= cntB;
                    println("Bot take: " + cntB + " stick", true);
                }

                if (win) {
                    break;
                }

                println(showSticks(), true);
                println("------------------------------", true);
                myTurn = !myTurn;
            }
        }
    }

    public static int botTurn(int curNum, int critNum) {
        int cntB = 0;

        for (int i = 0; i < 3; i++) {
            curNum--;
            cntB++;
            if (curNum == critNum) {
                break;
            }
        }

        return  cntB;
    }

    public static void println(String str, boolean newLine) {
        if (newLine) {
            System.out.println(str);
        } else {
            System.out.print(str);
        }
    }

    public static boolean turn() {
        final Random rand = new Random();

        return rand.nextBoolean();
    }

    public static String showSticks() {
        String stickC = "\t";

        for (int i = 0; i < stickCount; i++) {
            stickC += "|";
        }

        stickC += " (" + stickCount + ")";

        return stickC;
    }
}

