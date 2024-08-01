package BruteForcing;

import java.io.*;
import java.util.*;

public class Sudoku {
    static int[][] field = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* 입력 */
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        run(0, 0);
    }

    private static void run(int r, int c) throws IOException {
        if (c == 9) {
            run(r + 1, 0);
            return;
        }

        if (r == 9) { // 여기까지 온거면 모든 경우를 확인
            print();
            System.exit(0); // 이후 경우를 확인할 필요없이 프로세스 종료
        }

        if (field[r][c] == 0) {
            for (int i = 1; i <= 9; i++) { // 1~9 하나씩 넣어보며 확인
                if (find(r, c, i)) {
                    field[r][c] = i;
                    run(r, c + 1);
                }
            }
            field[r][c] = 0; // 되돌리기
            return;
        }
        run(r, c + 1); // 해당 row가 다 채워져있는 경우
    }

    private static boolean find(int y, int x, int value) { // 3단 체크
        return isRow(y, value) && isCol(x, value) && isSquare(y, x, value);
    }

    public static boolean isRow(int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (field[y][i] == value) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCol(int x, int value) {
        for (int i = 0; i < 9; i++) {
            if (field[i][x] == value) {
                return false;
            }
        }

        return true;
    }

    public static boolean isSquare(int y, int x, int value) { // 0, 3, 6 -> 구역
        y = y / 3 * 3;
        x = x / 3 * 3; // square 범위 지정
        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                if (field[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(field[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
