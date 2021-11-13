package letmeadd;

public class Calculator {
   public int add(int a, int b){
	return a+b;
   }
   public static void main(String args[]){
	Calculator obj = new Calculator();
	System.out.println(obj.add(10, 20));
   }
}