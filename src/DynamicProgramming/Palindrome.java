package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Palindrome {
    static int N;
    static int[] numbers;
    static boolean[][] sol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        sol = new boolean[N][N];
        run();

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            bw.write((sol[S - 1][E - 1] ? "1" : "0") + "\n");
        }
        br.close();
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < N; i++) {
            sol[i][i] = true;

            if (i < N - 1 && numbers[i] == numbers[i + 1]) {
                sol[i][i + 1] = true;
            }
        }

        for (int i = 2; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                if (numbers[j] == numbers[j + i] && sol[j + 1][j + i - 1]) {
                    sol[j][j + i] = true;
                }
            }
        }
    }
}
