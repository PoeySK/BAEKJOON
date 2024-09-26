package Mst;

import java.io.*;
import java.util.*;

public class CityDivisionPlan {
    static int N, M, E, result;
    static int[] parents;
    static PriorityQueue<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
        edges = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges.offer(new Edge(A, B, C));
        }
        br.close();

        /* 작동 */
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        while (E < N - 2) {
            Edge edge = edges.poll();
            union(edge.u, edge.v, edge.w);
        }
    }

    private static void union(int a, int b, int w) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parents[pb] = pa;
            result += w;
            E++;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}
