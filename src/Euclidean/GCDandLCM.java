package Euclidean;

import java.io.*;

public class GCDandLCM {
    static int result; // 결과 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");

        // 왼쪽과 오른쪽의 수 관계를 동일한 관계로 지정. a: max, b: min
        int a = Math.max(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        int b = Math.min(Integer.parseInt(s[0]), Integer.parseInt(s[1]));

        bw.write(gcd(a, b) + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static int gcd(int a, int b) {
        int rest = a % b;
        a = b;
        result = b;
        if(rest == 0) {
            return result;
        }
        gcd(a, rest);
        return result;
    }
}