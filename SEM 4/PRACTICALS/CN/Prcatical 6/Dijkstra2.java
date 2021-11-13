import java.util.*;

public class Dijkstra2 {
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
}