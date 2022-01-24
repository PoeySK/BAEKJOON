package DynamicProgramming;

import java.util.*;
import java.io.*;

public class Coin {
    static int N, K;
    static int[] coin, sol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]); // 코인 개수
        K = Integer.parseInt(str[1]); // 가치
        coin = new int[N]; // 코인 저장
        sol = new int[K + 1]; // 각 가치별 필요한 코인 개수 저장
        int input;
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            coin[i] = input;
        }

        for (int i = 0; i < N; i++) {
            if (coin[i] > K) { // 가치보다 코인이 큰 경우
                continue;
            }
            sol[coin[i]]++;
            for (int j = coin[i] + 1; j <= K; j++) { // coin[i]를 사용한 후
                /*
                 * 가치를 현재 코인 값에서 하나씩 올려가며
                 * 필요한 코인의 개수를 충당시켜줌.
                 */
                sol[j] += sol[j - coin[i]];
            }

        }
        bw.write(sol[K] + "\n"); // K 가치의 경우 코인 개수 출력
        bw.flush();
        br.close();
        bw.close();
    }
}