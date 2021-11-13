import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.midi.Sequencer;

import java.io.*;

public class Cuboid {
    public static void main(String[] args) throws IOException
    {
        /*try*/{
            String outmsg;
            Socket ss1 = new Socket("localhost",8080);
            BufferedReader d2 = new BufferedReader(new InputStreamReader((ss1.getInputStream())));
            outmsg = d2.readLine();
            System.out.println("Message is "+outmsg);
            ss1.close();
        }
        /*
        catch(IOException e)
        {
            e.printStackTrace();
        }
        */
    }
}
