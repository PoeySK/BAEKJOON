package DynamicProgramming;

import java.io.*;
import java.util.*;

public class DownHill {
    static int N, M;
    static int[][] field, sol;
    static int[] dy = {1, 0, 0, -1,};
    static int[] dx = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        field = new int[M][N];
        sol = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(sol[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        run(0, 0);

        /* 출력 */
        bw.write(sol[0][0] + "\n");
        bw.close();
    }

    private static int run(int y, int x) {
        if (y == M - 1 && x == N - 1) {
            return 1;
        }

        if (sol[y][x] > -1) { // 방문 확인
            return sol[y][x];
        }
        sol[y][x] = 0; // 방문 처리

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (isRange(ny, nx) && field[y][x] > field[ny][nx]) {
                sol[y][x] += run(ny, nx);
            }
        }

        return sol[y][x];
    }

    private static boolean isRange(int ny, int nx) {
        return 0 <= ny && ny < M && 0 <= nx && nx < N;
    }
}
