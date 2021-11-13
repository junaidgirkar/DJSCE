import java.util.*;  

public class Dijkstra {
    public static final int MAX_VALUE = 999;
    
    public static void dijkstra(int[][] graph, int source) {
        int count = graph.length;
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        int[] parent = new int[count];

        for (int i = 0; i < count; i++) 
        {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        distance[source] = 0;

        for (int i = 0; i < count; i++) 
        {

            int u = findMinDistance(distance, visitedVertex);
            visitedVertex[u] = true;

            for (int v = 0; v < count; v++) 
            {
                if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) 
                {
                    distance[v] = distance[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }
        for (int i = 0; i < distance.length; i++) 
        {
            System.out.println(String.format("For Vertex:%s\tSource:%s\tMin Distance:%s\tParent:%s", i, source, distance[i], parent[i]));
        }
    }
    private static int findMinDistance(int[] distance, boolean[] visitedVertex) 
    {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++) 
        {
            if (!visitedVertex[i] && distance[i] < minDistance) 
            {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }

    public static void main(String[] args) 
    {
        System.out.println("For Dijsktra's Algorithm: ");
        int source,n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Source: ");
        source = sc.nextInt();
        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();
        System.out.println();

        int[][] adjacencymatrix = new int[n][n];
        System.out.println("Enter Adjacency Matrix: ");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                adjacencymatrix[i][j] = sc.nextInt();
                if (i == j)
                {
                    adjacencymatrix[i][j] = 0;
                    continue;
                }
                if (adjacencymatrix[i][j] == 0)
                {
                    adjacencymatrix[i][j] = MAX_VALUE;
                }
            }
        }
        Dijkstra mindistance = new Dijkstra();
        mindistance.dijkstra(adjacencymatrix, source);
    }
}