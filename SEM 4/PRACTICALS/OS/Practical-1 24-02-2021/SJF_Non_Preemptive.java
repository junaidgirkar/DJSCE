import java.util.*;
 
public class SJF_Non_Preemptive {
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println ("Enter number of process:");
		int n = sc.nextInt();
		int pid[] = new int[n];
		int arrivalTime[] = new int[n];
		int burstTime[] = new int[n]; 
		int completeTime[] = new int[n]; 
		int turnAroundTime[] = new int[n]; 
		int waitingTime[] = new int[n];
		int f[] = new int[n];  // f means it is flag it checks process is completed or not
		int st=0, tot=0;
		float avgwt=0, avgta=0;
 
		for(int i=0;i<n;i++)
		{
			System.out.println ("enter process " + (i+1) + " arrival time:");
			arrivalTime[i] = sc.nextInt();
			System.out.println ("enter process " + (i+1) + " burst time:");
			burstTime[i] = sc.nextInt();
			pid[i] = i+1;
			f[i] = 0;
		}
		
		boolean a = true;
		while(true)
		{
			int c=n, min=999;
			if (tot == n) // total no of process = completed process loop will be terminated
				break;
			
			for (int i=0; i<n; i++)
			{
				/*
				 * If i'th process arrival time <= system time and its flag=0 and burst<min 
				 * That process will be executed first 
				 */ 
				if ((arrivalTime[i] <= st) && (f[i] == 0) && (burstTime[i]<min))
				{
					min=burstTime[i];
					c=i;
				}
			}
			
			/* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
			if (c==n) 
				st++;
			else
			{
				completeTime[c]=st+burstTime[c];
				st+=burstTime[c];
				turnAroundTime[c]=completeTime[c]-arrivalTime[c];
				waitingTime[c]=turnAroundTime[c]-burstTime[c];
				f[c]=1;
				tot++;
			}
		}
		
		System.out.println("\npid  arrival brust  complete turn waiting");
		for(int i=0;i<n;i++)
		{
			avgwt+= waitingTime[i];
			avgta+= turnAroundTime[i];
			System.out.println(pid[i]+"\t"+arrivalTime[i]+"\t"+burstTime[i]+"\t"+completeTime[i]+"\t"+turnAroundTime[i]+"\t"+waitingTime[i]);
		}
		System.out.println ("\naverage turn arounf time is "+ (float)(avgta/n));
		System.out.println ("average waiting time is "+ (float)(avgwt/n));
		sc.close();
	}
}