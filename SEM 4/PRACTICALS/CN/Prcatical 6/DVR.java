import java.util.*;

public class DVR
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		System.out.print("Enter no. of nodes:");
		int n = sc.nextInt();
		System.out.print("Nodes are : ");
		for(int i=0;i<n;i++){
		    System.out.print((char)(((int)'A')+i)+" ");
		}
		System.out.println();
		System.out.print("Enter the node for which routing table is to be generated: ");
		int node = ((int)sc.next().charAt(0))-65;
		int main_table[] = new int[n];
		int main_table_parent[] = new int[n];
		for(int i=0;i<n;i++){
		    main_table[i]=Integer.MAX_VALUE;
		}
		main_table[node] = 0;
		main_table_parent[node] = -1;
		System.out.print("Enter the number of neighbours: ");
		int m = sc.nextInt();
		Neighbour neighbours[] = new Neighbour[m];
		for(int i=0;i<m;i++){
		    System.out.print("Enter the neighbour node: ");
		    int neighbour_node=((int)sc.next().charAt(0))-65;
		    System.out.print("Enter the delay between "+((char)(node+65))+" and "+((char)(neighbour_node+65))+" : ");
		    int delay=sc.nextInt();
		    main_table[neighbour_node]=delay;
		    main_table_parent[neighbour_node]=neighbour_node;
		    neighbours[i]= new Neighbour(neighbour_node,delay,n);
		    neighbours[i].distance[neighbours[i].node]=0;
		    neighbours[i].distance[node]=neighbours[i].delay;
		    System.out.println("Routing Table of "+((char)(neighbour_node+65))+":");
		    for(int j=0;j<neighbours[i].distance.length;j++){
		        System.out.println(((char)(j+65))+"\t"+neighbours[i].distance[j]);
		    }
		    System.out.println();
		}
		System.out.println();
		for(int i=0;i<n;i++){
		    boolean flag=true;
		    if(i==node){
		        flag=false;
		    }
	        for(int j=0;j<m;j++){
	            if(i==neighbours[j].node){
	                flag=false;
	            }
	        }
	        if(flag){
	            int delay[] = new int[m];
	            int delay_parent[] = new int[m];
	            for(int j=0;j<m;j++){
	                delay[j]= neighbours[j].delay+neighbours[j].distance[i];
	                delay_parent[j]=neighbours[j].node;
	            }
	            int min_value=delay[0];
	            int min_value_index=0;
	            for(int j=0;j<m;j++){
	                if(min_value>delay[j]){
	                    min_value = delay[j];
	                    min_value_index = j;
	                }
	            }
	            main_table[i]=min_value;
	            main_table_parent[i]=delay_parent[min_value_index];
	        }
	    }
	    System.out.println("Node\tDelay\tParent");
	    for(int i=0;i<n;i++){
	        System.out.println(((char)(i+65))+"\t"+main_table[i]+"\t"+((char)(main_table_parent[i]+65)));
	    }
	}
}

class Neighbour{
    int node;
    int delay;
    int distance[];
    
    Neighbour(int node,int delay,int d_length){
        this.node = node;
        this.delay = delay;
        this.distance = new int[d_length];
        for(int i=0;i<d_length;i++){
            this.distance[i]=(int)(Math.random()*(9-1+1)+1);
        }
    }
}