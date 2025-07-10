package DFSandBFS;

import java.io.*;
import java.util.*;

public class Iceberg {
    static int N, M, result;
    static int[][] field;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

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

        run();

        bw.write((result - 1) + "\n");
        bw.close();
    }

    private static void run() {
        int island = 0;
        while (island < 2) {
            island = find();
            if (island == 0) {
                result = 1;
                return;
            }
            result++;
        }
    }

    private static int find() {
        int count = 0;
        boolean[][] isVisit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] > 0 && !isVisit[i][j]) {
                    bfs(i, j, isVisit);
                    count++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isVisit[i][j]) {
                    field[i][j] -= removeCount(i, j, isVisit);
                }
            }
        }
        return count;
    }

    private static void bfs(int y, int x, boolean[][] isVisit) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(y, x));
        isVisit[y][x] = true;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && field[ny][nx] > 0 && !isVisit[ny][nx]) {
                    isVisit[ny][nx] = true;
                    q.offer(new Node(ny, nx));
                }
            }
        }
    }


    private static int removeCount(int y, int x, boolean[][] isVisit) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (isRange(ny, nx) && !isVisit[ny][nx]) count++;
        }

        return count;
    }

    private static boolean isRange(int ny, int nx) {
        return 0 <= ny && ny < N && 0 <= nx && nx < M;
    }

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
