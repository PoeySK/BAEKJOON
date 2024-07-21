package BruteForcing;

import java.io.*;

public class NQueen {
    static int N;
    static int[] queen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        br.close();

        /* 작동 */
        queen = new int[N]; // row -> col 배열
        int result = 0;
        if (N != 2 && N != 3) { // N == 2 || N == 3, 불가능
            result = run(0);
        }

        /* 출력 */
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static int run(int row) { // k: queen의 개수
        if (row == N) {
            return 1;
        }

        int count = 0; // 해당 row에서 놓을 수 있는 queen의 개수
        for (int i = 0; i < N; i++) {
            queen[row] = i;
            if (isCheck(row)) {
                count += run(row + 1); // 퀸을 놓을 수 있으면 다음 row 탐색
            }
        }

        return count;
    }

    private static boolean isCheck(int r) {
        for (int i = 0; i < r; i++) {
            if (queen[r] == queen[i]) { // 같은 column
                return false;
            }
            int cha = r - i; // row와 다음 row 차이
            if (cha == Math.abs(queen[r] - queen[i])) { // 대각
                return false;
            }
        }
        return true;
    }

}
