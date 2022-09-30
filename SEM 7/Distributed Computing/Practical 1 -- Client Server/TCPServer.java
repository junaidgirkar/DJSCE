// Server side code
import java.io.*;
import java.net.*;
import java.util.*;
class TCPServer{
    public static void main(String args[]) throws Exception {
        String outmsg;
        ServerSocket ss1 = new ServerSocket(80);
        while(true){
            Socket s1 = ss1.accept();
            String m[] = {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
            int i = (int)(Math.random() * m.length);
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Message to be transmitted");
            outmsg = sc.nextLine();
            // outmsg = m[i];
            PrintStream d1 = new PrintStream(s1.getOutputStream());
            d1.println(outmsg);
        }
    }
}