import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.time.*;

public class sjfNonPreemptive {

   static int[] at= new int[50] ;
   static int[] bt= new int[50] ;
   static int[] tat= new int[50] ;
   static int[] wt= new int[50] ;
   static int[] process_order = new int[50] ;
   static boolean[] process_status= new boolean[50] ;  // false => executed

   static int[] processID = new int[50] ;
   static int num , total_tat=0 , total_wt=0 ;
   static double avg_tat=0.00 , avg_wt=0.00;

    
    public static void sortArrayByAT(int processID[] ,int at[] , int bt[]){

        for (int i = 1; i <num;  ++i) {
            int at_key = at[i];
            int bt_key = bt[i];
            int process_key = processID[i];

            int hole = i - 1;
            while (hole >= 0 && at[hole] > at_key) {
                at[hole + 1] = at[hole];
                bt[hole + 1] = bt[hole];
                processID[hole + 1] = processID[hole];
                hole = hole - 1;
            }
            at[hole + 1] = at_key;
            bt[hole + 1] = bt_key;
            processID[hole + 1] =process_key ;

        }

    }

    public static void sortArrayByProcessID(int processID[] ,int at[] , int bt[] , int tat[] , int wt[]){

        for (int i = 1; i <num;  ++i) {
            int at_key = at[i];
            int bt_key = bt[i];
            int tat_key = tat[i];
            int wt_key = wt[i];
            int process_key = processID[i];

            int hole = i - 1;
            while (hole >= 0 && processID[process_order[hole]] > process_key) {
                at[hole + 1] = at[hole];
                bt[hole + 1] = bt[hole];
                wt[hole + 1] = wt[hole];
                tat[hole + 1] = tat[hole];
                processID[hole + 1] = processID[hole];
                hole = hole - 1;
            }
            at[hole + 1] = at_key;
            bt[hole + 1] = bt_key;
            wt[hole + 1] = wt_key;
            tat[hole + 1] = tat_key;
            processID[hole + 1] =process_key ;

        }

    }

    public static void printInputTable(int processID[] ,int at[] , int bt[]){

        System.out.println("\n \n*** The  Input Table  ***\n ") ;
        System.out.println("\nPID \t AT\t BT") ;

        for(int j=0 ; j<num ; j++){
            System.out.println(processID[j]+"\t  "+at[j]+"\t  "+ bt[j] ) ;
        }
        System.out.println() ;
    }

    public static void printOutputTable(int processID[] ,int at[] , int bt[] , int tat[], int wt[], int  process_order[]){
        
        System.out.println("\n \n*** The Final Output Table *** \n ") ;
        System.out.println("\nPID \t AT\t BT\t TAT\t  WT") ;

        for(int j=0 ; j<num ; j++){
            System.out.println(processID[process_order[j]]+"\t  "+at[process_order[j]]+"\t  "+ bt[process_order[j]]+"\t  "+ tat[process_order[j]]+"\t   "+ wt[process_order[j]] ) ;
        }
        System.out.println() ;

        System.out.println("Average TurnAround Time : "+ avg_tat) ;
        System.out.println("Average Waiting Time : " +avg_wt) ;

    }

    public static void gantChart(int processID[] ,int switch_time[], int  process_order[]){
        int j=0 ;
        System.out.println("** GANTT CHART **\n") ;
        if(at[0] != 0){
          System.out.print(switch_time[j++]+"\t[NA]\t") ;
        }

        for(int i=0 ; i<num ; i++){
        // System.out.print(switch_time[j++]+"\t"+ processID[i] +" | ") ;
        System.out.print(switch_time[j++]+"\t["+ processID[process_order[i]] +"]\t") ;
        }
        System.out.print(switch_time[j]) ;

    }
    public static void CalcTAT_and_WT(int switch_time[] ,int at[] , int bt[], int  process_order[]){
        
        int j=1 ;

        if(at[0] != 0){
            j++ ;
        }
        for(int i=0 ; i<num ; i++){
                tat[process_order[i]] = switch_time[j++] - at[process_order[i]] ;
                wt[process_order[i]] =tat[process_order[i]] - bt[process_order[i]] ;
                total_tat += tat[process_order[i]];
                total_wt +=  wt[process_order[i]];
        }

        avg_tat = (double)total_tat/num ;
        avg_wt = (double)total_wt/num ;
    }

    public static void SJFNP(int  processID[],int at[] , int bt[]){
        int total_time=0 , j=0;
        int switch_time[] = new int[50] ;
        switch_time[j++] = 0; 

        if(at[0] != 0){
            total_time = total_time + at[0] ;
            switch_time[j++] =  total_time ;
        }

        int min_burst ;
        int index =0 ;

        for(int i=0 ; i<num ; i++){
            min_burst =999 ;
            for(int k=0 ; k<num ; k++){
              if((process_status[k] == true) && (at[k] <= total_time) && (bt[k]<min_burst)){
                min_burst= bt[k];
                index = k;
             }

           }

            total_time =total_time + min_burst;
            switch_time[j++] =  total_time ;
            process_status[index] = false ;
            process_order[i] =index ;
            // System.out.print(processID[i]+"\t" + index +"\n") ;
           
        }
        System.out.println() ;

        gantChart(processID  ,switch_time , process_order) ;
        CalcTAT_and_WT(switch_time ,at , bt , process_order) ;
      // sortArrayByProcessID(processID, at, bt, tat, wt);
        printOutputTable(processID, at, bt ,tat , wt , process_order) ;
    }

  

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in) ;

        System.out.println("Enter the number of processes : ") ;
        num =sc.nextInt() ;

        System.out.println("Enter the Arrival Time & Burst Time of the Processes :") ;
        
        for(int i=0 ; i<num ; i++){
           // System.out.println("Arrival Time & Burst Time of "+ (i+1)+" : ") ;
            processID[i] =i+1 ;
            at[i] =sc.nextInt() ;
            bt[i] =sc.nextInt() ;
            process_status[i]= true; 
            
         }

        
        //Sorting of the Processes wrt AT
        sortArrayByAT(processID,at,bt) ;

        //Print Array
        printInputTable(processID,at,bt) ;

        //SJF NP
        SJFNP(processID,at,bt) ;

    }
}


 /*   
5
2 5
4 2
1 7
5 8
3 1
*/