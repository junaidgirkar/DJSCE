import java.util.Scanner;
class digitCount
{
public static void main(String args[])
{
int n,count=0;
Scanner scan=new Scanner(System.in);
System.out.print("Enter a number:");
n=scan.nextInt();
while(n!=0)
{
n=n/10;
count++;
}
System.out.print("The number of digits in the entered no.is "+count);
}
}