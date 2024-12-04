package Dijkstra;

import java.io.*;
import java.util.*;

public class UnidentifiedDestination {
    static int N, M, T;
    static int[] targets;
    static ArrayList<Node>[] adj;

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
            T = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            adj = new ArrayList[N + 1];
            for (int i = 1; i < N + 1; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                adj[a].add(new Node(b, d));
                adj[b].add(new Node(a, d));
            }

            targets = new int[T];
            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                targets[i] = x;
            }
            Arrays.sort(targets);

            /* 작동 */
            int[][] dist = new int[3][N + 1];
            run(s, dist, 0);
            run(g, dist, 1);
            run(h, dist, 2);

            /* 출력 */
            for (int i = 0; i < T; i++) {
                int target = dist[0][targets[i]]; // s -> t
                int startToG = dist[0][g] + dist[1][h] + dist[2][targets[i]]; // s -> g -> h -> t
                int startToH = dist[0][h] + dist[2][g] + dist[1][targets[i]]; // s -> h -> g -> t
                if (target == startToG || target == startToH) {
                    bw.write(targets[i] + " ");
                }
            }
            bw.write("\n");
        }
        br.close();
        bw.close();
    }

    private static void run(int start, int[][] dist, int idx) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        Arrays.fill(dist[idx], Integer.MAX_VALUE);
        dist[idx][start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (Node next : adj[now.v]) {
                if (dist[idx][next.v] > dist[idx][now.v] + next.w) {
                    dist[idx][next.v] = dist[idx][now.v] + next.w;
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
