package DynamicProgramming;

import java.io.*;
import java.util.*;

public class DownHillRoad {
    static int N, M;
    static int[][] field, sol;
    static boolean[][] isVisit;
    static int[] dy = {-1, 1, 0, 0,};
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
        sol = new int[N][M];
        run(0, 0); // [0, 0] 에서 시작

        if (!isVisit[N - 1][M - 1]) { // 도달하지 못한 경우
            bw.write(0 + "\n");
            bw.close();
            return;
        }

        /* 출력 */
        bw.write(sol[0][0] + "\n");
        bw.close();
    }

    private static int run(int y, int x) {
        if (y == N - 1 && x == M - 1) {
            isVisit[y][x] = true;
            sol[y][x] = 1;
            return sol[y][x];
        }

        if (isVisit[y][x]) {
            return sol[y][x];
        }
        isVisit[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (isRange(ny, nx)) {
                if (field[y][x] > field[ny][nx]) {
                    sol[y][x] += run(ny, nx); // 각 위치마다 다가 올 수 있는 모든 경로 더해주기
                }
            }
        }

        return sol[y][x];
    }

    private static boolean isRange(int ny, int nx) {
        return 0 <= ny && ny < N && 0 <= nx && nx < M;
    }
}

