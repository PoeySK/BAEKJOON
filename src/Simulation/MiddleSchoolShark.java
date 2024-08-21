package Simulation;

import java.io.*;
import java.util.*;

public class MiddleSchoolShark {
    static int N, M, result;
    static int[][] field;
    static boolean[][] isMarking;
    static PriorityQueue<Save> save = new PriorityQueue<>();
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        while (true) {
            isMarking = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!isMarking[i][j] && 0 < field[i][j] && field[i][j] <= M) { // 검은색 무지개색 확인 안함
                        mark(i, j, field[i][j]);
                    }
                }
            }

            if (save.isEmpty()) { // 오토 플레이 끝
                break;
            }
            Save target = save.poll(); // 우선순위가 가장 높은 기준 블록
            remove(target.y, target.x, field[target.y][target.x]);
            result += (target.count * target.count);
            save.clear(); // 오토 플레이 다시 시작
            gravity();
            spin();
            gravity();
        }

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void mark(int y, int x, int number) { // 블록 그룹 찾기
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(y, x));
        isMarking[y][x] = true;
        boolean[][] isVisit = new boolean[N][N];
        isVisit[y][x] = true;
        int count = 1;
        int rainbowCnt = 0;
        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && !isVisit[ny][nx]) {
                    isVisit[ny][nx] = true;
                    if (field[ny][nx] == number) {
                        isMarking[ny][nx] = true;
                        field[ny][nx] = number;
                        q.offer(new Point(ny, nx));
                    } else if (field[ny][nx] == 0) {
                        q.offer(new Point(ny, nx));
                        rainbowCnt++;
                    } else { // 다른 색상 or 검은 색
                        continue;
                    }
                    count++;
                }
            }
        }
        if (count < 2) { // 그룹 조건: count 2이상
            return;
        }
        save.add(new Save(y, x, count, rainbowCnt));
    }

    private static boolean isRange(int ny, int nx) { // 탐색 범위
        return 0 <= ny && ny < N && 0 <= nx && nx < N;
    }

    private static void remove(int y, int x, int number) { // 선택된 범위 제거
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] isVisit = new boolean[N][N];
        q.offer(new Point(y, x));
        isVisit[y][x] = true;
        field[y][x] = 8;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && !isVisit[ny][nx] && field[ny][nx] > -1) {
                    if (field[ny][nx] == 0 || field[ny][nx] == number) {
                        q.offer(new Point(ny, nx));
                        isVisit[ny][nx] = true;
                        field[ny][nx] = 8; // 제거할 곳 마킹
                    }
                }
            }
        }


    }

    private static void gravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i > 0; i--) {
                if (field[i][j] != 8) {
                    continue;
                }
                int idx = i;
                while (idx >= 0) {
                    if (field[idx][j] == -1) {
                        i = idx + 1; // i-- 연산 생각
                        break;
                    }
                    if (field[idx][j] != 8) {
                        field[i][j] = field[idx][j];
                        field[idx][j] = 8;
                        break;
                    }
                    idx--;
                }
            }
        }
    }

    private static void spin() {
        int[][] spinField = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                spinField[i][j] = field[j][N - 1 - i];
            }
        }

        field = spinField;
    }

    static class Save implements Comparable<Save> {
        int y, x, count, rainbow;

        Save(int y, int x, int count, int rainbow) {
            this.y = y;
            this.x = x;
            this.count = count;
            this.rainbow = rainbow;
        }

        @Override
        public int compareTo(Save s) { // 우선순위
            if (s.count == this.count) {
                if (s.rainbow == this.rainbow) {
                    if (s.y == this.y) {
                        return s.x - this.x;
                    }
                    return s.y - this.y;
                }
                return s.rainbow - this.rainbow;
            }
            return s.count - this.count;
        }
    }

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
