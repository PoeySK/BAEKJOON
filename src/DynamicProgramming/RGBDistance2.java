package DynamicProgramming;

import java.io.*;
import java.util.*;

public class RGBDistance2 {
    static int N, result = Integer.MAX_VALUE;
    static int[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        field = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            field[i][0] = R;
            field[i][1] = G;
            field[i][2] = B;
        }
        br.close();

        /* 작동 */
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int[][] sol = new int[N][3];

        // 1번째
        for (int idx = 0; idx < 3; idx++) {
            Arrays.fill(sol[0], 1001);
            sol[0][idx] = field[0][idx];

            // 2 ~ N-1 번째
            for (int i = 1; i < N - 1; i++) {
                for (int j = 0; j < 3; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int k = 0; k < 3; k++) {
                        if (k == j) continue;
                        min = Math.min(min, sol[i - 1][k]);
                    }
                    sol[i][j] = min + field[i][j];
                }
            }

            // N 번째
            for (int i = 0; i < 3; i++) {
                if (i == idx) continue; // 1번, N번 비교
                for (int j = 0; j < 3; j++) {
                    if (j == i) continue; // N-1번, N번 비교
                    result = Math.min(result, sol[N - 2][j] + field[N - 1][i]);
                }
            }
        }
    }
}
