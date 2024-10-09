package Simulation;

import java.io.*;
import java.util.*;

public class FineDustBye {
    static int R, C, T, result;
    static int[][] field;
    static int airCleaner;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        field = new int[R][C];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == -1) {
                    airCleaner = i;
                }
            }
        }

        br.close();
        /* 작동 */
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < T; i++) {
            spread();
            upWind(airCleaner - 1, 0);
            downWind(airCleaner, 0);
        }

        sum();
    }

    private static void spread() {
        int[][] temp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (field[i][j] >= 5) {
                    dustMove(i, j, field[i][j], temp);
                }
            }
        }

        // 확장 마무리
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                field[i][j] += temp[i][j];
            }
        }
    }

    private static void dustMove(int y, int x, int dust, int[][] temp) { // 개인 확장
        int divDust = dust / 5;
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (isRange(ny, nx) && field[ny][nx] > -1) {
                temp[ny][nx] += divDust;
                cnt++;
            }
        }

        field[y][x] -= divDust * cnt;
    }

    private static boolean isRange(int ny, int nx) {
        return 0 <= ny && ny < R && 0 <= nx && nx < C;
    }

    private static void upWind(int y, int x) {
        int ny = y;
        int nx = x;
        // 하향
        while (ny > 0) {
            if (field[ny][nx] == -1) {
                field[ny - 1][nx] = 0;
            } else {
                field[ny][nx] = field[ny - 1][nx];
            }
            ny--;
        }

        // 좌향
        while (nx < C - 1) {
            field[ny][nx] = field[ny][nx + 1];
            nx++;
        }
        // 상향
        while (ny < y) {
            field[ny][nx] = field[ny + 1][nx];
            ny++;
        }

        // 우향
        while (nx > 0) {
            if (field[ny][nx - 1] == -1) {
                field[ny][nx] = 0;
                return;
            }
            field[ny][nx] = field[ny][nx - 1];
            nx--;
        }
    }

    private static void downWind(int y, int x) {
        int ny = y;
        int nx = x;

        // 상향
        while (ny < R - 1) {
            if (field[ny][nx] == -1) {
                field[ny + 1][nx] = 0;
            } else {
                field[ny][nx] = field[ny + 1][nx];
            }
            ny++;
        }
        // 좌향
        while (nx < C - 1) {
            field[ny][nx] = field[ny][nx + 1];
            nx++;
        }

        // 하향
        while (ny > y) {
            field[ny][nx] = field[ny - 1][nx];
            ny--;
        }

        // 우향
        while (nx > 0) {
            if (field[ny][nx - 1] == -1) {
                field[ny][nx] = 0;
                return;
            }
            field[ny][nx] = field[ny][nx - 1];
            nx--;
        }
    }

    private static void sum() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (field[i][j] > 0) {
                    result += field[i][j];
                }
            }
        }
    }
}

