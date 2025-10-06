package DynamicProgramming;

import java.io.*;
import java.util.*;

public class TheaterSeat {
    static int N, M, result = 1;
    static int[] dp;
    static boolean[] seats;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        seats = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            seats[Integer.parseInt(st.nextToken())] = true;
        }
        br.close();

        dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = 2; i < N + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int count = 0;
        for (int i = 1; i < N + 1; i++) {
            if (seats[i]) {
                result *= dp[count];
                count = 0;
            } else {
                count++;
            }
        }
        result *= dp[count];
    }
}
