package Math;

import java.io.*;
import java.util.*;

public class GoldenbachSpeculation {
    static int[] result = new int[2];
    static boolean[] isPrime = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        setPrime();

        while (TC-- > 0) {
            /* 입력 */
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            /* 작동 */
            run(n);

            /* 출력 */
            bw.write(result[0] + " " + result[1] + "\n");
        }
        br.close();
        bw.close();
    }

    private static void run(int n) {
        for (int i = 0; i <= n / 2 - 1; i++) {
            if (!isPrime[n / 2 - i] && !isPrime[n / 2 + i]) {
                result[0] = n / 2 - i;
                result[1] = n / 2 + i;
                return;
            }
        }
    }

    private static void setPrime() {
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(10000); i++) {
            if (isPrime[i]) continue;

            for (int j = i * i; j <= 10000; j += i) {
                isPrime[j] = true;
            }
        }
    }
}
