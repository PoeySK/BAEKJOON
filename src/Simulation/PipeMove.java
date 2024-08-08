package Simulation;

import java.io.*;
import java.util.*;

public class PipeMove {
    static int N, result;
    static int[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        run(0, 0, 0, 1); // [0, 0] [0, 1] 시작

        /* 작동 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int y1, int x1, int y2, int x2) {
        if (y2 == N - 1 && x2 == N - 1) { // 위치에 도착한 경우
            result++;
            return;
        }

        if (y2 == N || x2 == N || field[y2][x2] == 1) { // 범위 벗어난 경우
            return;
        }


        if (y1 - y2 == 0) { // 가로 파이프
            if (x2 + 1 < N && field[y1][x2 + 1] == 0) { // 가로 이동
                run(y2, x2, y2, x2 + 1);
            }
            if (y2 + 1 < N && x2 + 1 < N && field[y2 + 1][x2 + 1] == 0 && field[y2][x2 + 1] == 0 && field[y2 + 1][x2] == 0) { // 대각 이동
                run(y2, x2, y2 + 1, x2 + 1);
            }
        } else if (x1 - x2 == 0) { // 세로 파이프
            if (y2 + 1 < N && field[y2 + 1][x1] == 0) { // 세로 이동
                run(y2, x2, y2 + 1, x2);
            }
            if (y2 + 1 < N && x2 + 1 < N && field[y2 + 1][x2 + 1] == 0 && field[y2][x2 + 1] == 0 && field[y2 + 1][x2] == 0) { // 대각 이동
                run(y2, x2, y2 + 1, x2 + 1);
            }
        } else { // 대각 파이프
            if (x2 + 1 < N && field[y2][x2 + 1] == 0) { // 가로 이동
                run(y2, x2, y2, x2 + 1);
            }
            if (y2 + 1 < N && field[y2 + 1][x2] == 0) { // 세로 이동
                run(y2, x2, y2 + 1, x2);
            }
            if (x2 + 1 < N && y2 + 1 < N && field[y2 + 1][x2 + 1] == 0 && field[y2][x2 + 1] == 0 && field[y2 + 1][x2] == 0) { // 대각 이동
                run(y2, x2, y2 + 1, x2 + 1);
            }

        }
    }
}
