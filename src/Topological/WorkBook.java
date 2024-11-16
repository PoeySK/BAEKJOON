package Topological;

import java.io.*;
import java.util.*;

public class WorkBook {
    static int N, M;
    static int[] degree;
    static ArrayList<Integer>[] adj;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            degree[B]++;
            adj[A].add(B);
        }
        br.close();

        /* 작동 */
        sb = new StringBuilder();
        run();

        /* 출력 */
        bw.write(sb.toString() + "\n");
        bw.close();
    }

    private static void run() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                pq.offer(i);
                degree[i]--;
            }
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();

            sb.append(now + " ");
            for(int next : adj[now]) {
                if(degree[next] != 0) {
                    degree[next]--;
                    if(degree[next] == 0) {
                        pq.offer(next);
                        degree[next]--;
                    }
                }
            }
        }
    }
}
