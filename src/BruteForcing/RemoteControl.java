package BruteForcing;

import java.io.*;
import java.util.*;

public class RemoteControl {
    static String N;
    static int M, len, result = Integer.MAX_VALUE;
    static boolean[] ableNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        if (N.equals("100")) {
            bw.write("0");
            bw.close();
            return;
        }
        len = N.length();
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        ableNumbers = new boolean[10];
        Arrays.fill(ableNumbers, true);
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int brokenNumber = Integer.parseInt(st.nextToken());
                ableNumbers[brokenNumber] = false;
            }
        }
        br.close();

        result = Math.abs(100 - Integer.parseInt(N)); // 시작 채널: 100
        run(0, 0, "");

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int digits, int count, String startChannel) {
        if (!startChannel.isEmpty()) {
            int cha = Math.abs(Integer.parseInt(N) - Integer.parseInt(startChannel));
            result = Math.min(result, count + cha);
        }
        if (digits == len + 1) return;

        for (int i = 0; i < 10; i++) {
            if (ableNumbers[i]) {
                run(digits + 1, count + 1, startChannel + i);
            }
        }
    }
}
