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
            rename();
            while (true) {
                this.send("menu");

                switch (menu()) {
                    case 1: // onlineUsers
                        showOnline();
                        break;
                    case 2: // msg
                        break;
                    case 3: // group
                        break;
                    case 4: // rename
                        break;
                    case 5: // exit
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

    private void send(String msg)
    {
        try
        {
            out.write(msg + "\n");
            out.flush();
        }
        catch (IOException ignored) {}
    }

    private void showOnline() {
        String userList = "";
        for (ServerSomthing vr : Server.serverList)
        {
            if(vr.socket != this.socket)
            {
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
        send("Input your nickname: ");

    }

    private int menu() {
        int sel = 0;
        try {
            sel = in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str = "1) Show online\n" +
                "2) Send message\n" +
                "3) Group\n" +
                "4) Rename\n" +
                "5) Exit\n" +
                "Your choice input number(1-5): ";

        this.send(str);
        return sel;
    }
}