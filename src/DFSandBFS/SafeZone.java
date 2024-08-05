package DFSandBFS;

import java.io.*;
import java.util.*;

public class SafeZone {
    static int N, result;
    static int max; // 최대 높이 지역
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int[][] field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, field[i][j]);
            }
        }

        /* 작동 */
        run(field);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int[][] field) { // 높이만큼 비 채우기
        for (int i = 0; i < max; i++) {
            full(i, field);
        }
    }

    private static void full(int h, int[][] field) { // 비 채운 후 안전 지역 확인
        boolean[][] rain = new boolean[N][N]; // 채운 영역
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] <= h) { // 높이보다 낮으면 비 채우기
                    rain[i][j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!rain[i][j]) {
                    bfs(rain, i, j);
                    count++;
                }
            }
        }
        result = Math.max(result, count);
    }

    private static void bfs(boolean[][] rain, int r, int c) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(r, c));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (0 <= ny && ny < N && 0 <= nx && nx < N && !rain[ny][nx]) {
                    rain[ny][nx] = true;
                    q.offer(new Node(ny, nx));
                }
            }
        }
    }

    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
