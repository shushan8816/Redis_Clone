package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {

    private String serverName = null;
    private int serverPort ;
    private Socket socket = null;
    private DataOutputStream dos = null;
    private DataInputStream dis = null;

    public Client(String serverName, int serverPort) {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Client started on port " + socket.getLocalPort() + "...");
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());
            System.out.println("Write your Redis commands...");
            dis = new DataInputStream(System.in);
            dos = new DataOutputStream(socket.getOutputStream());

            String command = "";

            while (true) {
                try {
                    if (command.equals("exit")) {
                        break;
                    }
                    command = this.dis.readLine();
                    dos.writeUTF(command);
                    dos.flush();
                } catch (IOException e) {
                    break;
                }
            }
            dos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void main(String args[]) {

        Client client = new Client("127.0.0.1",8000);
    }
}

