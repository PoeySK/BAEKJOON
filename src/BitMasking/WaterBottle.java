package BitMasking;

import java.io.*;
import java.util.*;

public class WaterBottle {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        br.close();

        int result = run();

        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        if (N == K) return 0;

        int count = 0;
        while (Integer.bitCount(N) > K) {
            int lsb = N & -N; // 비트 중 가장 작은 1의 위치
            count += lsb;
            N += lsb;
        }

        return count;
    }
}
