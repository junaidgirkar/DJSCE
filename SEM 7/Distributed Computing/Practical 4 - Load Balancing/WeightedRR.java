import java.util.*;

public class WeightedRR {

    public static int[] removeTheElement(int[] arr, int index)
    {
 
        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null || index < 0
            || index >= arr.length) {
 
            return arr;
        }
 
        // Create another array of size one less
        int[] anotherArray = new int[arr.length - 1];
 
        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {
 
            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }
 
            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }
 
        // return the resultant array
        return anotherArray;
    }
 

    public static void LoadBalancer(int servers_count, int[] servers_weight, int[] servers_assigned_count, int processes_count){

        while(processes_count != 0){
            for (int k =0; k < servers_count; k++){
                if(servers_weight[k] < processes_count){
                    servers_assigned_count[k] += servers_weight[k];
                    processes_count -= servers_weight[k];
                }
                else{
                    servers_assigned_count[k] += processes_count;
                    processes_count = 0;
                }
            }
        }

        System.out.println("\n");
        for (int l = 0; l < servers_count; l++){
            System.out.println("Server " + l + " has " + servers_assigned_count[l] + " Processes");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Servers: ");
        int servers_count = sc.nextInt();
        int[] servers_weight = new int[servers_count];
        int[] servers_assigned_count = new int[servers_count];


        int i = 0;
        for(i=0; i<servers_count; i++){
            System.out.print("Enter Server No " + i + "'s Weight: ");
            int server_weight_input = sc.nextInt();
            servers_weight[i] = server_weight_input;
        }
        System.out.print("Enter Number of Processes: ");
        int processes_count = sc.nextInt();

        LoadBalancer(servers_count, servers_weight, servers_assigned_count, processes_count);
        
        System.out.println();
        while(true){
            System.out.println("1. Add 1 Server\n2. Remove 1 Server\n3. Add Processes\n4. Remove Processes\n5. Exit");
            switch(sc.nextInt()){
                case 1:
                    System.out.println("Enter New Server's Weight : ");
                    int additional_server_weight = sc.nextInt();
                    int[] incremented_servers_weight = new int[servers_weight.length + 1];
                    System.arraycopy(servers_weight, 0, incremented_servers_weight, 0, servers_weight.length);
                    int current_servers_count = incremented_servers_weight.length;
                    servers_weight = incremented_servers_weight;
                    servers_weight[current_servers_count-1] = additional_server_weight;
                    System.out.print(incremented_servers_weight);

                    int[] incremented_servers_assigned_count = new int[servers_assigned_count.length + 1];
                    System.arraycopy(servers_assigned_count, 0, incremented_servers_assigned_count, 0, servers_assigned_count.length);
                    int current_servers_assigned_count = incremented_servers_assigned_count.length;
                    servers_assigned_count = incremented_servers_assigned_count;
                    servers_assigned_count[current_servers_assigned_count-1] = 0;

                    LoadBalancer(servers_count+1, servers_weight, servers_assigned_count, processes_count);
                    break;
                case 2:
                    System.out.println("Enter the Server Index to be removed : ");
                    int index_of_removed_server = sc.nextInt();
                    LoadBalancer(servers_count-1, removeTheElement(servers_weight, index_of_removed_server) , removeTheElement(servers_assigned_count, index_of_removed_server), processes_count);
                    break;
                case 3:
                    System.out.println("How many processes to be added : ");
                    int no_of_additional_processes = sc.nextInt();
                    LoadBalancer(servers_count, servers_weight, servers_assigned_count, processes_count+no_of_additional_processes);
                    break;
                case 4:
                    System.out.println("How many processes to be removed : ");
                    int no_of_processes = sc.nextInt();
                    LoadBalancer(servers_count, servers_weight, servers_assigned_count, processes_count-no_of_processes);
                    break;
                case 5:
                    break;
            }

        }

    }
}
