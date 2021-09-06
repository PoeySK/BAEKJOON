package Euclidean;

import java.io.*;
import java.util.*;

public class CommonFactor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        int GCD = Integer.parseInt(s[0]);
        int LCM = Integer.parseInt(s[1]);

        long x = 1, y = 1; // 초기화
        long result = LCM / GCD; // 최소공배수에서 최대공약수를 나누어 나온 값의 약수들 중 두 값이 답이 나옴.
        for (long i = 1; i * i <= result; i++) { // 약수를 구하여 가장 마지막에 나온 값이 최소합인 약수
            if (result % i == 0) { // 약수가 발견되면 값은 0으로 나옴.
                // 최대공약수를 구해주고 그 값이 1이면 최소공배수와 최대공약수가 초기 입력값과 동일함.
                long r;
                long a = result / i;
                long b = i;
                while (true) {
                    r = a % b;
                    a = b;
                    b = r;
                    if (r == 0) {
                        break;
                    }
                }
                if (a == 1) {
                    x = i;
                    y = result / i;
                }
            }
        }

        bw.write((x * GCD) + " " + (y * GCD) + "\n"); // 나온 값에서 최대공약수를 곱해주면 두 자연수가 출력됨.
        bw.flush();
        br.close();
        bw.close();
    }
}