package DynamicProgramming;

import java.io.*;
import java.util.*;

public class CoinDistribution {
    static int N;
    static Node[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = 3;
        StringTokenizer st;
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            coins = new Node[N];
            int total = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int money = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                coins[i] = new Node(money, count);
                total += money * count;
            }

            if (total % 2 == 1) {
                bw.write(0 + "\n");
            } else {
                bw.write(run(total) + "\n");
            }
        }
        br.close();
        bw.close();
    }

    private static int run(int total) {
        int[] m = new int[total / 2 + 1];
        Arrays.fill(m, -1);
        m[0] = 0;

        for (int i = 0; i < N; i++) {
            int value = coins[i].money;
            int count = coins[i].count;

            for (int j = 0; j < total / 2 + 1; j++) {
                if (m[j] >= 0) {
                    m[j] = count;
                } else if (j >= value && m[j - value] > 0) {
                    m[j] = m[j - value] - 1;
                } else {
                    m[j] = -1;
                }
            }
        }

        return m[total / 2] >= 0 ? 1 : 0;
    }

    private static class Node {
        int money, count;

        Node(int money, int count) {
            this.money = money;
            this.count = count;
        }
    }
}
