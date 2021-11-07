package DynamicProgramming;

import java.io.*;

public class BuyTheCard {
    static int N; // 구매할 카드의 개수
    static int[][] array; // 카드 배열
    static int[][] s; // solution table

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        array = new int[N][2];
        s = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            array[i][0] = i + 1;
            array[i][1] = Integer.parseInt(input[i]);
        }

        s = new int[N + 1][N + 1];
        dp();

        bw.write(s[N][N] + "\n");

        bw.flush();
        br.close();
        bw.close();

    }

    public static void dp() {
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (i == 0 || j == 0) {
                    s[i][j] = 0;
                } else if (i > j) { // 카드팩의 개수 > 최대 카드 개수
                    s[i][j] = s[i - 1][j];
                } else {
                    /* "knapsack과 비슷하지만 ★중복★이 가능한 문제"
                     * i: 현재 가지고 있는 카드 묶음의 집합 / j: 최대 가질 수 있는 카드팩의 개수
                     * knapsack에선 s[i - 1][j - w[i]] 이지만,
                     * 이 문제에선 s[i][j - w[i](== i)] 이다.
                     * 이유) '중복'
                     */
                    int max = Math.max(s[i - 1][j], s[i][j - i] + array[i - 1][1]);
                    s[i][j] = max;
                }
            }
        }
    }
}