package DynamicProgramming;

import java.io.*;

public class LCS {
    static int xlen, ylen;
    static int[][] s; // solution table
    static String[] X, Y; // 첫 번째 입력 배열, 두 번째 입력 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        String[] x = br.readLine().split("");
        xlen = x.length;
        X = new String[xlen];
        X = x;

        String[] y = br.readLine().split("");
        ylen = y.length;
        Y = new String[ylen];
        Y = y;

        s = new int[xlen + 1][ylen + 1];
        check();

        bw.write(s[xlen][ylen] + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

    public static void check() {
        /*
         * X의 배열을 기준으로 0번째 인덱스부터 Y의 배열을 검사한다.
         * ex) X = ACAYKP / Y = CAPCAK
         * X의 첫 번째 알파벳인 A부터 검사하면
         * Y에서 2번째가 A로 똑같기 때문에 solution table 에 값을 저장한다.
         */
        for (int i = 0; i < xlen + 1; i++) {
            for (int j = 0; j < ylen + 1; j++) {
                if (i == 0 || j == 0) { // 초기 0으로 지정
                    s[i][j] = 0;
                } else {
                    if (X[i - 1].equals(Y[j - 1])) {
                        /*
                         * 한 알파벳이 중복으로 걸릴 수 있기 때문에
                         * i - 1, j - 1 인덱스의 값을 가져옴.
                         */
                        s[i][j] = s[i - 1][j - 1] + 1;
                    } else {
                        /*
                         * X1, ... , Xn-1과 Y1, ... , Yn
                         * X1, ... , Xn과 Y1, ... , Yn-1
                         * 위 두 가지의 LCS를 비교해서 더 큰 값을 가진다.
                         */
                        int max = Math.max(s[i - 1][j], s[i][j - 1]);
                        s[i][j] = max;
                    }
                }
            }
        }
    }
}