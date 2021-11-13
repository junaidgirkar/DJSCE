//Multiple try and catch
//Catching the arithmaticException

import java.io.*;
class Divide
{
    public static void main(String args[]) throws IOException
    {
        int a=0,b=0,res;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter two numbers");
        try
        {
            str = br.readLine();
            a = Integer.parseInt(str);
            str = br.readLine();
            b = Integer.parseInt(str);
            
        }
        catch(NumberFormatException e)
        {
            System.out.println("Please Enter Numbers");
        }
        try
        {
            res = a/b;
            System.out.println("Quotient is "+res);
        }
        catch(ArithmeticException e)
        {
            System.out.println("Arithmetic Exception occured, please do not enter denominator as zero");
        }
    }
}