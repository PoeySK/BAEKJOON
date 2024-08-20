package DynamicProgramming;

import java.io.*;
import java.util.*;

public class GreenZelda {
    static int N, result;
    static int[][] field, sol;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        StringTokenizer st;
        int tc = 1;
        while ((input = br.readLine()) != null) {
            /* 입력 */
            N = Integer.parseInt(input);
            if (N == 0) { // 종료 시점
                break;
            }
            field = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            /* 작동 */
            result = Integer.MAX_VALUE;
            sol = new int[N][N]; // solution table
            for (int i = 0; i < N; i++) {
                Arrays.fill(sol[i], Integer.MAX_VALUE);
            }
            sol[0][0] = field[0][0];
            run();


            /* 출력 */
            bw.write("Problem " + tc++ + ": " + result + "\n");
        }
        br.close();
        bw.close();
    }

    private static void run() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (0 <= ny && ny < N && 0 <= nx && nx < N) {
                    if (sol[ny][nx] > sol[now.y][now.x] + field[ny][nx]) {
                        q.offer(new Point(ny, nx));
                        sol[ny][nx] = sol[now.y][now.x] + field[ny][nx];
                    }
                }
            }
        }

        result = sol[N - 1][N - 1];
    }

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}

