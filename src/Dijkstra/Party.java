package Dijkstra;

import java.io.*;
import java.util.*;

public class Party {
    static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] adj = new ArrayList[N + 1];
        ArrayList<Node>[] reverseAdj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
            reverseAdj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            adj[start].add(new Node(end, T));
            reverseAdj[end].add(new Node(start, T));
        }
        br.close();

        /* 작동 */
        int[] dist = new int[N + 1];
        int[] reverseDist = new int[N + 1];
        run(adj, dist);
        run(reverseAdj, reverseDist);

        int result = 0;
        for (int i = 1; i < N + 1; i++) {
            result = Math.max(result, dist[i] + reverseDist[i]);
        }

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(ArrayList<Node>[] arr, int[] di) {
        Arrays.fill(di, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Node now : arr[X]) {
            pq.offer(now);
            di[now.v] = now.w;
        }
        di[X] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (Node next : arr[now.v]) {
                if (di[next.v] > di[now.v] + next.w) {
                    di[next.v] = di[now.v] + next.w;
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
