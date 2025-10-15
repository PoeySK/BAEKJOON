package GraphTheory;

import java.io.*;
import java.util.*;

public class LCA {
    static int N, M, lv;
    static int[] parents, level;
    static ArrayList<Integer>[] tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        level = new int[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            tree[i] = new ArrayList<>();
            parents[i] = i;
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        setting();

        sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(run(a, b)).append("\n");
        }
        br.close();

        bw.write(sb.toString() + "\n");
        bw.close();
    }

    private static int run(int a, int b) {
        while(parents[a] != parents[b]) {
            if (level[a] < level[b]) b = parents[b];
            else a = parents[a];
        }

        while (a != b) { // 본인도 포함
            a = parents[a];
            b = parents[b];
        }

        return a;
    }

    private static void setting() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        boolean[] isVisit = new boolean[N + 1];
        isVisit[1] = true;
        level[1] = 0;
        lv = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            lv++;
            for (int i = 0; i < size; i++) {
                int now = q.poll();

                for (int next : tree[now]) {
                    if (isVisit[next]) continue;
                    isVisit[next] = true;
                    q.offer(next);
                    parents[next] = now;
                    level[next] = lv;
                }
            }
        }
    }
}
