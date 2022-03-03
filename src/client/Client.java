package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class Client {

    public Client(String ip, int port) throws Exception {
        Socket clientSocket = new Socket(ip, port);

        System.out.println("Welcome to Redis store.");
        System.out.println("Connected to server " + clientSocket.getRemoteSocketAddress());
        System.out.println("Write your Redis commands...");

        DataInputStream dis = new DataInputStream(System.in);
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

        String command = "";

        while (!command.equalsIgnoreCase("exit")) {
            command = dis.readLine();
            dos.writeUTF(command);
        }

        dis.close();
        dos.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws Exception {
        new Client("127.0.0.1", 5000);
    }
}

