package GraphTheory;

import java.io.*;
import java.util.*;

public class MazeRunner {
    static int N, M;
    static int count = 0;
    static int[][] maze;
    static int[] mx = {0, 0, 1, -1}; // x의 움직임, 상 하 좌 우
    static int[] my = {-1, 1, 0, 0}; // y의 움직임, 상 하 좌 우

    static class Coordinate {
        int x;
        int y;
        int foot;

        public Coordinate(int x, int y, int foot) {
            this.x = x;
            this.y = y;
            this.foot = foot;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);
        maze = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] xy = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(xy[j]);
            }
        }

        find();


        bw.write(count + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static void find() { // bfs 너비우선탐색
        Queue<Coordinate> q = new LinkedList<>();
        count = 1;

        if (maze[0][0] == 1) { // 시작지점이 1인지 파악
            q.offer(new Coordinate(0, 0, count));
            maze[0][0] = 8; // 방문한 곳은 8로 표기 (작성자 마음)
        } else {
            count = -1;
            return;
        }

        while (!q.isEmpty()) {
            Coordinate coo = q.poll(); // 현재 좌표와 현재 칸 수
            count = coo.foot;

            if (coo.x == M - 1 && coo.y == N - 1) { // 도착지점에 오면 종료 (차선의 길 방지, 최선의 길 찾기 문제임)
                break;
            }

            for (int i = 0; i < 4; i++) { // 4방향 이동
                int nx = coo.x + mx[i];
                int ny = coo.y + my[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N) { // 좌표의 크기가 0보다 크거나 같고 지정 값보다 작아야 함
                    if (maze[ny][nx] == 1) {
                        maze[ny][nx] = 8;
                        q.offer(new Coordinate(nx, ny, count + 1));
                    }
                }
            }
            
        }

    }
}