package Simulation;

import java.io.*;
import java.util.*;

public class LadderManipulation {
    static BufferedWriter bw;
    static int N, M, H;
    static boolean[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (M == 0) { // 일자
            bw.write("0");
            bw.close();
            return;
        }
        H = Integer.parseInt(st.nextToken());
        field = new boolean[H][N - 1];
        int count = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            field[a][b] = true;
            if (field[a][b]) count++;
        }

        br.close();

        /* 작동 */
        if (M == 1) { // 짝수 맞추기
            bw.write(count % 2 == 0 ? 0 + "\n" : 1 + "\n");
            bw.close();
            return;
        }

        if (isCheck()) {
            bw.write(0 + "\n");
            bw.close();
            return;
        }

        for (int i = 1; i <= 3; i++) {
            run(0, i, 0);
        }

        /* 출력 */
        bw.write(-1 + "\n"); // 실패
        bw.close();
    }

    private static void run(int cnt, int max, int y) throws IOException {
        if (cnt == max) {
            if (isCheck()) {
                bw.write(cnt + "\n");
                bw.close();
                System.exit(0);
            }
            return;
        }

        if (cnt > max) {
            return;
        }

        for (int i = y; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (!field[i][j]) {
                    if ((j > 0 && field[i][j - 1]) || (j < N - 2 && field[i][j + 1])) continue;

                    field[i][j] = true;
                    run(cnt + 1, max, i);
                    field[i][j] = false;
                }
            }
        }
    }

    private static boolean isCheck() {
        for (int i = 0; i < N; i++) {
            int start = i;
            for (int lv = 0; lv < H; lv++) {
                boolean left = start > 0 && field[lv][start - 1];
                boolean right = start < N - 1 && field[lv][start];

                if (left) {
                    start--;
                } else if (right) {
                    start++;
                }
            }
            if (start != i) return false;
        }
        return true;
    }
}
