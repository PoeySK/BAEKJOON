package DisjointSet;

import java.io.*;
import java.util.*;

public class Cebu {
    static int N, M;
    static int[] parents;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        edges = new Edge[M];
        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(h1, h2, k);
        }
        br.close();

        Arrays.sort(edges);
        int result = run(s, e);


        bw.write(result + "\n");
        bw.close();
    }

    private static int run(int s, int e) {
        for (int i = 0; i < M; i++) {
            int u = edges[i].u;
            int v = edges[i].v;
            int k = edges[i].k;

            if (union(u, v)) {
                if (find(s) == find(e)) {
                    return k;
                }
            }
        }

        return 0;
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa < pb) {
            parents[pb] = pa;
            return true;
        } else if (pa > pb) {
            parents[pa] = pb;
            return true;
        }
        return false;
    }

    private static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    private static class Edge implements Comparable<Edge> {
        int u, v, k;

        Edge(int u, int v, int k) {
            this.u = u;
            this.v = v;
            this.k = k;
        }

        @Override
        public int compareTo(Edge o) {
            return o.k - this.k;
        }
    }
}
