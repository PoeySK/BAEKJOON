package Dijkstra;

import java.io.*;
import java.util.*;

public class Exercise {
    static int V, E, result = Integer.MAX_VALUE;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V + 1][V + 1];
        for (int i = 1; i < V + 1; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = c;
        }
        br.close();

        run();

        for (int i = 1; i < V + 1; i++) {
            result = Math.min(result, dist[i][i]);
        }

        bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n");
        bw.close();
    }

    private static void run() {
        for (int s = 1; s < V + 1; s++) {
            for (int i = 1; i < V + 1; i++) {
                for (int j = 1; j < V + 1; j++) {
                    if (dist[i][s] != Integer.MAX_VALUE && dist[s][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][s] + dist[s][j]);
                    }
                }
            }
        }
    }
}
