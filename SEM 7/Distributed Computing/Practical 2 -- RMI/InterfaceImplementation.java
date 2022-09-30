// Implementing the remote interface 
public class InterfaceImplementation implements AdditionInterface {  
   
   // Implementing the interface method 
   public void addTwoNumbers(int a, int b) {  
        System.out.println("The sum of " + a + " and " + b + " is: " + (a + b));
   } 
}