//package OS.exp2;

import java.io.*;
import java.net.*;

class manageClient extends Thread {
    private Socket s;
    private String name;

    manageClient(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader d2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.name = d2.readLine();

            System.out.println("\nClient Name: " + this.name);

            PrintStream d1 = new PrintStream(s.getOutputStream());
            d1.println("You are connected " + this.name);
        } catch (Exception e) {
            System.out.println("\n [!] Thread of client: " + this.name + " was interrupted.");
        }
    }
}

class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server...\n");
        ServerSocket socket = new ServerSocket(80);
        try {
            while (true) {
                Socket s1 = socket.accept();

                System.out.println("\nClient connected");

                Thread T1 = new manageClient(s1);
                T1.start();
            }
        } catch (Exception e) {
            System.out.println(e);
            socket.close();
        }
    }
}