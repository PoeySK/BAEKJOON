package DynamicProgramming;

import java.io.*;
import java.util.*;

public class FillTile {
    static int[] sol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        /* 작동 */
        if (N % 2 == 1) {
            bw.write(0 + "\n");
            bw.close();
            return;
        }
        sol = new int[N + 1];
        sol[0] = 1;
        sol[2] = 3;
        run(N);

        /* 출력 */
        bw.write(sol[N] + "\n");
        bw.close();
    }

    private static void run(int N) {
        /*
          N == 2 -> 3
          N == 4 -> sol[2] * 3 + sol[0] * 2 = 11
          N == 6 -> sol[4] * 3 + (sol[4] + sol[2]) * 2 + sol[0] * 2
          N == 8 -> sol[6] * 3 + (sol[6] + sol[2]) * 2 + (sol[4] + sol[4]) * 2 + sol[0] * 2
          ...
          N -> sol[N - 2] * 3 + (sol[{N - i}]) * 2 + sol[0] * 2
         */
        for (int i = 4; i <= N; i += 2) { // 짝수만 생각
            sol[i] = sol[i - 2] * 3;
            for (int j = 4; j <= i; j += 2) {
                sol[i] += sol[i - j] * 2;
            }
        }
    }
}
