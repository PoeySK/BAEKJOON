package Mst;

import java.io.*;
import java.util.*;

public class NetworkConnection {
    static int V, E, result;
    static int[] parent;
    static PriorityQueue<Edge> edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        edge = new PriorityQueue<>();

        StringTokenizer st;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge.offer(new Edge(u, v, w));
        }
        br.close();

        /* 작동 */
        parent = new int[V + 1]; // 부모 정보
        for (int i = 1; i < V + 1; i++) { // 초기화: 자기 자신 가르킴
            parent[i] = i;
        }
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        while (!edge.isEmpty()) {
            Edge e = edge.poll();
            if (find(e.u) != find(e.v)) {
                union(e.u, e.v);
                result += e.w;
            }
        }
    }

    private static void union(int a, int b) {
        // u, v 의 부모 찾기
        int ap = find(a);
        int bp = find(b);

        if (ap != bp) { // u -> v
            parent[bp] = ap;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
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
