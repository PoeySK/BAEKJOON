package Greedy;

import java.io.*;
import java.util.*;

public class Coin0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }

        int count = 0;

        for (int i = coins.length - 1; i >= 0; i--) {
            if (coins[i] <= K) {
                K -= coins[i];
                i++;
                count++;
                if (K == 0) {
                    break;
                }
            }
        }

        bw.write(count + "\n");
        bw.flush();

    }
}