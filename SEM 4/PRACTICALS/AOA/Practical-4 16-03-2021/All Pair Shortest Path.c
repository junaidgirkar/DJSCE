// C Program for Floyd Warshall Algorithm
#include<stdio.h>

#define V 4

#define INF 99999


void printSolution(int dist[][V]);

void floydWarshall (int graph[][V])
{

	int dist[V][V], i, j, k;

	for (i = 0; i < V; i++)
		for (j = 0; j < V; j++)
			dist[i][j] = graph[i][j];

	for (k = 0; k < V; k++)
	{
		for (i = 0; i < V; i++)
		{
			for (j = 0; j < V; j++)
			{
				if (dist[i][k] + dist[k][j] < dist[i][j])
					dist[i][j] = dist[i][k] + dist[k][j];
			}
		}
	}


	printSolution(dist);
}


void printSolution(int dist[][V])
{
	printf ("The following matrix shows the shortest distances"
			" between every pair of vertices \n");
	for (int i = 0; i < V; i++)
	{
		for (int j = 0; j < V; j++)
		{
			if (dist[i][j] == INF)
				printf("%7s", "INF");
			else
				printf ("%7d", dist[i][j]);
		}
		printf("\n");
	}
}


int main()
{


	int graph[V][V]/* = { {0, 5, INF, 10},
						{INF, 0, 3, INF},
						{INF, INF, 0, 1},
						{INF, INF, INF, 0}
					}*/;
					
	int i,j,m,n,value;
	printf("Enter the matrix size : (m x n) :");
	scanf("%d x %d", &m, &n);
	
	printf("Enter 99999 for INF \n");
	for(i=0;i<m;i++)
	{
	    for(j=0;j<n;j++)
	    {
	        printf("Enter value for %d x %d: ", i+1 ,j+1);
	        scanf("%d", &graph[i][j]);
	    }
	}

	floydWarshall(graph);
	return 0;
}
