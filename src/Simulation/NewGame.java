package Simulation;

import java.io.*;
import java.util.*;

public class NewGame {
    static int N, K;
    static int[][] field;
    static ArrayList<Info> info;
    static ArrayDeque<Integer>[][] fieldStack;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[N][N];
        fieldStack = new ArrayDeque[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                fieldStack[i][j] = new ArrayDeque<>();
            }
        }

        info = new ArrayList<>();
        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            info.add(new Info(y - 1, x - 1, m - 1));
            fieldStack[y - 1][x - 1].offer(i);
        }
        br.close();

        /* 작동 */
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int count = 0;
        while (count <= 1000) {
            for (int i = 0; i < K; i++) {
                int y = info.get(i).y;
                int x = info.get(i).x;
                int number = fieldStack[y][x].peek();
                if (number != i + 1) continue;

                int move = info.get(i).m;
                int ny = y + dy[move];
                int nx = x + dx[move];

                if (isBlue(ny, nx)) { // 범위 외 포함
                    info.get(i).m = move % 2 == 0 ? move + 1 : move - 1;
                    ny = y + dy[info.get(i).m];
                    nx = x + dx[info.get(i).m];
                    if (!isBlue(ny, nx)) {
                        if (field[ny][nx] == 1) moveRed(y, x, ny, nx);
                        else moveWhite(y, x, ny, nx);
                    }
                } else {
                    if (field[ny][nx] == 1) moveRed(y, x, ny, nx);
                    else moveWhite(y, x, ny, nx);
                }
            }

            count++;
            if (isCheck()) break;
        }

        return count == 1001 ? -1 : count;
    }

    private static void moveRed(int y, int x, int ny, int nx) {
        while (!fieldStack[y][x].isEmpty()) {
            int num = fieldStack[y][x].peekLast();
            info.get(num - 1).y = ny;
            info.get(num - 1).x = nx;
            fieldStack[ny][nx].offer(fieldStack[y][x].pollLast());
        }
    }

    private static void moveWhite(int y, int x, int ny, int nx) {
        while (!fieldStack[y][x].isEmpty()) {
            int num = fieldStack[y][x].peek();
            info.get(num - 1).y = ny;
            info.get(num - 1).x = nx;
            fieldStack[ny][nx].offer(fieldStack[y][x].poll());
        }
    }

    private static boolean isBlue(int ny, int nx) {
        return 0 > ny || ny >= N || 0 > nx || nx >= N || field[ny][nx] == 2;
    }

    private static boolean isCheck() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (fieldStack[i][j].size() >= 4) return true;
            }
        }
        return false;
    }

    static class Info {
        int y, x, m;

        Info(int y, int x, int m) {
            this.y = y;
            this.x = x;
            this.m = m;
        }
    }
}
