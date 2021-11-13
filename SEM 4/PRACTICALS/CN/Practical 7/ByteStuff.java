// Byte Stuff
import java.util.Scanner;

public class ByteStuff
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Enter Flag Pattern: ");
	    char flag = sc.next().charAt(0);
	    System.out.print("Enter Escape Pattern: ");
	    char escape = sc.next().charAt(0);
		System.out.print("Enter Number of Frames :");
		int n = sc.nextInt();
		String data[] = new String[n];
		for(int i=0;i<n;i++){
		    System.out.print("Enter Data: ");
		    data[i] = sc.next();
		}
		String d = sender(data,flag,escape);
		String data2[] = receiver(d,n,flag,escape);
		System.out.println("Output: ");
		for(int i=0;i<data2.length;i++){
		    System.out.println(data2[i]);
		}
	}
	
	public static String sender(String data[],char flag,char escape){
	    String d="";
	    for(int i=0;i<data.length;i++){
	        d+=flag;
	        for(int j=0;j<data[i].length();j++){
	            if(data[i].charAt(j)==flag){
	                d+=""+escape+""+flag+""+escape;
	            }
	            else{
	                d+=data[i].charAt(j);
	            }
	        }
	        d+=flag;
	    }
	    System.out.println("Output: "+d);
	    return d;
	}
	public static String[] receiver(String d,int n,char flag,char escape){
	    System.out.println("Reveived Data: "+d);
	    String data[] = new String[n];
	    for(int i=0;i<n;i++){
	        data[i]="";
	    }
	    int j=-1;
	    for(int i=0;i<d.length();i++){
	        if(d.charAt(i)==flag){
	            if((i!=0 && i!=d.length()-1) && (d.charAt(i-1)==escape && d.charAt(i+1)==escape)){
	                data[j]+=d.charAt(i);
	            }
	            else{
	                j++;
	            }
	        }
	        else{
	            data[j]+=d.charAt(i);
	        }
	    }
	    
	    return data;
	}
}
