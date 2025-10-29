package Math;

import java.io.*;
import java.util.*;

public class SquareNoNoNumber {
    static int count;
    static boolean[] prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        br.close();

        count = (int) (max - min) + 1;
        prime = new boolean[count];
        run(min, max);

        bw.write(count + "\n");
        bw.close();
    }

    private static void run(long min, long max) {
        for (long i = 2; i <= Math.sqrt(max); i++) {
            long pow = i * i;
            long share = min % pow == 0 ? min / pow : min / pow + 1;

            for (long j = share; j * pow <= max; j++) {
                int idx = (int) (j * pow - min);
                if (prime[idx]) continue;
                prime[idx] = true;
                count--;
            }
        }
    }
}
