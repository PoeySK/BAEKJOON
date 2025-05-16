package AccumulatedSum;

import java.io.*;
import java.util.*;

public class ScoringPoints {
    static int N, M, result = Integer.MIN_VALUE;
    static int[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < N; i++) {
            int[] colSum = new int[M];
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    colSum[k] += field[j][k];
                }
                result = Math.max(result, kadane(colSum));
            }
        }
    }

    private static int kadane(int[] arr) {
        int curSum = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            curSum = Math.max(arr[i], curSum + arr[i]);

            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }
}
