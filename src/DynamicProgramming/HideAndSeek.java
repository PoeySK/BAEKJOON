package DynamicProgramming;

import java.io.*;
import java.util.*;

public class HideAndSeek {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        /* 작동 */
        int result = N - K;
        if (N < K) {
            result = run();
        }

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int[] sol = new int[K + 1];
        for (int i = 0; i < N; i++) {
            sol[i] = N - i;
        }

        for (int i = N + 1; i <= K; i++) {
            sol[i] = sol[i - 1] + 1;
            if (i % 2 == 0) {
                sol[i] = Math.min(sol[i], sol[i / 2]);
            } else {
                int min = Math.min(sol[(i - 1) / 2] + 1, sol[(i + 1) / 2] + 1);
                sol[i] = Math.min(sol[i], min);
            }
        }


        return sol[K];
    }
}
