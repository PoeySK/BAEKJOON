package GraphTheory;

import java.io.*;
import java.util.*;

public class Alphabet {
    static int R, C, result = 1;
    static char[][] field;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        field = new char[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for (int j = 0; j < C; j++) {
                field[i][j] = input.charAt(j);
            }
        }
        br.close();

        /* 작동 */
        boolean[] isCheck = new boolean[26];
        isCheck[field[0][0] - 65] = true;
        run(0, 0, isCheck, 1);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int y, int x, boolean[] isCheck, int count) {
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (isRange(ny, nx)) {
                int ascii = field[ny][nx];
                if (!isCheck[ascii - 65]) { // 65 => A의 아스키 코드
                    isCheck[ascii - 65] = true;
                    run(ny, nx, isCheck, count + 1);
                    isCheck[ascii - 65] = false;
                } else {
                    result = Math.max(result, count);
                }
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
}
