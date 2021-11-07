package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Knapsack {
    static int N, K; // 물품의 수, 버틸 수 있는 무게
    static int[] w, v; // 넣을 물품의 무게와 가치
    static int[][] b; // 물건들
    static int[][] sTable; // solution table

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);

        w = new int[N];
        v = new int[N];
        b = new int[N][2]; // row 인덱스는 weight, value만 들어감

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int weight = Integer.parseInt(input[0]);
            int value = Integer.parseInt(input[1]);

            b[i][0] = weight;
            b[i][1] = value;
        }

        Arrays.sort(b, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // o[n]: row의 n번째 값에 따른 정렬
            }
        }); // 무게에 따른 오름차순 정렬

        // 무게와 가치 배열에 값 저장
        for (int i = 0; i < N; i++) {
            w[i] = b[i][0];
            v[i] = b[i][1];
        }

        sTable = new int[N + 1][K + 1]; // 초기 0의 무게와 0의 가치를 넣어줌으로 배열의 크기 1 증가
        dp();

        bw.write(sTable[N][K] + "\n"); // 가장 마지막 인덱스 배열의 값이 최댓값
        bw.flush();
        br.close();
        bw.close();

    }

    public static void dp() {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (i == 0 || j == 0) { // 현재 짐의 개수가 0 or 현재 들 수 있는 무게가 0
                    sTable[i][j] = 0;
                } else if (w[i - 1] > j) { // 현재 짐의 무게 > 현재 들 수 있는 무게
                    sTable[i][j] = sTable[i - 1][j]; // 현재 들 수 있는 무게에서 들 수 있는 짐의 개수 - 1의 값 가져옴.
                } else {
                    /* 현재 무게에 맞는 최대 무게 짐의 가치
                     * vs
                     * 현재 가질 수 있는 물품의 개수 - 1에서 같은 무게의 가치
                    */
                    int max = Math.max(sTable[i - 1][j], v[i - 1] + sTable[i - 1][j - w[i - 1]]);
                    sTable[i][j] = max;
                }
            }
        }
    }

}