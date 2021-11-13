// Shortest Job First
import java.util.Scanner;
import java.util.Arrays;
public class Main_SJF
{
	public static void main(String[] args) {
	    Scanner sc= new Scanner(System.in);
		System.out.print("Enter number of processes: ");
		int n=sc.nextInt();
		Processs p[]= new Processs[n];
		for(int i=1;i<=n;i++){
		    System.out.print("Enter the burst time for process "+i+": ");
		    int bt= sc.nextInt();
		    p[i-1] = new Processs(i,bt,0);
		}
		Arrays.sort(p);
		for(int i=0;i<n;i++){
		    if(i==0){
		        p[i].ct=p[i].bt;
		    }
		    else{
		        p[i].ct=p[i-1].ct+p[i].bt;
		    }
		    p[i].tat=p[i].ct-p[i].at;
		    p[i].wt=p[i].tat-p[i].bt;
		    
		}
		int tat=0;int wt=0;
		System.out.println("P\tAT\tBT\tCT\tTAT\tWT");
		for(Processs temp:p){
		    System.out.println(temp);
		    tat+=temp.tat;
		    wt+=temp.wt;
		}
		tat=tat/n;
		wt=wt/n;
		System.out.println("Avg tat. ="+tat+"\nAvg wt ="+wt);
		System.out.print("0--");
		for(Processs temp:p){
		    System.out.print("P"+temp.pid+"--");
		    System.out.print(+temp.ct+"--");
		}
	}
}

class Processs implements Comparable<Processs>{
   int pid;
   int bt;
   int at;
   int ct;
   int tat;
   int wt;
   
   public Processs(int pid, int bt, int at) { 
        this.pid = pid; 
        this.bt = bt; 
        this.at = at; 
        this.tat=0;
        this.wt=0;
   }
   public int compareTo(Processs a){
       int compareBT= ((Processs)a).bt;
       return this.bt - compareBT;
   }
   public String toString(){
       return "P"+this.pid+"\t"+this.at+"\t"+this.bt+"\t"+this.ct+"\t"+this.tat+"\t"+this.wt;
   }
}
