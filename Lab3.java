import java.util.*;

public class BellmanFord {

    static final int INF = 999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] cost = new int[n + 1][n + 1];
        int[] dist = new int[n + 1];

        // Input adjacency matrix
        System.out.println("Enter adjacency matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                cost[i][j] = sc.nextInt();

                if (i == j) cost[i][j] = 0;
                else if (cost[i][j] == 0) cost[i][j] = INF;
            }
        }

        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();

        // Step 1: initialize distances
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Step 2: relax all edges (n-1) times
        for (int k = 1; k <= n - 1; k++) {
            for (int u = 1; u <= n; u++) {
                for (int v = 1; v <= n; v++) {
                    if (cost[u][v] != INF && dist[v] > dist[u] + cost[u][v]) {
                        dist[v] = dist[u] + cost[u][v];
                    }
                }
            }
        }

        // Step 3: check negative cycle
        for (int u = 1; u <= n; u++) {
            for (int v = 1; v <= n; v++) {
                if (cost[u][v] != INF && dist[v] > dist[u] + cost[u][v]) {
                    System.out.println("Graph contains negative cycle!");
                    sc.close();
                    return;
                }
            }
        }

        // Output
        System.out.println("\nShortest distances from " + src + ":");
        for (int i = 1; i <= n; i++) {
            System.out.println(src + " → " + i + " = " + dist[i]);
        }

        sc.close();
    }
}
