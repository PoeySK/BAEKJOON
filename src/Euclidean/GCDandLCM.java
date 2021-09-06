package Euclidean;

import java.io.*;
import java.util.*;

public class GCDandLCM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");

        // 왼쪽과 오른쪽의 수 관계를 동일한 관계로 지정.
        int a = Math.max(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        int b = Math.min(Integer.parseInt(s[0]), Integer.parseInt(s[1]));

        int save = a * b; // 최소공배수를 구하기 위해 초기 두 값의 곱을 저장해 놓음.

        int result;
        while (true) { // 큰수에서 작은 수를 나눈뒤 나머지를 작은 수에 나누는 것을 반복.
            result = a % b;
            a = b;
            b = result;

            if (result == 0) { // 결과가 0이 나오면 최대공약수가 나옴.
                break;
            }
        }
        bw.write(a + "\n" + (save / a)); // 저장해놓은 값에서 최대공약수를 나눠주면 최소공배수가 나옴.
        bw.flush();
        br.close();
        bw.close();
    }
}