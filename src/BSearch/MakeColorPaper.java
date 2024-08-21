package BSearch;

import java.util.*;
import java.io.*;

public class MakeColorPaper {
    static int N, white, blue;
    static int[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        field = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        run(0, 0, N);

        /* 출력 */
        bw.write(white + "\n" + blue);
        bw.close();
    }

    private static void run(int r, int c, int size) {
        /**
         * 1. 주어진 공간이 모두 같은 색으로 이루어졌는지 확인
         * 2. 같은 색으로 이루어져 있지 않으면 4분할
         * 3. 같은 색으로 이루어져 있으면 분할하지 않음
         **/

        // 1번 과정
        int sum = 0;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                sum += field[i][j];
            }
        }

        // 2번 과정
        if (sum == 0) {
            white++;
        } else if (sum == size * size) {
            blue++;
        } else {
            // 3번 과정
            int half = size / 2;
            run(r, c, half);
            run(r, c + half, half);
            run(r + half, c, half);
            run(r + half, c + half, half);
        }

    }
}
