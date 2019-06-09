package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

class Server {

    public static final int PORT = 27001;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(PORT);
        try
        {
            while (true)
            {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket));
                }
                catch (IOException e)
                {
                    socket.close();
                }
            }
        }
        finally
        {
            server.close();
        }
    }
}

//===================================================================================

class ServerSomthing extends Thread {

    private Socket socket;
    private String nickname;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String word;

//        try {
            this.send("Connected successfully!");
            this.rename();

            while (true) {
                this.send("menu");

                switch (menu()) {
                    case 1: // onlineUsers
                        this.showOnline();
                        break;
                    case 2: // msg
                        break;
                    case 3: // group
                        break;
                    case 4: // rename
                        this.rename();
                        break;
                    case 5: // exit
                        this.logoutUser();
                        break;
                }

//              if(word.equals("stop"))
//              {
//                  break;
//              }

            }
//        }
//        catch (IOException e) {}
    }

    private void logoutUser() {
        this.send("");
        Server.serverList.remove(this.socket);
    }

    private void send(String msg)
    {
        try {
            this.out.write(msg + "\n");
            this.out.flush();
        }
        catch (IOException ignored) {}
    }

    private void showOnline() {
        String userList = "";
        for (ServerSomthing vr : Server.serverList) {
            if(vr.socket != this.socket) {
                userList += vr.nickname + "|";
            }
        }

        this.send(userList);
    }

    private void messageWork() {

    }

    private void groupWork() {

    }

    private void rename() {
        String newNick;
        this.send("\nInput nickname: ");
        try {
            newNick = this.in.readLine();
            this.nickname = newNick;
            this.send("Nickname is changed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int menu() {
        String str = "\n1) Show online\n" +
                "2) Send message\n" +
                "3) Group\n" +
                "4) Rename\n" +
                "5) Exit\n" +
                "Your choice input number(1-5): ";
        this.send(str);

        int sel = 0;
        try {
//            sel = this.in.read() - '0';
            sel = Integer.parseInt(this.in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sel;
    }
}