package Math;

import java.io.*;
import java.util.*;

public class ContinuousPrime {
    static int result;
    static boolean[] isPrime;
    static ArrayList<Integer> prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        int N = Integer.parseInt(br.readLine());
        br.close();

        /* 작동 */
        isPrime = new boolean[N + 1];
        prime = new ArrayList<>();
        setPrime(N);
        run(N);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int N) { // 투포인터
        int len = prime.size();
        int start = 0;
        int end = 0;
        int sum = 0;
        while (start < len) {
            while (sum < N && end < len) {
                sum += prime.get(end++);
            }

            if (sum == N) {
                result++;
            }
            sum -= prime.get(start++);
        }
    }

    private static void setPrime(int N) { // N 이하 소수 구하기
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (isPrime[i]) continue;
            for (int j = i * i; j <= N; j += i) {
                isPrime[j] = true;
            }
        }

        for (int i = 2; i <= N; i++) {
            if (!isPrime[i]) prime.add(i);
        }
    }
}
