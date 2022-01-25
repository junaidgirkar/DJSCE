Class server 

 

  public void setRecPort(int recPort)    

  {         this.recPort=recPort;     }     

 public void sendData()throws Exception      

{         BufferedReader br;          

DatagramSocket ds;          

DatagramPacket dp;          

String data="";         

 System.out.println("Enter the Response 'VOTE_COMMIT' || 'VOTE_ABORT' ");          

br=new BufferedReader(new InputStreamReader(System.in));         

 data = br.readLine();         System.out.println("Data is "+data);                  

ds=new DatagramSocket(sendPort);          

dp=new DatagramPacket(data.getBytes(),data.length(),lclhost,sendPort-1000);         

 ds.send(dp);          

ds.close();             }  

public void recData()throws Exception          

{             byte buf[]=new byte[256];              

DatagramPacket dp;              

DatagramSocket ds;                          

ds=new DatagramSocket(recPort);              

dp=new DatagramPacket(buf,buf.length);             

 ds.receive(dp);              

ds.close();              

String msgStr=new String(dp.getData(),0,dp.getLength());              

System.out.println("Client1 data " +msgStr);                 }        }; 

 
