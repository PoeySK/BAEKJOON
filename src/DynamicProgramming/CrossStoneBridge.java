package DynamicProgramming;

import java.io.*;
import java.util.*;

public class CrossStoneBridge {
    static int N;
    static String target;
    static String[] ad = new String[2];
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        target = st.nextToken();
        st = new StringTokenizer(br.readLine());
        ad[0] = st.nextToken();
        st = new StringTokenizer(br.readLine());
        ad[1] = st.nextToken();
        br.close();

        N = ad[0].length();
        dp = new int[2][target.length() + 1][N + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < target.length() + 1; j++) {
                for (int k = 0; k < N + 1; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        int result = run(-1, 0, 0) + run(-1, 0, 1);

        bw.write(result + "\n");
        bw.close();
    }

    private static int run(int now, int tn, int isAD) {
        if (tn == target.length()) {
            return 1;
        }
        if (dp[isAD][tn][now + 1] != -1) return dp[isAD][tn][now + 1];

        int count = 0;
        for (int i = now + 1; i < N; i++) {
            if (target.charAt(tn) == ad[isAD].charAt(i)) {
                count += run(i, tn + 1, (isAD + 1) % 2);
            }
        }

        return dp[isAD][tn][now + 1] = count;
    }
}
