package Dijkstra;

import java.io.*;
import java.util.*;

public class LookingForHouse {
    static int V, E, result = Integer.MAX_VALUE;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj = new ArrayList[V + 1];

        int[] mDist = new int[V + 1];
        int[] sDist = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            adj[i] = new ArrayList<>();
            mDist[i] = Integer.MAX_VALUE;
            sDist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
        }

        PriorityQueue<Node> mpq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            mpq.offer(new Node(num, 0));
            mDist[num] = 0;
        }

        PriorityQueue<Node> spq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int num = Integer.parseInt(st.nextToken());
            spq.offer(new Node(num, 0));
            sDist[num] = 0;
        }
        br.close();

        dij(mpq, mDist, x);
        dij(spq, sDist, y);
        run(mDist, sDist);

        bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n");
        bw.close();
    }

    private static void run(int[] mDist, int[] sDist) {
        for (int i = 1; i < V + 1; i++) {
            if (mDist[i] == 0 || mDist[i] == Integer.MAX_VALUE || sDist[i] == 0 || sDist[i] == Integer.MAX_VALUE) continue;

            int sum = mDist[i] + sDist[i];
            result = Math.min(result, sum);
        }
    }

    private static void dij(PriorityQueue<Node> pq, int[] dist, int ad) {
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (Node next : adj[now.v]) {
                int nd = dist[now.v] + next.w;
                if (nd > ad) continue;

                if (dist[next.v] > nd) {
                    dist[next.v] = nd;
                    pq.offer(next);
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
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
/*
8 11
1 2 2
1 4 1
2 4 2
2 3 1
2 7 8
3 7 3
4 5 2
4 6 1
6 7 6
6 8 4
7 8 2
2 6
1 8
1 4
8
 */