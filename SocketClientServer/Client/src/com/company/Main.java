package com.company;

import java.io.*;
import java.net.Socket;

public class Main {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static boolean isWork = true;

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

                //
                class ReadMsg extends Thread {
                    @Override
                    public void run() {

                        String str;
                        try {
                            while (true) {
                                str = in.readLine();
                                System.out.println(str);







//                                if (str.equals("stop"))
//                                {
//                                    isWork = false;
//                                    break;
//                                }
                            }
                        }
                        catch (IOException e) {}
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


                                if (userWord.equals("stop"))
                                {
                                    out.write("stop" + "\n");
                                    isWork = false;
                                    break;
                                }
                                else {
                                    out.write(userWord + "\n");
                                }
                                out.flush();
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