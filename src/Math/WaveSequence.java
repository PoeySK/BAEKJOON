package Math;

import java.io.*;
import java.util.*;

public class WaveSequence {
    static long[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            /* 작동 */
            numbers = new long[N];
            if (N >= 1) {
                numbers[0] = 1;
            }
            if (N >= 2) {
                numbers[1] = 1;
            }
            if (N >= 3) {
                numbers[2] = 1;
            }
            if (N >= 4) {
                numbers[3] = 2;
            }
            if (N >= 5) {
                numbers[4] = 2;
            }

            if(numbers[N - 1] == 0) {
                run(N);
            }

            /* 출력 */
            bw.write(numbers[N - 1] + "\n");
        }
        br.close();
        bw.close();
    }

    private static void run(int n) {
        for (int i = 5; i < n; i++) {
            numbers[i] = numbers[i - 1] + numbers[i - 5];
        }
    }
}
