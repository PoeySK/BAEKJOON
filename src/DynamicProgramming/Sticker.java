package DynamicProgramming;

import java.io.*;

public class Sticker {
    static int N;
    static int[][] score; // 점수판
    static int[][] s; // solution table

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            score = new int[2][N];
            for (int i = 0; i < 2; i++) { // 2N개 입력
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    score[i][j] = Integer.parseInt(input[j]);
                }
            }

            s = new int[2][N];
            // s-table 초기값 지정
            s[0][0] = score[0][0];
            s[1][0] = score[1][0];
            sum();

            int maxSum = Math.max(s[0][N - 1], s[1][N - 1]); // 가장 끝 2값 중 하나가 최댓값
            bw.write(maxSum + "\n");

        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void sum() {
        /* 두 가지 케이스를 확인하여 큰 값 저장
         * ex) s[0][2]에 최대 값을 저장할 때
         *
         * case 1: 3번 건너는 경우
         * 1 0
         * 0 1
         * 1 0
         *
         * case 2: 2번 건너는 경우
         * 1 0
         * 0 0
         * 0 1
         */
        for (int j = 1; j < N; j++) {
            for (int i = 0; i < 2; i++) {
                int max = 0; // 두 case 중 큰 값 저장 ( 점수 범위: 0 < x <= 100 )
                if (i == 0) {
                    if (j == 1) {
                        s[i][j] = score[i][j] + s[i + 1][j - 1];
                    } else {
                        int case1 = s[i + 1][j - 1] + score[i][j];
                        int case2 = s[i + 1][j - 2] + score[i][j];
                        max = Math.max(case1, case2);
                        s[i][j] = max;
                    }
                } else {
                    if (j == 1) {
                        s[i][j] = score[i][j] + s[i - 1][j - 1];
                    } else {
                        int case1 = s[i - 1][j - 1] + score[i][j];
                        int case2 = s[i - 1][j - 2] + score[i][j];
                        max = Math.max(case1, case2);
                        s[i][j] = max;
                    }
                }
            }
        }
    }
}