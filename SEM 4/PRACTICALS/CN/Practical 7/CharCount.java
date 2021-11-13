import java.util.Scanner;

public class CharCount
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number of Frames :");
		int n = sc.nextInt();
		String data[] = new String[n];
		for(int i=0;i<n;i++){
		    System.out.print("Enter Data: ");
		    data[i] = sc.next();
		}
		String d = sender(data);
		String data2[] = receiver(n);
		System.out.println("Output: ");
		for(int i=0;i<data2.length;i++){
		    System.out.println(data2[i]);
		}
	}
	
	public static String sender(String data[]){
	    String d="";
	    for(int i=0;i<data.length;i++){
	        d+=(data[i].length()+1)+data[i];
	    }
	    System.out.println("Output: "+d);
	    return d;
	}
	public static String[] receiver(int n){
        System.out.print("Enter Received Data : ");
        Scanner sc = new Scanner(System.in);
        String d = sc.nextLine();
        System.out.println("\n");
	    //System.out.println("Reveived Data: "+d);
	    String data[] = new String[n];
	    for(int i=0;i<n;i++){
	        data[i]="";
	    }
	    int j=-1;
	    int target=0;
	    for(int i=0;i<d.length();i++){
	        if(i==target){
	            target+=Integer.parseInt(String.valueOf(d.charAt(i)));
	            j++;
	            if(target>=d.length()) target=d.length();
	        }
	        else{
	            data[j]+=d.charAt(i);
	        }
	    }
	    return data;
	}
}
