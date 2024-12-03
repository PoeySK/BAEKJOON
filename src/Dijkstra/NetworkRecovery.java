package Dijkstra;

import java.io.*;
import java.util.*;

public class NetworkRecovery {
    static int N, M;
    static int[] path;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        path = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
        }
        br.close();

        /* 작동 */
        run();

        /* 출력 */
        bw.write((N - 1) + "\n");
        for (int i = 2; i < N + 1; i++) {
            bw.write(i + " " + path[i] + "\n");
        }
        bw.close();
    }

    private static void run() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (Node next : adj[now.v]) {
                if (dist[next.v] > dist[now.v] + next.w) {
                    dist[next.v] = dist[now.v] + next.w;
                    path[next.v] = now.v;
                    pq.offer(next);
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
