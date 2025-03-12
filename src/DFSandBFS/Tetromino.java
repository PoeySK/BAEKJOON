package DFSandBFS;

import java.io.*;
import java.util.*;

public class Tetromino {
    static int N, M, result;
    static int[][] field;
    static boolean[][] isVisit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
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

        /* 작동 */
        isVisit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int crossSum = field[i][j];
                if (i == 0) { // 상단
                    if (j != 0 && j != M - 1) {
                        for (int k = 0; k < 4; k++) {
                            if (k == 0) continue;
                            int ny = dy[k] + i;
                            int nx = dx[k] + j;
                            crossSum += field[ny][nx];
                        }
                        result = Math.max(result, crossSum);
                    }
                } else if (i == N - 1) { // 하단
                    if (j != 0 && j != M - 1) {
                        for (int k = 0; k < 4; k++) {
                            if (k == 1) continue;
                            int ny = dy[k] + i;
                            int nx = dx[k] + j;
                            crossSum += field[ny][nx];
                        }
                        result = Math.max(result, crossSum);
                    }
                } else if (j == 0) { // 좌측
                    if (i != N - 1) {
                        for (int k = 0; k < 4; k++) {
                            if (k == 2) continue;
                            int ny = dy[k] + i;
                            int nx = dx[k] + j;
                            crossSum += field[ny][nx];
                        }
                        result = Math.max(result, crossSum);
                    }
                } else if (j == M - 1) { // 우측
                    if (i != N - 1) {
                        for (int k = 0; k < 4; k++) {
                            if (k == 3) continue;
                            int ny = dy[k] + i;
                            int nx = dx[k] + j;
                            crossSum += field[ny][nx];
                        }
                        result = Math.max(result, crossSum);
                    }
                } else { // 가운데
                    for (int k = 0; k < 4; k++) {
                        int ny = dy[k] + i;
                        int nx = dx[k] + j;
                        crossSum += field[ny][nx];
                    }
                    for (int k = 0; k < 4; k++) {
                        int ny = dy[k] + i;
                        int nx = dx[k] + j;
                        result = Math.max(result, crossSum - field[ny][nx]);
                    }
                }
                run(i, j, 1, 0);
            }
        }

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int y, int x, int count, int value) {
        value += field[y][x];
        isVisit[y][x] = true;
        if (count == 4) {
            result = Math.max(result, value);
            isVisit[y][x] = false;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (isRange(ny, nx) && !isVisit[ny][nx]) {
                run(ny, nx, count + 1, value);
            }
        }
        isVisit[y][x] = false;
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
