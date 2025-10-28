package Simulation;

import java.io.*;
import java.util.*;

public class GasPipe {
    final static int[] dy = {1, 0, -1, 0};
    final static int[] dx = {0, 1, 0, -1};
    final static char[] pipe = {'|', '-', '+', '1', '2', '3', '4'};
    static int R, C, ry, rx;
    static char rp;
    static char[][] field;
    static boolean[][] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        field = new char[R][C];
        isVisit = new boolean[R][C];
        int sy = 0, sx = 0;
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                field[i][j] = input.charAt(j);
                if (field[i][j] == 'M') {
                    sy = i;
                    sx = j;
                    isVisit[i][j] = true;
                }
            }
        }
        br.close();

        for (int i = 0; i < 4; i++) {
            int ny = sy + dy[i];
            int nx = sx + dx[i];
            if (isOut(ny, nx) || field[ny][nx] == '.' || field[ny][nx] == 'Z') continue;

            run(ny, nx, i);
        }

        bw.write(String.format("%d %d %s\n", ry, rx, rp));
        bw.close();
    }

    private static void run(int y, int x, int d) {
        if (isOut(y, x) || isVisit[y][x]) return;
        isVisit[y][x] = true;
        switch (field[y][x]) {
            case '|':
                for (int i = 0; i < 4; i += 2) {
                    int ny = y + dy[i];
                    run(ny, x, i);
                }
                break;
            case '-':
                for (int i = 1; i < 4; i += 2) {
                    int nx = x + dx[i];
                    run(y, nx, i);
                }
                break;
            case '+':
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    run(ny, nx, i);
                }
                break;
            case '1':
                for (int i = 0; i < 2; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    run(ny, nx, i);
                }
                break;
            case '2':
                for (int i = 1; i < 3; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    run(ny, nx, i);
                }
                break;
            case '3':
                for (int i = 2; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    run(ny, nx, i);
                }
                break;
            case '4':
                for (int i = 0; i < 4; i += 3) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    run(ny, nx, i);
                }
                break;
            case '.':
                ry = y + 1;
                rx = x + 1;
                isPipe(y, x, d);
        }
    }

    private static void isPipe(int y, int x, int d) {
        int vd = (d + 2) % 4;
        int bit = 1 << vd;
        for (int i = 0; i < 4; i++) {
            if (vd == i) continue;
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (isOut(ny, nx) || field[ny][nx] == '.') continue;
            boolean check = false;
            switch (field[ny][nx]) {
                case '|':
                    if (i % 2 == 0) {
                        check = true;
                    }
                    break;
                case '-':
                    if (i % 2 == 1) {
                        check = true;
                    }
                    break;
                case '+':
                    check = true;
                    break;
                case '1':
                    if (i > 1) {
                        check = true;
                    }
                    break;
                case '2':
                    if (i < 1 || i > 2) {
                        check = true;
                    }
                    break;
                case '3':
                    if (i < 2) {
                        check = true;
                    }
                    break;
                case '4':
                    if (0 < i && i < 3) {
                        check = true;
                    }
                    break;
            }
            if (check) {
                bit += 1 << i;
            }
        }
        rp = selPipe(bit);
    }

    private static char selPipe(int bit) {
        // 5 10 15 3 6 12 9
        switch (bit) {
            case 5:
                return pipe[0];
            case 10:
                return pipe[1];
            case 15:
                return pipe[2];
            case 3:
                return pipe[3];
            case 6:
                return pipe[4];
            case 12:
                return pipe[5];
            case 9:
                return pipe[6];
        }
        return 0;
    }

    private static boolean isOut(int y, int x) {
        return 0 > y || R <= y || 0 > x || C <= x;
    }
}
