package Simulation;

import java.io.*;
import java.util.*;

public class DragonCurve {
    static int N, result;
    static boolean[][] field = new boolean[101][101];
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            /* 작동 */
            field[y][x] = true;
            addAll(y, x, d, g);
        }
        br.close();

        /* 출력 */
        isCheck();
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(ArrayList<Integer> ds, int y, int x) { // 이동
        for (int d : ds) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            field[ny][nx] = true;
            y = ny;
            x = nx;
        }
    }

    private static void addAll(int y, int x, int d, int g) { // 세대별 이동 방향 저장
        ArrayList<Integer> ds = new ArrayList<>();
        ds.add(d);  // 기본 0세대
        for (int i = 1; i <= g; i++) {
            int last = ds.size();
            for (int j = last - 1; j >= 0; j--) {
                ds.add((ds.get(j) + 1) % 4);
            }
        }

        run(ds, y, x);
    }

    private static void isCheck() { // 모든 좌표 사각형 검사
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (field[i][j] && field[i][j + 1] && field[i + 1][j] && field[i + 1][j + 1]) {
                    result++;
                }
            }
        }
    }
}
