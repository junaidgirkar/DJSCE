import java.util.*;
 
public class SRTF_Preemptive {
	public static void main (String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println ("Enter number of process:");
		int n= sc.nextInt();
		int pid[] = new int[n]; // it takes pid of process
		int arrivalTime[] = new int[n];
		int burstTime[] = new int[n];
		int completeTime[] = new int[n];
		int turnAroundTime[] = new int[n];
		int waitingTime[] = new int[n];  
		int f[] = new int[n];  // f means it is flag it checks process is completed or not
		int k[]= new int[n];   // it is also stores brust time
	    int i, st=0, tot=0;
	    float avgwt=0, avgta=0;
 
	    for (i=0;i<n;i++)
	    {
	    	pid[i]= i+1;
	    	System.out.println ("Enter process " +(i+1)+ " arrival time:");
	    	arrivalTime[i]= sc.nextInt();
	    	System.out.println("Enter process " +(i+1)+ " burst time:");
	    	burstTime[i]= sc.nextInt();
	    	k[i]= burstTime[i];
	    	f[i]= 0;
	    }
	    
	    while(true){
	    	int min=99,c=n;
	    	if (tot==n)
	    		break;
	    	
	    	for ( i=0;i<n;i++)
	    	{
	    		if ((arrivalTime[i]<=st) && (f[i]==0) && (burstTime[i]<min))
	    		{	
	    			min=burstTime[i];
	    			c=i;
	    		}
	    	}
	    	
	    	if (c==n)
	    		st++;
	    	else
	    	{
	    		burstTime[c]--;
	    		st++;
	    		if (burstTime[c]==0)
	    		{
	    			completeTime[c]= st;
	    			f[c]=1;
	    			tot++;
	    		}
	    	}
	    }
	    
	    for(i=0;i<n;i++)
	    {
	    	turnAroundTime[i] = completeTime[i] - arrivalTime[i];
	    	waitingTime[i] = turnAroundTime[i] - k[i];
	    	avgwt+= waitingTime[i];
	    	avgta+= turnAroundTime[i];
	    }
	    
	    System.out.println("pid  arrival  burst  complete turn waiting");
	    for(i=0;i<n;i++)
	    {
	    	System.out.println(pid[i] +"\t"+ arrivalTime[i]+"\t"+ k[i] +"\t"+ completeTime[i] +"\t"+ turnAroundTime[i] +"\t"+ waitingTime[i]);
	    }
	    
	    System.out.println("\naverage tat is "+ (float)(avgta/n));
	    System.out.println("average wt is "+ (float)(avgwt/n));
	    sc.close();
	}
}