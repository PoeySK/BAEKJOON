package BSearch;

import java.io.*;
import java.util.*;

public class SumIsZero {
    static int N;
    static long result;
    static int[] teams;
    static boolean[] isCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        teams = new int[N];
        isCheck = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            teams[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(teams);
        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int sum = teams[i] + teams[j];
                int lowerBound = lower(-sum, j + 1);
                if (lowerBound == N || teams[lowerBound] != -sum) continue;
                int upperBound = upper(-sum, j + 1);
                result += upperBound - lowerBound;
            }
        }
    }

    private static int lower(int target, int start) {
        int left = start, right = N;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;

            if (target <= teams[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int upper(int target, int start) {
        int left = start, right = N;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;

            if (target < teams[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
