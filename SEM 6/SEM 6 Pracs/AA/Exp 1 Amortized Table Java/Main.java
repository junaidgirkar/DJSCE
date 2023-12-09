import java.util.*;

public class Main
{
    public void printArr(int[] arr, double total, int elem){
        System.out.println(Arrays.toString(arr) + "\tLength: " + arr.length + "\tAmortized Cost: " + (total / elem));
    }
    
	public static void main(String[] args) {
	    Main obj = new Main();
	    int[] arr = new int[1];
	    int counter = 0, length = arr.length;
	    Scanner sc = new Scanner(System.in);
	    int inp = 1;
	    double total = 0.0;
	    ArrayList<Integer> cost = new ArrayList<Integer>();
	    
	   // long start = System.currentTimeMillis();

	    while(true){
	       // System.out.print("Enter Element: ");
	        inp = sc.nextInt();
	        
	        if(inp == -1)
	            break;
	        
	        if(counter < length){
	            arr[counter++] = inp;
	            cost.add(1);
	            total += 1;
	        } else {
	            System.out.println("Double Size");
	            cost.add(counter + 1);
	            total += counter + 1;
	            int[] temp = arr.clone();
	            arr = new int[length * 2];
	            System.arraycopy(temp, 0, arr, 0, length);
	            arr[counter++] = inp;
	            length = length * 2;
	        }
	        obj.printArr(arr, total, counter);
	    }
        // long end = System.currentTimeMillis();
        // long elapsedTime = end - start;
        
        // System.out.println("Time: " + elapsedTime / ((counter + 1)) + "ms");
        System.out.println("Cost: " + cost + "\nCost per Element: " + (total/counter));
	}
}
