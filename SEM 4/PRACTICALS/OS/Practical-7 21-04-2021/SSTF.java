import java.util.Scanner;

class node {
      
    int distance = 0; 
    boolean accessed = false; 
}
  
public class SSTF {
      
    // Calculates difference of each track number with the head position
    public static void calculateDifference(int queue[], 
                                        int head, node diff[])
                                          
    {
        for (int i = 0; i < diff.length; i++)
            diff[i].distance = Math.abs(queue[i] - head);
    }

    public static int findMin(node diff[])
    {
        int index = -1, minimum = Integer.MAX_VALUE;
  
        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {
                  
                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }
  
    public static void shortestSeekTimeFirst(int request[],int head)
                                                       
    {
        if (request.length == 0)
            return;
              
        // create array of objects of class node    
        node diff[] = new node[request.length]; 

        for (int i = 0; i < diff.length; i++) 
          
            diff[i] = new node();
 
        int seek_count = 0; 
          
        // stores sequence in which disk access is done
        int[] seek_sequence = new int[request.length + 1]; 
          
        for (int i = 0; i < request.length; i++) {
              
            seek_sequence[i] = head;
            calculateDifference(request, head, diff);
              
            int index = findMin(diff);
              
            diff[index].accessed = true;
            seek_count += diff[index].distance; 
            head = request[index]; 
        }
          
        // for last accessed track
        seek_sequence[seek_sequence.length - 1] = head; 
          
        System.out.println("Total number of seek operations = " 
                                                     + seek_count);
                                                       
        System.out.println("Seek Sequence is");
          
        // print the sequence
        for (int i = 0; i < seek_sequence.length; i++) 
            System.out.print(seek_sequence[i] + " -> ");
            
    }
  
    public static void main(String[] args)
    {
        int n;  
        Scanner sc=new Scanner(System.in);  
        System.out.print("Enter the number of elements you want to store: ");   
        n=sc.nextInt();  

        int[] arr = new int[n];  
        System.out.print("Enter the elements of the array: ");  

        for(int i=0; i<n; i++)  
        {  
            arr[i]=sc.nextInt();  
        }  

        System.out.print("Enter Initial Head Position: ");
        int head_pos = sc.nextInt();
        shortestSeekTimeFirst(arr, head_pos);
    }
}