package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Travel {
    static int N, M, K;
    static ArrayList<Node>[] courses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        courses = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            courses[i] = new ArrayList<>();
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a < b) courses[a].add(new Node(b, c));
        }
        br.close();

        /* 작동 */
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(1, 0, 1));
        int[][] dist = new int[N + 1][M + 1];
        dist[1][1] = 0;
        int result = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.v == N) {
                result = Math.max(result, dist[N][now.c]);
                continue;
            }
            for (int i = 0; i < courses[now.v].size(); i++) {
                Node next = courses[now.v].get(i);
                if (now.c + 1 <= M) {
                    if (dist[next.v][now.c + 1] < dist[now.v][now.c] + next.w) {
                        dist[next.v][now.c + 1] = dist[now.v][now.c] + next.w;
                        q.offer(new Node(next.v, next.w, now.c + 1));
                    }
                }
            }
        }

        return result;
    }

    static class Node {
        int v, w, c;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        Node(int v, int w, int c) {
            this.v = v;
            this.w = w;
            this.c = c;
        }
    }
}
