package server;

import server.Exeptions.BadRequestException;
import server.commands.Parser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


 public class Server implements Runnable {

    private ServerSocket serverSocket;
    private Thread thread;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");
            System.out.println("Waiting for a client...");
            thread = new Thread(this);
            thread.start();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    @Override
    public void run() {
        try {
            while (thread != null) {
                Socket socket = serverSocket.accept();
                System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                DataInputStream dis = new DataInputStream(bis);

                String command = "";
                while (true) {
                    try {
                        command = dis.readUTF();
                        String response = new Parser(command).parseCommand();
                        if (command.equals("exit")) {
                            break;
                        }
                        System.out.println("Client [" + socket.getRemoteSocketAddress() + "] : " + response);
                    } catch (BadRequestException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        break;
                    }
                }
                dis.close();
                socket.close();
                System.out.println("Client " + socket.getRemoteSocketAddress() + " disconnect from server...");
            }
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String[] args) {
        new Server(5000);
    }
}



