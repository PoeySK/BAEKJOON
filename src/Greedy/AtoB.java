package Greedy;

import java.util.*;
import java.io.*;

public class AtoB {
    static long A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        A = Long.parseLong(input[0]);
        B = Long.parseLong(input[1]);

        int count = 0;
        boolean isCheck = true;
        while (A != B) {
            if (A > B) { // 생성불가
                isCheck = false;
                break;
            }

            long one = B % 10; // 일의 자리 숫자 구하기
            if (one == 1) {
                B = B / 10;
            } else if (B % 2 == 0){
                B = B / 2;
            } else { // 생성불가
                isCheck = false;
                break;
            }
            // System.out.println("B: " + B);
            count++;
        }
        if (isCheck) {
            bw.write((count + 1) + "\n");
        } else {
            bw.write("-1");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
