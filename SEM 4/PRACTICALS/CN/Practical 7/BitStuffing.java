import java.util.*;
public class BitStuffing{
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter flag pattern :");
        String flag = sc.nextLine();
        System.out.println("Enter data : ");
        String data = sc.nextLine();
        checkBit(data, flag);
        System.out.println("enter stuffed data from client");
        String stuffedData = sc.nextLine();
        SeperateBits(stuffedData);
        
    }
    static void SeperateBits(String stuffedData){
        System.out.println("Stuffed data from client: " + stuffedData);
        int count=0; 
        for(int i=8;i<stuffedData.length()-8;i++){
            char ch = stuffedData.charAt(i);
            if(ch=='1'){
            count++;
            System.out.print(ch);
            if(count==5){
                i++;
                count=0;
            }
            }else{
                System.out.print(ch);
                count=0;
            }
        }
        System.out.println(); 
    }
    static void checkBit(String data, String flag){
        int countOne=0;
        String s ="";
        for(int i=0;i<data.length();i++){
            char ch = data.charAt(i);
            if(ch=='1'){
               countOne++;
               if(countOne<5)
               s  =s+ch;
               else{
                   s = s+ ch+'0';
                   countOne=0;
               }
            }else{
                s = s+ch;
                countOne=0;
            }
        }
        s = flag + s +flag;
        
        System.out.println("Data stuffed in client: " + s); 
    }
}