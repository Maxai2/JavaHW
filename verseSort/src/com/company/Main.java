package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> verse = new ArrayList<>();

        while (true) {
            String str = scan.nextLine();

            if (str.equals("")) {
                break;
            }
            verse.add(str);
//            str = "";
        }


    }
}
