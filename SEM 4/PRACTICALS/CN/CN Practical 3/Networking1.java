//package Networking1;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Networking1
{

    public static void main(String[] args)
    {
        try{
            InetAddress ipAdd = InetAddress.getLocalHost();
            System.out.println("Local Host "+ipAdd);
            System.out.println("Machine Name "+ipAdd.getHostName());
            System.out.println("IP Address "+ipAdd.getHostAddress());

        }
        catch(UnknownHostException e){
            e.printStackTrace();
        }
    }
}