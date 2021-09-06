package DynamicProgramming;

import java.io.*;

public class OneTwoThreePlus {
    static int[] n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        n = new int[12]; // 조건이 n은 양수이며 11보다 작다.

        for (int i = 0; i < T; i++) {
            int value = Integer.parseInt(br.readLine());
            bw.write(dp(value) + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    static int dp(int x) { // 1,2,3 은 규칙없이 생성되어 값을 지정해준다.
        if (x == 1) {
            return n[x] = 1;
        }
        if (x == 2) {
            return n[x] = 2;
        }
        if (x == 3){
            return n[x] = 4;
        }
        if (n[x] != 0) { // 만약 이미 구한 값이라면 그 값을 바로 반환한다.
            return n[x];
        }
        // 1로 시작할 경우 나머지 경우의 수는 이미 앞의 숫자로 구할 수 있고, 2와 3도 마찬가지로 앞 수에서 값을 찾을 수 있다.
        return n[x] = dp(x - 1) + dp(x - 2) + dp(x - 3);
    }
}