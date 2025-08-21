package DynamicProgramming;

import java.io.*;
import java.util.*;

public class PlumTree {
    static int T, W;
    static int[] tree;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        tree = new int[T];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        dp = new int[T][2][W + 1];
        dp[0][0][W] = tree[0] == 1 ? 1 : 0;
        run(1, 0, W);

        dp[0][1][W - 1] = tree[0] == 2 ? 1 : 0;
        run(1, 1, W - 1);

        int result = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < W + 1; j++) {
                result = Math.max(result, dp[T - 1][i][j]);
            }
        }
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int time, int now, int move) {
        if (time == T) return;

        if (tree[time] == now + 1) {
            if (dp[time][now][move] < dp[time - 1][now][move] + 1) {
                dp[time][now][move] = dp[time - 1][now][move] + 1;
                run(time + 1, now, move);
            }
        } else {
            if (dp[time][now][move] < dp[time - 1][now][move]) {
                dp[time][now][move] = dp[time - 1][now][move];
                run(time + 1, now, move);
            }
        }

        if (move > 0) {
            int mod = (now + 1) % 2;
            if (tree[time] == (mod + 1)) {
                if (dp[time][mod][move - 1] < dp[time - 1][now][move] + 1) {
                    dp[time][mod][move - 1] = dp[time - 1][now][move] + 1;
                    run(time + 1, mod, move - 1);
                }
            } else {
                if (dp[time][mod][move - 1] < dp[time - 1][now][move]) {
                    dp[time][mod][move - 1] = dp[time - 1][now][move];
                    run(time + 1, mod, move - 1);
                }
            }
        }
    }
}
