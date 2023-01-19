import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {

  private static Vector<PrintWriter> writers = new Vector<PrintWriter>();

  public static void main(String[] args) throws Exception {
    ServerSocket listener = new ServerSocket(9001);
    System.out.println("The server is running at port 9001.");
    while (true)
      new Handler(listener.accept()).start();
  }

  private static class Handler extends Thread {

    private Socket socket;

    public Handler(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("SUBMITNAME");
        String name = in.readLine();
        System.out.println(name + " joined");
        writers.add(out);
        while (true) {
          String input = in.readLine();
          for (PrintWriter writer : writers)
            writer.println(
                "MESSAGE " + name + ": " + input);
        }
      } catch (Exception e) {
        System.err.println(e);
      }
    }
  }
}
