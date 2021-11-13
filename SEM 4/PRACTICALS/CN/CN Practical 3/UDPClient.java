//UDPClient.java File
import java.io.*;
import java.net.*;
class UDPClient
{
    public static void main(String[] args) throws Exception
    {
        byte rData[] = new byte[1024];
        byte sData[] = new byte[1024];
        String msg = "";
        DatagramSocket cs = new DatagramSocket();
        sData=msg.getBytes();
        InetAddress ipadd = InetAddress.getByName("localhost");
        DatagramPacket sPacket = new DatagramPacket(sData, sData.length, ipadd, 9876);
        cs.send(sPacket);
        DatagramPacket rPacket = new DatagramPacket(rData, rData.length);
        cs.receive(rPacket);
        msg=new String(rPacket.getData());
        System.out.println(msg);
        cs.close();
    }
}
