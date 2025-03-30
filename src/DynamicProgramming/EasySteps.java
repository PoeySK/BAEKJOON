package DynamicProgramming;

import java.io.*;
import java.util.*;

public class EasySteps {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        br.close();

        int result = run(N);

        bw.write(result + "\n");
        bw.close();
    }

    private static int run(int N) {
        int[][] sol = new int[N + 1][10];

        for (int i = 1; i <= 9; i++) {
            sol[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            sol[i][0] = sol[i - 1][1];
            sol[i][9] = sol[i - 1][8];

            for (int j = 1; j < 9; j++) {
                sol[i][j] = sol[i - 1][j - 1] + sol[i - 1][j + 1];
                sol[i][j] %= MOD;
            }
        }

        int result = 0;
        for (int i = 0; i <= 9; i++) {
            result += sol[N][i];
            result %= MOD;
        }
        return result;
    }
}
