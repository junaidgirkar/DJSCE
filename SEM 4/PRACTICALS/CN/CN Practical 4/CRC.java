import java.util.*;
class CRC{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String data,generator,tcode;
		System.out.println("Enter Generator code(g(x)):");
		generator=sc.nextLine();
		System.out.println("Enter Data code(m(x)):");
		data=sc.nextLine();
		String dividend=data;
		String divisor=generator;
		while(dividend.length()<(data.length()+generator.length()-1))
		{
			dividend=dividend+"0";
		} 
		tcode=data+division(dividend,divisor);
        /*
        System.out.println("Enter the message you need to check: ");
        String checking_message = sc.nextLine();
        if(checking_message==tcode)
        {
            System.out.println("SUCCESS");
        }
        else{
            System.out.println("FAILURE");
            System.out.println("The correct output is: "+ tcode);
        }
		//*/System.out.println("Transmitted Code Word is:"+tcode);
	}
	static String division(String n1, String n2){
		int i;
		int len= n2.length();
        String itr= n1.substring(0,len);
        String rem = "";
        for (i = 0; i < n2.length(); i++){
        	if(itr.charAt(i)==n2.charAt(i)){
        		rem+="0";
        	}
        	else{
        		rem+="1";
        	}
        }
        while(len<n1.length()){
        	if(rem.charAt(0)=='0'){
        		rem=rem.substring(1,rem.length());
        		rem=rem+String.valueOf(n1.charAt(len));
        		len++;
        	}
            itr=rem;
            rem="";
            for(i=0; i<n2.length(); i++)
        	  {
        	  	if(itr.charAt(i)==n2.charAt(i)){
        	  		rem+="0";
        	  	}
        	  	else{
        	  		rem+="1";
        	  	}
        	
        }

    }
	return rem.substring(1,rem.length());
}
}