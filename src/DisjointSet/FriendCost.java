package DisjointSet;

import java.io.*;
import java.util.*;

public class FriendCost {
    static int N, M, k;
    static int[] costs, parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        costs = new int[N + 1];
        parents = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (v == w) continue;

            union(v, w);
        }
        br.close();

        int result = run();

        bw.write((k - result >= 0 ? result : "Oh no") + "\n");
        bw.close();
    }

    private static int run() {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < N + 1; i++) {
            set.add(find(parents[i]));
        }
        ArrayList<Integer> friends = new ArrayList<>(set);

        int total = 0;
        for (int friend : friends) {
            total += costs[friend];
        }

        return total;
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (costs[pa] < costs[pb]) {
            parents[pb] = pa;
        } else {
            parents[pa] = pb;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
