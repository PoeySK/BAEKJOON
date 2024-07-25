package DFSandBFS;

import java.io.*;
import java.util.*;

public class BreakWall {
    static int N, M, result;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] wall;
    static boolean[][] isVisitDestroy; // 벽을 부신 세계
    static boolean[][] isVisitUnDestroy; // 벽을 아직 안부신 세계

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        wall = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                wall[i][j] = Integer.parseInt(input[j]);
            }
        }
        br.close();

        /* 작동 */
        isVisitDestroy = new boolean[N][M];
        isVisitUnDestroy = new boolean[N][M];
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static void run() {
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1, false));

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.y == N - 1 && now.x == M - 1) {
                result = now.time;
                return;
            }
            if(now.destroy) { // 벽을 부순 상황
                if(isVisitDestroy[now.y][now.x]) {
                    continue;
                }
                isVisitDestroy[now.y][now.x] = true;
            } else {
                if(isVisitUnDestroy[now.y][now.x]) {
                    continue;
                }
                isVisitUnDestroy[now.y][now.x] = true;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                    if (wall[ny][nx] == 0) {
                        q.add(new Node(ny, nx, now.time + 1, now.destroy));
                    } else { // wall[ny][nx] == 1
                        if (!now.destroy) {
                            q.add(new Node(ny, nx, now.time + 1, true));

                        }
                    }
                }
            }
        }

        result = -1; // (N, M)에 못닿을 시
    }

    static class Node {
        int y;
        int x;
        int time;
        boolean destroy; // false -> 아직 사용 안함 , true -> 부시기 사용

        public Node(int y, int x, int time, boolean destroy) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.destroy = destroy;
        }
    }
}
