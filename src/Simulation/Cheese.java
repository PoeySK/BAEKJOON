package Simulation;

import java.io.*;
import java.util.*;

public class Cheese {
    static int N, M, count;
    static int[][] field;
    static ArrayList<Point> save = new ArrayList<>();
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == 1) {
                    save.add(new Point(i, j));
                }
            }
        }
        br.close();

        /* 작동 */
        findAir(0, 0);
        run();

        /* 출력 */
        bw.write(count + "\n");
        bw.close();
    }

    private static void run() {
        ArrayList<Integer> del;
        while (!save.isEmpty()) {
            del = new ArrayList<>();
            findCheese(del);
            removeCheese(del);
            count++;
        }
    }

    private static void findCheese(ArrayList<Integer> del) {
        for (int i = 0; i < save.size(); i++) {
            Point now = save.get(i);
            if (attachAir(now)) {
                del.add(i);
            }
        }
    }

    private static void removeCheese(ArrayList<Integer> del) {
        for (int i = del.size() - 1; i >= 0; i--) {
            int idx = del.get(i);
            Point now = save.get(idx);
            findAir(now.y, now.x);
            save.remove(idx);
        }
    }

    private static boolean attachAir(Point now) {
        int at = 0;
        for (int i = 0; i < 4; i++) {
            int ny = now.y + dy[i];
            int nx = now.x + dx[i];

            if (field[ny][nx] == 8) at++;
        }

        return at >= 2;
    }

    private static void findAir(int y, int x) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(y, x));
        field[y][x] = 8;
        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (isRange(ny, nx) && field[ny][nx] == 0) {
                    field[ny][nx] = 8;
                    q.offer(new Point(ny, nx));
                }
            }
        }
    }

    private static boolean isRange(int ny, int nx) {
        return 0 <= ny && ny < N && 0 <= nx && nx < M;
    }

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
