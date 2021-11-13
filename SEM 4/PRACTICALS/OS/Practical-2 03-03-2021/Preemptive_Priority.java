import java.util.*;

class Prow {
   
    int id;
    int burstTime;
    int arrivalTime;
    int priority;

    public Prow(int id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }
}

public class Preemptive_Priority {
    public static void main(String[] args) {
        int n;
        System.out.println("Enter Number of Processes:");
        Scanner ob = new Scanner(System.in);
        n = ob.nextInt();
        ArrayList<Integer> Grant = new ArrayList<Integer>();
        int arrivalTime = 0, bursttime = 0, priority;
        int waitingTime[] = new int[n];
        int turnAroundTime[] = new int[n];
        int finishTime[] = new int[n];
        Prow process_arr[] = new Prow[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time, Burst Time and Priority for the Process:");
            arrivalTime = ob.nextInt();
            bursttime = ob.nextInt();
            priority = ob.nextInt();
            process_arr[i] = new Prow(i + 1, arrivalTime, bursttime, priority);
        }
        findWaitingTime(process_arr, waitingTime, finishTime, Grant);
        findTurnAroundTime(process_arr, waitingTime, turnAroundTime);
        findavgTime(process_arr, waitingTime, turnAroundTime, finishTime, Grant);

        ob.close();

    }
    

    static void findWaitingTime(Prow process_arr[], int waitingTime[], int finish_time[], ArrayList<Integer> Grant) {

        int length = process_arr.length;
        int min = 0;
        int priorityList[] = new int[length];
        int remainingTime[] = new int[length];

        for (int i = 0; i < length; i++)
            priorityList[i] = process_arr[i].priority;

        for (int i = 0; i < length; i++)
            remainingTime[i] = process_arr[i].burstTime;

        int complete = 0;
        int time = 0;
        int minimum = Integer.MAX_VALUE;
        int shortest = 0;
        boolean check = false;

        while (complete != length) {

            for (int j = 0; j < length; j++) {
                
                if ((process_arr[j].arrivalTime <= time) && (priorityList[j] < minimum && remainingTime[j] != 0) && (priorityList[j] > 0)) {
                    minimum = priorityList[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                Grant.add(0);
                time++;
                continue;
            }

            Grant.add(process_arr[shortest].id);
            remainingTime[shortest]--;

            //System.out.println("Remaining Time : " + remainingTime[shortest]);

            minimum = priorityList[shortest];

            min = remainingTime[shortest];
            if (min <= 0)
                minimum = Integer.MAX_VALUE;

            if (remainingTime[shortest] <= 0) {

                complete++;
                check = false;
                finish_time[shortest] = time + 1;

                // waiting time
                waitingTime[shortest] = finish_time[shortest] - process_arr[shortest].burstTime
                        - process_arr[shortest].arrivalTime;
            }
            time++;
        }
    }

    static void findTurnAroundTime(Prow process_arr[], int waitingTime[], int turnAroundTime[]) {
        int length = process_arr.length;
        for (int i = 0; i < length; i++)
            turnAroundTime[i] = process_arr[i].burstTime + waitingTime[i];
    }

    static void findavgTime(Prow process_arr[], int waitingTime[], int turnAroundTime[], int finishTime[],
            ArrayList<Integer> Grant) {

        int length = process_arr.length;
        int total_waitTime = 0;
        int total_tATime = 0;
        System.out.println("Processes " + " Arrival Time " + " Burst time " +  "Priority" + " Finish Time" + "  Waiting time "
                + "  Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < length; i++) {
            total_waitTime = total_waitTime + waitingTime[i];
            total_tATime = total_tATime + turnAroundTime[i];
            System.out.println(" " + process_arr[i].id + "\t\t" + " " + process_arr[i].arrivalTime + "\t\t"
                    + +process_arr[i].burstTime + "\t" + process_arr[i].priority + "\t" + finishTime[i] + "\t\t" + waitingTime[i] + "\t\t"
                    + turnAroundTime[i]);
        }

        System.out.println("Average waiting time = " + (double) total_waitTime / (double) length);
        System.out.println("Average turn around time = " + (double) total_tATime / (double) length);
        System.out.println("Grant Chart: " + Grant);

    }
}