package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Segmentation {
    static int N, M;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + number;
        }
        br.close();

        /* 작동 */
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int[][] sol = new int[M + 1][N + 1];
        for (int i = 0; i < M + 1; i++) {
            Arrays.fill(sol[i], -987654321);
        }

        // M == 1
        for (int i = 1; i <= N; i++) {
            sol[1][i] = Math.max(sol[1][i - 1], sum[i]);
            for (int k = i - 2; k >= 0; k--) {
                sol[1][i] = Math.max(sol[1][i], sum[i] - sum[k + 1]);
            }
        }

        // M >= 2
        for (int i = 2; i <= M; i++) {
            for (int j = i * 2 - 1; j <= N; j++) { // 구간별 시작지점 -> 2 * m - 1\
                sol[i][j] = Math.max(sol[i][j - 1], sol[i - 1][j - 2] + sum[j] - sum[j - 1]);
                for (int k = j - 2; k >= (i - 1) * 2 - 1; k--) {
                    sol[i][j] = Math.max(sol[i][j], sol[i - 1][k] + (sum[j] - sum[k + 1]));
                }
            }
        }

        return sol[M][N];
    }
}
