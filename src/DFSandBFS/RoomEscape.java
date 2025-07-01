package DFSandBFS;

import java.io.*;
import java.util.*;

public class RoomEscape {
    static int N, M, maxLen, result;
    static int[][] field;
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
        br.close();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 0) continue;
                boolean[][] isVisit = new boolean[N][M];
                isVisit[i][j] = true;
                run(i, j, isVisit);
            }
        }
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int sy, int sx, boolean[][] isVisit) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(sy, sx, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (maxLen == now.len) {
                result = Math.max(result, field[sy][sx] + field[now.y][now.x]);
            } else if (maxLen < now.len) {
                result = field[sy][sx] + field[now.y][now.x];
                maxLen = now.len;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && field[ny][nx] > 0 && !isVisit[ny][nx]) {
                    isVisit[ny][nx] = true;
                    q.offer(new Node(ny, nx, now.len + 1));
                }
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    static class Node {
        int y, x, len;

        Node(int y, int x, int len) {
            this.y = y;
            this.x = x;
            this.len = len;
        }
    }
}
