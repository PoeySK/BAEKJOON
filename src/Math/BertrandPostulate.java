package Math;

import java.io.*;

public class BertrandPostulate {
    static boolean[] isPrime = new boolean[123456 * 2 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        setPrime();

        /* EOF */
        String input;
        while ((input = br.readLine()) != null) {
            if (input.equals("0")) break;

            int number = Integer.parseInt(input);
            /* 작동 */
            int result = run(number);

            /* 출력 */
            bw.write(result + "\n");
        }
        br.close();
        bw.close();
    }

    private static int run(int number) {
        int count = 0;
        for (int i = number + 1; i <= number * 2; i++) {
            if (!isPrime[i]) count++;
        }

        return count;
    }

    private static void setPrime() {
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(123456 * 2); i++) {
            if (isPrime[i]) continue;

            for (int j = i * i; j <= 123456 * 2; j += i) {
                isPrime[j] = true;
            }
        }
    }
}
