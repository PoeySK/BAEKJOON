package Dijkstra;

import java.io.*;
import java.util.*;

public class TimeMachine {
    final static int INF = 1_000_000_000;
    static int N, M;
    static long[] dist;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(A, B, C);
        }
        br.close();

        dist = new long[N + 1];
        Arrays.fill(dist, INF);
        if (run()) {
            bw.write(-1 + "\n");
            bw.close();
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N + 1; i++) {
            if (dist[i] == INF) {
                sb.append(-1).append("\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        bw.write(sb + "\n");
        bw.close();
    }

    private static boolean run() {
        dist[1] = 0;
        for (int i = 0; i < N; i++) {
            for (Edge edge : edges) {
                int u = edge.u;
                int v = edge.v;
                int d = edge.d;

                if (dist[u] == INF) continue;

                if (dist[v] > dist[u] + d) {
                    if (i == N - 1) return true;

                    dist[v] = dist[u] + d;
                }
            }
        }

        return false;
    }

    private static class Edge {
        int u, v, d;

        Edge(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }
    }
}
