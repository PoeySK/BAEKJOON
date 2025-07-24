package DynamicProgramming;

import java.io.*;
import java.util.*;

public class UphillNumber {
    final static int MOD = 10_007;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        br.close();
        if (N == 1) {
            bw.write(10 + "\n");
            bw.close();
            return;
        }
        numbers = new int[10];
        numbers[0] = 1;
        for (int i = 1; i < 10; i++) {
            numbers[i] += numbers[i - 1] + i + 1;
        }

        if (N > 2) run(N);

        bw.write(numbers[9] + "\n");
        bw.close();
    }

    private static void run(int N) {
        for (int i = 2; i < N; i++) {
            for (int j = 9; j >= 0; j--) {
                for (int k = 0; k < j; k++) {
                    numbers[j] = (numbers[j] + numbers[k]) % MOD;
                }
            }
        }

    }
}
