// Client side Code
import java.io.*;
import java.net.*;
class TCPClient{
    public static void main(String[] args) throws Exception{
        String msg;
        Socket s2 = new Socket("localhost", 80);
        BufferedReader d2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
        msg = d2.readLine();
        System.out.println("Message is: " + msg);
        s2.close();
    }
}