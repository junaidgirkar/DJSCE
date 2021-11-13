import java.util.*;
import java.util.Scanner;

public class RoundRobin {
	static ArrayList<chartItem> ganttChart = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of Process: "); 
		int n =sc.nextInt();
		Process3[] process = new Process3[n];
		System.out.println("ID AT BT");
		for(int i=0;i<n;i++)
		{
            int id = sc.nextInt(); 
            int AT = sc.nextInt();  
            int BT = sc.nextInt();
            process[i] = new Process3(id,AT,BT);
		}
		System.out.println("Enter Quantum Time : "); 
		int QT =sc.nextInt();
		sortProcess(process);
		completeProcess(process,QT);
		float wait =0.0f;
		float Ttime =0.0f;
		System.out.println("Id\tAT\tBT\tCT\tWT\tTT ");
		for(int i=0;i<process.length;i++)
		{
			System.out.println(process[i].id+"\t"+process[i].AT+"\t"+process[i].BT+"\t"+process[i].CT+"\t"+process[i].WT+"\t"+process[i].TT);
			wait+=process[i].WT;
			Ttime+=process[i].TT;
		}
		System.out.print("\nProcess: ");
		for(int i=0;i<ganttChart.size();i++)
		{
			System.out.print("| "+ganttChart.get(i).id+"\t");
			
		}
		System.out.println("|\n");
		System.out.print("Time:-\t");
		int time=0;
		System.out.print(" "+time+"\t");
		for(int i=0;i<ganttChart.size();i++)
		{
			time+=ganttChart.get(i).time;
			System.out.print(" "+time+"\t");
			
		}
		System.out.println("\n");
		System.out.println("AVG Waiting time : "+(wait/process.length)+"\nAVG Turn around time : "+(Ttime/process.length));

	}
	
	static void completeProcess(Process3[] process,int QT) {
		ArrayList<Process3> readyQueue = new ArrayList<>();
		int time = 0 ;
		int pt =0;
		while(pt<process.length)
		{
			if(readyQueue.isEmpty())
			{
				readyQueue.add(new Process3(process[pt]));
				time = process[pt].AT>=time ?process[pt].AT:time;
			}
			else {
			if(process[pt].AT>time)
			{
				break;
			}
			else
			{
				readyQueue.add(new Process3(process[pt]));
			}
			}
			pt++;
		}
		
		while(!readyQueue.isEmpty()) {
			boolean completed = false;
			Process3 p = readyQueue.remove(0);
			
			if(p.BT>QT) {
				p.BT-=QT;
				time+=QT;
				chartItem c=new chartItem(p.id,QT);
				ganttChart.add(c);
				completed=false;
			}
			else {
				time +=p.BT;
				int id = getId(process,p);
				process[id].CT=time;
				process[id].TT=time-process[id].AT;
				process[id].WT=process[id].TT-process[id].BT;
				chartItem c=new chartItem(p.id,p.BT);
				ganttChart.add(c);
				completed=false;
				completed=true;
				
			}
			
			while(pt<process.length)
			{
				if(readyQueue.isEmpty())
				{
					readyQueue.add(new Process3(process[pt]));
					time = process[pt].AT>=time ?process[pt].AT:time;
				}
				else {
				if(process[pt].AT>time)
				{
					break;
				}
				else
				{
					readyQueue.add(new Process3(process[pt]));
				}
				}
				pt++;
			}
			
			if(!completed) {
				readyQueue.add(p);
			}
			
		}
	}
	
	static void sortProcess(Process3[] process)
	{
		for (int i = 0; i < process.length; i++) { 
			for (int j = 0; j < process.length - i - 1; j++) { 
			if (process[j].AT > process[j+1].AT) { 
					Process3 Temp = process[j];
					process[j]=process[j+1];
					process[j+1]=Temp;
            	} 
        	} 
    	} 
	}
	
	static int getId(Process3[] ps,Process3 p)
	{
		int id =0;
		for(id=0;id<ps.length;id++)
		{
			if(ps[id].id==p.id)
				break;
		}
		return id;
	}

}

class Process3{
	int id,AT,BT,WT=0,TT=0,CT=0;
	Process3(int id,int AT,int BT)
	{
		this.id=id;
		this.AT=AT;
		this.BT=BT;
	}
	Process3(Process3 p){
		this.id=p.id;
		this.AT=p.AT;
		this.BT=p.BT;
	}
}

class chartItem{
	int id,time;
	chartItem(int id,int time){
		this.id=id;
		this.time = time;
	}
}