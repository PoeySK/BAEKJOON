package Dijkstra;

import java.io.*;
import java.util.*;

public class SpecialDijkstra {
    static int N, M, INF = 200000000;
    static long[] dist;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
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

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        br.close();

        /* 작동 */
        dist = new long[N + 1];
        run(v1);
        long v1To1 = dist[1];
        long v1Tov2 = dist[v2];
        long v1ToN = dist[N];

        dist = new long[N + 1];
        run(v2);
        long v2To1 = dist[1];
        long v2ToN = dist[N];

        // one: 1 -> v1 -> v2 -> N, two: 1 -> v2 -> v1 -> N
        long one = 0;
        one += v1To1;
        one += v1Tov2;
        one += v2ToN;
        long two = 0;
        two += v2To1;
        two += v1Tov2;
        two += v1ToN;
        long result = Math.min(one, two);

        /* 출력 */
        boolean isCheck = one >= INF && two >= INF; // 도달 확인
        bw.write((isCheck ? -1 : result) + "\n");
        bw.close();
    }

    private static void run(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.v] < now.w) continue;

            for (Node next : adj[now.v]) {
                if (dist[next.v] > dist[now.v] + next.w) {
                    dist[next.v] = dist[now.v] + next.w;
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
