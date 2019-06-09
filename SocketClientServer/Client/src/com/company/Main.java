package com.company;

import java.io.*;
import java.net.Socket;

public class Main {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static boolean isWork = true;
    private static String mode = "";
    private static char sel;

    public static void main(String[] args)
    {
        try
        {
            try
            {
                clientSocket = new Socket("localhost", 27001);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


                class ReadMsg extends Thread {
                    @Override
                    public void run() {
                        String str;
                        try {
                            while (true) {
                                str = in.readLine();

                                switch (str) {
                                    case "menu":
                                        mode = str;
                                        str = in.readLine();
                                        System.out.println(str);
                                        this.menuAns(str);
                                        break;
                                    default:
                                        System.out.println(str);
                                        break;
                                }

//                                if (str.equals("stop"))
//                                {
//                                    isWork = false;
//                                    break;
//                                }
                            }
                        }
                        catch (IOException e) {}
                    }

                    private void menuAns(String str) {
                        switch (sel) {
                            case '1':
                                if (str.equals("")) {
                                    System.out.println("No user online!");
                                } else {
                                    System.out.println(str.replace('|', '\n'));
                                }
                                System.out.println("------------------------------------\n");
                                break;
                            case '2':
                                break;
                            case '3':
                                break;
                            case '4':
                                System.out.println(str);
                                break;
                            case '5':
                                break;
                        }
                    }
                }

                //
                class WriteMsg extends Thread {
                    @Override
                    public void run() {
                        while (true) {
                            String userWord;
                            try
                            {
                                userWord = reader.readLine();

                                if (mode.equals("menu")) {
                                    switch (userWord) {
                                        case "1":
                                            sel = '1';
                                            break;
                                        case "2":
                                            sel = '2';
                                            break;
                                        case "3":
                                            sel = '3';
                                            break;
                                        case "4":
                                            sel = '4';
                                            break;
                                        case "5":
                                            sel = '5';
                                            break;
                                    }
                                }

                                out.write(userWord + "\n");
                                out.flush();

//                                if (userWord.equals("stop"))
//                                {
//                                    out.write("stop" + "\n");
//                                    isWork = false;
//                                    break;
//                                }
//                                else {
//                                    out.write(userWord + "\n");
//                                }
//                                out.flush();
                            } catch (IOException e) {

                            }

                        }
                    }
                }

                new ReadMsg().start();
                new WriteMsg().start();

                while(isWork){}

            }
            finally
            {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}