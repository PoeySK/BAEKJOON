package Euclidean;

import java.io.*;
import java.util.*;

public class TheLeastCommonMultiple {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        long a = Long.parseLong(s[0]); // 작은 수
        long b = Long.parseLong(s[1]); // 큰 수
        long save = a*b; // 최소공배수의 기본인 두 수의 곱에서 찾기위해 먼저 두 수를 곱해줌.

        long result;
        while (true) { // 최대공약수를 구하는 방법을 이용해 최소공배수를 구함.
            result = b % a;

            b = a;
            a = result;

            if (result == 0) { // 두 수가 맞아떨어지거나 b보다 a가 더 커지면 종료.
                break;
            }
        }

        bw.write((save / b) + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

}