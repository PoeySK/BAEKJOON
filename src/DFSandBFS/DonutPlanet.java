package DFSandBFS;

import java.io.*;
import java.util.*;

public class DonutPlanet {
    static int N, M;
    static int[][] field;
    static boolean[][] isVisit;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

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
        br.close();

        isVisit = new boolean[N][M];
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 0 && !isVisit[i][j]) {
                    isVisit[i][j] = true;
                    run(i, j);
                    result++;
                }
            }
        }

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int sy, int sx) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(sy, sx));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny == N) ny = 0;
                if (nx == M) nx = 0;
                if (ny < 0) ny = N - 1;
                if (nx < 0) nx = M - 1;

                if (isVisit[ny][nx] || field[ny][nx] == 1) continue;
                isVisit[ny][nx] = true;
                q.offer(new Node(ny, nx));
            }
        }
    }

    private static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
