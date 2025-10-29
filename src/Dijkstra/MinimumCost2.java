package Dijkstra;

import java.io.*;
import java.util.*;

public class MinimumCost2 {
    static int n, m;
    static int[] path;
    static long[] cost;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        cost = new long[n + 1];
        adj = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        br.close();

        path = new int[n + 1];
        run(start);

        bw.write(print(start, end) + "\n");
        bw.close();
    }

    private static String print(int start, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append(cost[end]).append("\n");
        int idx = end;
        int count = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(idx);
        while (idx != start) {
            idx = path[idx];
            stack.push(idx);
            count++;
        }
        sb.append(count).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

    private static void run(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        boolean[] isVisit = new boolean[n + 1];
        cost[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (isVisit[now.v]) continue;
            isVisit[now.v] = true;

            for (Node next : adj[now.v]) {
                long nc = next.c + cost[now.v];
                if (cost[next.v] > nc) {
                    cost[next.v] = nc;
                    pq.offer(new Node(next.v, nc));
                    path[next.v] = now.v;
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int v;
        long c;

        public Node(int v, long c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.c, o.c);
        }
    }
}
