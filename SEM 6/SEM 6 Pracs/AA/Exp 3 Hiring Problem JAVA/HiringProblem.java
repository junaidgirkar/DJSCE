import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class HiringProblem {

    public static int daily_cost = 0;
    public static int interview_cost = 0;
    public static int firing_cost = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter cost of interview: ");
        interview_cost = sc.nextInt();
        System.out.print("Enter per day salary: ");
        daily_cost = sc.nextInt();
        System.out.print("Enter cost of firing: ");
        firing_cost = sc.nextInt();
        System.out.print("Enter number of days: ");
        int n = sc.nextInt();
        int[] arr = createArray(n);
        System.out.println("Cost for interview: " + interview_cost + "\nDaily Salary of Employee: " + daily_cost + "\nCost for firing: " + firing_cost);
        System.out.print("Employee points:\t");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
        System.out.print("Employee Name:\t\t");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("C" + (i+1) + "\t");
        }
        System.out.println();
        int cost = calculateCost(arr);
        System.out.println("Total cost using normal method is: " + cost);
        int random_cost = calculateRandomCost(arr);
        System.out.println("Total cost using random method is: " + random_cost);
    }

    public static int[] createArray(int n) {
        Random random =  new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n-1; i++) {
            arr[i] = random.nextInt(99);
        }
        arr[n-1] = 100;
        return arr;
    }

    public static int calculateCost(int[] arr) {
        System.out.println("\nUsing Normal Method!\n");
        int totalCost = 0;
        int points = -1;
        int temp = -1;
        int hired = 0, fired = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Day " + (i+1));
            System.out.println("Candidate selected: C" + (i+1) + " having points = " + arr[i]);
            int cost = 0;
            cost += interview_cost;
            if (arr[i] > points) {
                if (points == -1) {
                    System.out.println("C" + (i+1) + " hired having points = " + arr[i] + "!");
                    cost += daily_cost;
                    hired ++;
                } else {
                    System.out.println("C" + (temp+1) + " fired having points = " + arr[temp] + "!");
                    System.out.println("C" + (i+1) + " hired having points = " + arr[i] + "!");
                    cost += daily_cost;
                    cost += firing_cost;
                    cost += daily_cost;
                    hired ++;
                    fired ++;
                }
                points = arr[i];
                temp = i;
            } else {
                System.out.println("C" + (temp+1) + " still working!");
                cost += daily_cost;
            }
            System.out.println("Cost of day " + (i+1) + " is: " + cost);
            System.out.println();
            totalCost += cost;
        }
        System.out.println("Hired " + hired + " candidates, Fired " + fired + " candidates.");
        return totalCost;
    }

    public static int calculateRandomCost(int[] arr) {
        System.out.println("\nUsing Random Method!\n");
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            set.add(i);
        }
        int points = -1;
        int totalCost = 0;
        int temp = -1;
        int hired = 0, fired = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Day " + (i+1));
            Integer[] nos = set.toArray(new Integer[set.size()]);
            Random random =  new Random();
            int cost = 0;
            cost += interview_cost;
            int random_index = random.nextInt(set.size());
            int random_no = nos[random_index];
            System.out.println("Candidate selected: C" + (random_no+1) + " having points = " + arr[random_no]);
            if (arr[random_no] > points) {
                if (points == -1) {
                    System.out.println("C" + (random_no+1) + " hired having points = " + arr[random_no] + "!");
                    cost += daily_cost;
                    hired ++;
                } else {
                    System.out.println("C" + (temp+1) + " fired having points = " + arr[temp] + "!");
                    System.out.println("C" + (random_no+1) + " hired having points = " + arr[random_no] + "!");
                    cost += daily_cost;
                    cost += firing_cost;
                    cost += daily_cost;
                    hired ++;
                    fired ++;
                }
                points = arr[random_no];
                temp = random_no;
            } else {
                System.out.println("C" + (temp+1) + " still working!");
                cost += daily_cost;
            }
            System.out.println("Cost of day " + (i+1) + " is: " + cost);
            set.remove(random_no);
            totalCost += cost;
            System.out.println();
        }
        System.out.println("Hired " + hired + " candidates, Fired " + fired + " candidates.");
        return totalCost;
    }

}
