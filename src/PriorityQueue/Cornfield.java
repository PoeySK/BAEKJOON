package PriorityQueue;

import java.io.*;
import java.util.*;

public class Cornfield {
    static int N, M, K;
    static int[][] field;
    static StringBuilder sb;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        br.close();

        sb = new StringBuilder();
        run();

        bw.write(sb.toString() + "\n");
        bw.close();
    }

    private static void run() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    q.offer(new Node(i, j, field[i][j]));
                    field[i][j] = -1;
                }
            }
        }

        while (!q.isEmpty() && K > 0) {
            Node now = q.poll();
            K--;
            sb.append(now.y + 1).append(" ").append(now.x + 1).append("\n");

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (1 <= ny && ny < N - 1 && 1 <= nx && nx < M - 1 && field[ny][nx] > 0) {
                    q.offer(new Node(ny, nx, field[ny][nx]));
                    field[ny][nx] = -1;
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int y, x, value;

        Node(int y, int x, int value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return o.value - this.value;
        }
    }
}
