package Recursion;

import java.io.*;
import java.util.*;

public class Multiple {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        String[] input = br.readLine().split(" ");
        br.close();
        long A = Integer.parseInt(input[0]);
        long B = Integer.parseInt(input[1]);
        long C = Integer.parseInt(input[2]);

        /* 작동 */
        long result = run(A, B, C);

        /* 출력 */
        bw.write((result % C) + "");
        bw.flush();
        bw.close();
    }

    private static long run(long A, long B, long C) {
        if (B == 1) {
            return A;
        }
        long ac = run(A, B / 2, C);

        if (B % 2 == 0) { // even
            return ((ac % C) * (ac % C)) % C;
        } else { // B % 2 == 1, odd
            return (((ac % C) * (ac % C)) % C) * (A % C);
        }
    }
}
