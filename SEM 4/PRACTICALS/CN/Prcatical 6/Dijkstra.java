import java.util.*;

public class Dijkstra {
    public static final int MAX_VALUE = 999;

    public static voidimport java.util.*;

    public class Dijkstra {
        static HashSet<Integer> path = new HashSet<Integer>();
    
        public static int min(int dist[], boolean visit[], int V) {
            int mini = Integer.MAX_VALUE, index = -1;
            for (int v = 0; v < V; v++) {
                if (visit[v] == false && dist[v] < mini) {
                    mini = dist[v];
                    index = v;
                }
            }
            return index;
        }
    
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.println("Dijkstra Routing Algorithm:");
            System.out.println();
            System.out.print("Enter the number of nodes:");
            int V = sc.nextInt();
            int cost[][] = new int[V][V];
            System.out.println();
            System.out.println("Enter the adjacency matrix: ");
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    cost[i][j] = sc.nextInt();
                }
            }
            System.out.println();
            System.out.print("Enter the starting node:");
            int s = sc.nextInt();
            System.out.print("Enter the destination node:");
            int d = sc.nextInt();
            int dist[] = new int[V];
            boolean visit[] = new boolean[V];
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                visit[i] = false;
            }
            dist[s] = 0;
            for (int i = 1; i < V - 1; i++) {
                int u = min(dist, visit, V);
                visit[u] = true;
                for (int v = 0; v < V; v++) {
                    if (!visit[v] && cost[u][v] != 0 && dist[u] + cost[u][v] < dist[v])
                        dist[v] = dist[u] + cost[u][v];
                    path.add(u);
                }
            }
            System.out.println();
            System.out.println("Starting node:" + s);
            System.out.println("Destination node:" + d);
            System.out.println("Minimum Cost of destination from starting node:" + dist[d]);
            System.out.print("Path: ");
            for (Integer p : path)
                System.out.print(p + "->");
            System.out.println(d);
            sc.close();
        }
    } dijkstra(int[][] graph, int source, int destination) {
        int count = graph.length;
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        int[] parent = new int[count];

        for (int i = 0; i < count; i++) {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        distance[source] = 0;

        for (int i = 0; i < count; i++) {

            int u = findMinDistance(distance, visitedVertex);
            visitedVertex[u] = true;

            for (int v = 0; v < count; v++) {
                if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
                    distance[v] = distance[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        int routeCounter = destination;
        System.out.println("\nDistance of Node" + destination + ": " + distance[destination]);
        System.out.print("\nPath: ");
        while (routeCounter != source) {
            System.out.print(routeCounter + "<=");
            routeCounter = parent[routeCounter];
        }
        System.out.print(routeCounter);
    }

    private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visitedVertex[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }

    public static void main(String[] args) {
        System.out.println("For Dijsktra's Algorithm: ");
        int source, n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Source: ");
        source = sc.nextInt();
        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();
        System.out.println();

        int[][] adjacencymatrix = new int[n][n];
        System.out.println("Enter Adjacency Matrix: ");
        for (int i = 0; i < n; i++) {
            System.out.println("\n=> Node " + i);
            for (int j = 0; j < n; j++) {
                System.out.print("Element " + j + ": ");
                adjacencymatrix[i][j] = sc.nextInt();
                if (i == j) {
                    adjacencymatrix[i][j] = 0;
                    continue;
                }
                if (adjacencymatrix[i][j] == 0) {
                    adjacencymatrix[i][j] = MAX_VALUE;
                }
            }
        }

        System.out.print("\nEnter Destination: ");
        int destination = sc.nextInt();

        Dijkstra mindistance = new Dijkstra();
        mindistance.dijkstra(adjacencymatrix, source, destination);
    }
}