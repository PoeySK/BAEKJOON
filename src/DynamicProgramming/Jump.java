package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Jump {
    static int N;
    static long result;
    static int[][] field;
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        field = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        long[][] sol = new long[N][N];
        sol[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int jp = field[i][j];
                if (i == N - 1 && j == N - 1) {
                    result = sol[N - 1][N - 1];
                    return;
                }
                for (int k = 0; k < 2; k++) {
                    int ny = i + dy[k] * jp;
                    int nx = j + dx[k] * jp;

                    if (isRange(ny, nx)) {
                        sol[ny][nx] += sol[i][j];
                    }
                }
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
