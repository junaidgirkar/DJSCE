import java.util.Scanner;
class CRC2{
public static void main(String args[]){
Scanner sc = new Scanner(System.in);
//Input Data Stream
//System.out.println("\n");
System.out.println("*******   TRANSMITTER SIDE    *******");
System.out.print("Enter data stream: ");
String datastream = sc.nextLine();
System.out.print("Enter generator: ");
String generator = sc.nextLine();
int data[] = new int[datastream.length() - 1 + generator.length()];
int divisor[] = new int[generator.length()];
for(int i=0;i<datastream.length();i++)
data[i] = Integer.parseInt(datastream.charAt(i)+"");
for(int i=0;i<generator.length();i++)
divisor[i] = Integer.parseInt(generator.charAt(i)+"");

//Calculation of CRC
for(int i=0;i<datastream.length();i++){
if(data[i]==1)
for(int j=0;j<divisor.length;j++)
data[i+j] ^= divisor[j];
}

//Display CRC
System.out.print("The CRC code is: ");
for(int i=0;i<datastream.length();i++)
data[i] = Integer.parseInt(datastream.charAt(i)+"");
for(int i=0;i<data.length;i++) System.out.print(data[i]);
System.out.println();
System.out.println("*******   RECEIVER SIDE   *******");
//Check for input CRC code
System.out.print("Enter CRC code: ");
datastream = sc.nextLine();
System.out.print("Enter generator: ");
generator = sc.nextLine();
data = new int[datastream.length() + generator.length() - 1];
divisor = new int[generator.length()];
for(int i=0;i<datastream.length();i++)
data[i] = Integer.parseInt(datastream.charAt(i)+"");
for(int i=0;i<generator.length();i++)
divisor[i] = Integer.parseInt(generator.charAt(i)+"");

//Calculation of remainder
for(int i=0;i<datastream.length();i++){
if(data[i]==1)
for(int j=0;j<divisor.length;j++)
data[i+j] ^= divisor[j];
}

//Display validity of data
boolean valid = true;
for(int i=0;i<data.length;i++)
if(data[i]==1){
valid = false;
break;
}

if(valid==true) System.out.println("\nData stream is valid");
else System.out.println("\nINVALID. CRC error occured.");
sc.close();
}
}