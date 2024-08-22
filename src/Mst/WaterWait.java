package Mst;

import java.io.*;
import java.util.*;

public class WaterWait {
    static int N, result;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int W = Integer.parseInt(br.readLine());
            adj[0].add(new Node(i + 1, W)); // 임의의 0번 노드
            adj[i + 1].add(new Node(0, W)); // 양방향
        }
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int p = Integer.parseInt(st.nextToken());
                if (p == 0) continue;
                adj[i + 1].add(new Node(j + 1, p));
            }
        }
        br.close();

        /* 작동 */
        // 임의의 0번 노드와 mst 형성
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() { // prim
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Node n : adj[1]) {
            pq.offer(n);
        }
        boolean[] isVisit = new boolean[N + 1];
        isVisit[1] = true;
        int E = 0;
        while (E != N) { // E = V - 1
            Node now = pq.poll();

            if (isVisit[now.v]) continue;
            isVisit[now.v] = true;
            result += now.w;

            for (Node next : adj[now.v]) {
                if (!isVisit[next.v]) {
                    pq.offer(next);
                }
            }
            E++;
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
