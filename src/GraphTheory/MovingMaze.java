package GraphTheory;

import java.io.*;
import java.util.*;

public class MovingMaze {
    static char[][][] field = new char[9][8][8];
    static boolean[][][] isVisit = new boolean[9][8][8];
    static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        for (int i = 0; i < 8; i++) {
            input = br.readLine();
            for (int j = 0; j < 8; j++) {
                field[0][i][j] = input.charAt(j);
            }
        }
        br.close();

        move();
        run(7, 0, 0);

        bw.write(0 + "\n");
        bw.close();
    }

    private static void run(int y, int x, int lv) {
        if (y == 0 && x == 7) {
            System.out.println(1);
            System.exit(0);
        }
        isVisit[lv][y][x] = true;
        if (field[lv][y][x] == '#') return;
        lv = Math.min(lv + 1, 8);
        for (int i = 0; i < 9; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (isRange(ny, nx) && !isVisit[lv][ny][nx] && field[lv - 1][ny][nx] == '.') {
                run(ny, nx, lv);
            }
        }
    }

    private static void move() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 8; j++) {
                field[i][j] = field[i - 1][j - 1].clone();
            }
            field[i][0] = new char[8];
            Arrays.fill(field[i][0], '.');
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < 8 && 0 <= x && x < 8;
    }
}
