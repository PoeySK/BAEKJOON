package Dijkstra;

import java.util.*;
import java.io.*;

public class WeightLimitation {
    static int N, M, result;
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
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            adj[A].add(new Node(B, C));
            adj[B].add(new Node(A, C));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        br.close();

        /* 작동 */
        run(start, end);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, Integer.MAX_VALUE));

        boolean[] isVisit = new boolean[N + 1];
        int[] dist = new int[N + 1];
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (isVisit[now.v]) continue;
            isVisit[now.v] = true;

            if (now.v == end) {
                result = Math.min(now.w, now.l);
                return;
            }

            for (Node next : adj[now.v]) {
                if (dist[next.v] < next.w) {
                    dist[next.v] = next.w;
                    int l = Math.min(next.w, now.l);
                    pq.offer(new Node(next.v, next.w, l));
                }
            }
        }

    }

    static class Node implements Comparable<Node> {
        int v, w, l;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        Node(int v, int w, int l) {
            this.v = v;
            this.w = w;
            this.l = l;
        }

        @Override
        public int compareTo(Node o) {
            return o.w - this.w;
        }
    }
}
