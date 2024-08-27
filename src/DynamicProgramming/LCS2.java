package DynamicProgramming;

import java.io.*;
import java.util.*;

public class LCS2 {
    static String[] one, two;
    static int[][] sol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        one = br.readLine().split("");
        two = br.readLine().split("");
        br.close();

        /* 작동 */
        sol = new int[one.length + 1][two.length + 1];
        run();

        /* 출력 */
        bw.write(sol[one.length][two.length] + "\n" + lcs());
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < one.length + 1; i++) {
            for (int j = 0; j < two.length + 1; j++) {
                if (i == 0) {
                    sol[i][j] = 0;
                } else {
                    if (j == 0) {
                        sol[i][j] = 0;
                    } else {
                        if (one[i - 1].equals(two[j - 1])) { // 겹치면 이전 상황에서 +1
                            sol[i][j] = sol[i - 1][j - 1] + 1;
                        } else {
                            sol[i][j] = Math.max(sol[i - 1][j], sol[i][j - 1]);
                        }
                    }
                }
            }
        }
    }

    private static String lcs() {
        StringBuilder sb = new StringBuilder();
        int i = one.length;
        int j = two.length;
        while (i > 0 && j > 0) {
            if (sol[i][j] == sol[i][j - 1]) { // 공통 부분 찾기
                j--;
            } else if (sol[i][j] == sol[i - 1][j]) { // 공통 부분 찾기
                i--;
            } else if (sol[i - 1][j] == sol[i][j - 1]) { // 공통 부분
                i--;
                j--;
                sb.append(one[i]);
            }
        }

        return String.valueOf(sb.reverse());
    }
}
