package DynamicProgramming;

import java.io.*;
import java.util.*;

public class TreeAndQuery {
    static int N, R, Q;
    static int[] parents;
    static boolean[] isVisit;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }
        parents = new int[N + 1];
        Arrays.fill(parents, 1);
        isVisit = new boolean[N + 1];
        parents[R] = run(R) + 1;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());

            bw.write(parents[u] + "\n");
        }
        br.close();
        bw.close();
    }

    private static int run(int root) {
        if (isVisit[root]) return parents[root];
        isVisit[root] = true;

        if (adj[root].isEmpty()) {
            return 1;
        }

        int count = 0;
        for (int i = 0; i < adj[root].size(); i++) {
            int node = adj[root].get(i);
            int sub = run(node);
            count += sub;
            parents[node] = sub;
        }

        return count;
    }
}
