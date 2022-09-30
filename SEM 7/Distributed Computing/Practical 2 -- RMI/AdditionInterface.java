import java.rmi.Remote; 
import java.rmi.RemoteException;  

// Creating Remote interface for our application 
public interface AdditionInterface extends Remote {  
   void addTwoNumbers(int a, int b) throws RemoteException;  
} 