package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Coursework {
    static int N, K;
    static Node[] course;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        course = new Node[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            course[i] = new Node(I, T);
        }
        br.close();

        bw.write(run() + "\n");
        bw.close();
    }

    private static int run() {
        int[] dp = new int[N + 1];
        for (int i = 0; i < K; i++) {
            for (int j = N; j >= course[i].t; j--) {
                dp[j] = Math.max(dp[j], dp[j - course[i].t] + course[i].l);
            }
        }

        return dp[N];
    }

    private static class Node {
        int l, t;

        public Node(int l, int t) {
            this.l = l;
            this.t = t;
        }
    }
}
