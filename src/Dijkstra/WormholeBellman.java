package Dijkstra;

import java.io.*;
import java.util.*;

public class WormholeBellman {
    static final int INF = 1000000000;
    static int N, M, W;
    static int[] dist;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        while (TC-- > 0) {
            /* 입력 */
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                // 양방향
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken()) * -1;

                // 단방향
                edges.add(new Edge(S, E, T));
            }

            /* 작동 */
            dist = new int[N + 1];
            Arrays.fill(dist, INF);
            boolean result = run();

            /* 출력 */
            bw.write(result ? "YES\n" : "NO\n");
        }
        br.close();
        bw.close();
    }

    private static boolean run() {
        dist[1] = 0;
        for (int i = 0; i < N; i++) { // N - 1번
            for (Edge edge : edges) {
                int u = edge.u;
                int v = edge.v;
                int w = edge.w;

                if (dist[v] > dist[u] + w) {
                    if (i == N - 1) return true; // 음의 사이클 존재

                    dist[v] = dist[u] + w;
                }
            }
        }

        return false;
    }

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
