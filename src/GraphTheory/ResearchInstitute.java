package GraphTheory;

import java.io.*;
import java.util.*;

public class ResearchInstitute {
    static int N, M;
    static int[] moveX = {-1, 1, 0, 0}; // 상 하 좌 우 x의 움직임
    static int[] moveY = {0, 0, -1, 1}; // 상 하 좌 우 y의 움직임
    static int[][] r;
    static ArrayList<Integer> sol;

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        r = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                r[i][j] = Integer.parseInt(row[j]);
            }
        }
        sol = new ArrayList<>(); // solution table

        Wall(0);
        sol.sort(Collections.reverseOrder()); // 내림차순 정렬

        bw.write(sol.get(0) + "\n"); // index 0이 최댓값
        bw.flush();
        br.close();
        bw.close();
    }

    public static void Wall(int count) { // 벽을 3개 지으면 감염 시작
        if (count >= 3) {
            Infection();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (r[i][j] == 0) {
                    r[i][j] = 1;
                    Wall(count + 1);
                    r[i][j] = 0; // 원상복구
                }
            }
        }
    }

    public static void Infection() { // 감염
        int[][] copy_r = new int[N][M];
        for (int i = 0; i < copy_r.length; i++) {
            System.arraycopy(r[i], 0, copy_r[i], 0, M);
        }

        Queue<Coordinate> q = new LinkedList<>();

        // find to virus start point
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (r[i][j] == 2) {
                    q.offer(new Coordinate(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Coordinate coo = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = coo.x + moveX[i];
                int ny = coo.y + moveY[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (copy_r[nx][ny] == 0) {
                        copy_r[nx][ny] = 2;
                        q.offer(new Coordinate(nx, ny));
                    }
                }
            }
        }
        sol.add(findSaveArea(copy_r));
    }

    public static int findSaveArea(int[][] array) { // 안전한 지역 찾기
        int save = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (array[i][j] == 0) {
                    save++;
                }
            }
        }
        return save;
    }
}