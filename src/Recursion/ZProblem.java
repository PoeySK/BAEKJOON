package Recursion;

import java.io.*;
import java.util.*;

public class ZProblem {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int r = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);
        br.close();

        /* 작동 */
        int size = (int) Math.pow(2, N);
        run(size, r, c);

        /* 출력 */
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    private static void run(int size, int r, int c) {
        if (size == 1) {
            return;
        }

        int halfSize = size / 2;
        if (r < halfSize && c < halfSize) { // 2사분면
            run(halfSize, r, c);
        } else if (r < halfSize && c >= halfSize) { // 1사분면
            count += halfSize * halfSize;
            run(halfSize, r, c - halfSize);
        } else if (r >= halfSize && c >= halfSize) { // 4사분면
            count += halfSize * halfSize * 3;
            run(halfSize, r - halfSize, c - halfSize);
        } else if (r >= halfSize && c < halfSize) { // 3사분면
            count += halfSize * halfSize * 2;
            run(halfSize, r - halfSize, c);
        }
    }
}
