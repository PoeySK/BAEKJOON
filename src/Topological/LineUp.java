package Topological;

import java.io.*;
import java.util.*;

public class LineUp {
    static int N, M;
    static ArrayList<Integer>[] adj;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] degree = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adj[A].add(B);
            degree[B]++;
        }
        br.close();

        /* 작동 */
        sb = new StringBuilder();
        run(degree);

        /* 출력 */
        bw.write(String.valueOf(sb));
        bw.close();
    }

    private static void run(int[] degree) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if (degree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for (Integer next : adj[now]) {
                degree[next]--;
                if (degree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}
