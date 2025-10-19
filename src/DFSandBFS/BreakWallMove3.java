package DFSandBFS;

import java.io.*;
import java.util.*;

public class BreakWallMove3 {
    static int N, M, K, result = Integer.MAX_VALUE;
    static int[][] field;
    static int[] dy = {1, 0, 0, -1};
    static int[] dx = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(input[j]);
            }
        }
        br.close();

        run();

        bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n");
        bw.close();
    }

    private static void run() {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 1, K));

        boolean[][][] isVisit = new boolean[N][M][K + 1];
        isVisit[0][0][K] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.y == N - 1 && now.x == M - 1) {
                result = now.time;
                return;
            }

            boolean isStay = false;
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx)) {
                    if (field[ny][nx] == 1) {
                        if (now.time % 2 == 1) {
                            if (now.b > 0 && !isVisit[ny][nx][now.b - 1]) {
                                isVisit[ny][nx][now.b - 1] = true;
                                q.offer(new Node(ny, nx, now.time + 1, now.b - 1));
                            }
                        } else {
                            if (isStay) continue;
                            isStay = true;
                            q.offer(new Node(now.y, now.x, now.time + 1, now.b));
                        }
                    } else {
                        if (!isVisit[ny][nx][now.b]) {
                            isVisit[ny][nx][now.b] = true;
                            q.offer(new Node(ny, nx, now.time + 1, now.b));
                        }
                    }
                }

            }
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    private static class Node {
        int y, x, time, b;

        Node(int y, int x, int time, int b) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.b = b;
        }
    }
}
