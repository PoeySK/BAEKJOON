package Dijkstra;

import java.io.*;
import java.util.*;

public class Hacking {
    static int n;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj = new ArrayList[n + 1];
            for (int i = 1; i < n + 1; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adj[b].add(new Node(a, s));
            }
            int[] result = run(c);
            bw.write(result[0] + " " + result[1] + "\n");
        }
        br.close();
        bw.close();
    }

    private static int[] run(int start) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(start, 0));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (Node next : adj[now.v]) {
                if (dist[next.v] > dist[now.v] + next.time) {
                    dist[next.v] = dist[now.v] + next.time;
                    q.offer(new Node(next.v, now.time + next.time));
                }
            }
        }

        int count = 0;
        int max = 0;
        for (int i = 1; i < n + 1; i++) {
            if (dist[i] < Integer.MAX_VALUE) {
                count++;
                max = Math.max(max, dist[i]);
            }
        }

        return new int[]{count, max};
    }

    private static class Node {
        int v, time;

        Node(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }
}
