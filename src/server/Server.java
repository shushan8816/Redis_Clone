package server;

import server.commands.Parser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {

    private Socket socket ;
    private ServerSocket serverSocket ;
    private BufferedInputStream bis ;
    private DataInputStream dis ;
    private Thread thread ;

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
                socket = serverSocket.accept();
                System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");
                bis = new BufferedInputStream(socket.getInputStream());
                dis = new DataInputStream(bis);

                String command = "";
                while (true) {
                    try {
                        command = dis.readUTF();
                        String response;
                        response = new Parser(command).parseCommand();
                        if (command.equals("exit")) {
                            break;
                        }
                        System.out.println("Client [" + socket.getRemoteSocketAddress() + "] : " + response);
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

    public static void main(String args[]) {
         new Server(5000);
    }
}



