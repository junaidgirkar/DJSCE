//package OS.exp2;

import java.io.*;
import java.net.*;

class Client {
    public static void main(String[] args) throws Exception {
        String msg;
        Socket s2 = new Socket("localhost", 80);

        String names[] = { "JUNAID", "JUNAID_GIRKAR" };
        int i = (int) (Math.random() * (names.length));
        msg = names[i];

        PrintStream d1 = new PrintStream(s2.getOutputStream());
        d1.println(msg);

        BufferedReader d2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
        String resp = d2.readLine();

        System.out.println("\nServer response: " + resp);
    }
}