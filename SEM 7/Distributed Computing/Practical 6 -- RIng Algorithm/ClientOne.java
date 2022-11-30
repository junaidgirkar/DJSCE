import java.io.*;
import java.net.*;

public class ClientOne {

  public static void main(String args[]) throws IOException {
    Socket s = new Socket("localhost", 7000);
    PrintStream out = new PrintStream(s.getOutputStream());
    ServerSocket ss = new ServerSocket(7001);
    Socket s1 = ss.accept();
    BufferedReader in1 = new BufferedReader(
      new InputStreamReader(s1.getInputStream())
    );
    PrintStream out1 = new PrintStream(s1.getOutputStream());
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = "Token";
    while (true) {
      if (str.equalsIgnoreCase("Token")) {
        System.out.println("Do you want to send some data");
        System.out.println("Enter Yes or No");
        str = br.readLine();
        if (str.equalsIgnoreCase("Yes")) {
          System.out.println("Enter the data");
          str = br.readLine();
          out.println(str);
        }
        out1.println("Token");
      }
      System.out.println("Waiting for Token");
      str = in1.readLine();
    }
  }
}