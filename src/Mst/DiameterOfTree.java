package Mst;

import java.io.*;
import java.util.*;

public class DiameterOfTree {
    static int N, max, maxIdx;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) {
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());
                adj[now].add(new Node(next, weight));
            }
        }
        br.close();

        /* 작동 */
        // 시작 노드에서 가장 먼 노드 찾기
        int[] dist = new int[N + 1];
        run(1, dist);

        // 위에서 찾은 노드에서 가장 먼 노드 찾기
        dist = new int[N + 1];
        max = 0;
        run(maxIdx, dist);


        /* 출력 */
        bw.write(dist[maxIdx] + "\n");
        bw.close();
    }

    private static void run(int start, int[] dist) { // 최댓값 prim 사용
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] isVisit = new boolean[N + 1];
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (isVisit[now.v]) {
                continue;
            }
            isVisit[now.v] = true;

            for (Node next : adj[now.v]) {
                if (!isVisit[next.v] && dist[next.v] < dist[now.v] + next.w) {
                    dist[next.v] = dist[now.v] + next.w;
                    if (max < dist[next.v]) {
                        max = dist[next.v];
                        maxIdx = next.v;
                    }
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
        public int compareTo(Node o) { // 최댓값
            return o.w - this.w;
        }
    }
}
