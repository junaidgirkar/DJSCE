import java.util.*;

class P {
    int id;
    int burstTime;
    int arrivalTime;

    public P(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

class P_SRT {

    static void findWaitingTime(P process_arr[], int waitingTime[], int finish_time[], ArrayList<Integer> Grant) {

        int length = process_arr.length;
        int remainingTime[] = new int[length];

        for (int i = 0; i < length; i++)
            remainingTime[i] = process_arr[i].burstTime;

        int complete = 0;
        int time = 0;
        int minimum = Integer.MAX_VALUE;
        int shortest = 0;
        boolean check = false;

        while (complete != length) {

            for (int j = 0; j < length; j++) {
                if ((process_arr[j].arrivalTime <= time) && (remainingTime[j] < minimum) && remainingTime[j] > 0) {
                    minimum = remainingTime[j];
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

            minimum = remainingTime[shortest];
            if (minimum == 0)
                minimum = Integer.MAX_VALUE;

            if (remainingTime[shortest] == 0) {

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

    static void findTurnAroundTime(P process_arr[], int waitingTime[], int turnAroundTime[]) {
        int length = process_arr.length;
        for (int i = 0; i < length; i++)
            turnAroundTime[i] = process_arr[i].burstTime + waitingTime[i];
    }

    static void findavgTime(P process_arr[], int waitingTime[], int turnAroundTime[], int finishTime[],
            ArrayList<Integer> Grant) {

        int length = process_arr.length;
        int total_waitTime = 0;
        int total_tATime = 0;
        System.out.println("Processes " + " Arrival Time " + " Burst time " + "Finish Time" + "  Waiting time "
                + "  Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < length; i++) {
            total_waitTime = total_waitTime + waitingTime[i];
            total_tATime = total_tATime + turnAroundTime[i];
            System.out.println(" " + process_arr[i].id + "\t\t" + " " + process_arr[i].arrivalTime + "\t\t"
                    + +process_arr[i].burstTime + "\t" + finishTime[i] + "\t\t" + waitingTime[i] + "\t\t"
                    + turnAroundTime[i]);
        }

        System.out.println("Average waiting time = " + (double) total_waitTime / (double) length);
        System.out.println("Average turn around time = " + (double) total_tATime / (double) length);
        System.out.println("Grant Chart: " + Grant);

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes");
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<Integer> Grant = new ArrayList<Integer>();
        int arrivalTime = 0, bursttime = 0;
        int waitingTime[] = new int[n];
        int turnAroundTime[] = new int[n];
        int finishTime[] = new int[n];
        P process_arr[] = new P[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter arrival time and burst time ");
            arrivalTime = sc.nextInt();
            bursttime = sc.nextInt();
            process_arr[i] = new P(i + 1, arrivalTime, bursttime);
        }
        sc.close();

        findWaitingTime(process_arr, waitingTime, finishTime, Grant);
        findTurnAroundTime(process_arr, waitingTime, turnAroundTime);
        findavgTime(process_arr, waitingTime, turnAroundTime, finishTime, Grant);
    }
}