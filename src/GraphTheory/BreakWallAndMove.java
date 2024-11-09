package GraphTheory;

import java.io.*;
import java.util.*;

public class BreakWallAndMove {
    static int N, M, number;
    static int[][] field;
    static ArrayList<Integer> save;
    static StringBuilder sb = new StringBuilder();
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(input[j]);
                if (num == 1) field[i][j] = -1;
                else field[i][j] = 0;
            }
        }
        br.close();

        /* 작동 */
        save = new ArrayList<>();
        mark();
        run();

        /* 출력 */
        bw.write(sb.toString());
        bw.close();
    }


    private static void run() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int cnt = 0;
                if (field[i][j] == -1) {
                    Set<Integer> set = new HashSet<>();
                    cnt = 1;
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (isRange(ny, nx) && !set.contains(field[ny][nx]) && field[ny][nx] > 0) {
                            set.add(field[ny][nx]);
                            cnt += save.get(field[ny][nx]);
                        }
                    }
                }
                sb.append(cnt % 10);
            }
            sb.append("\n");
        }
    }

    private static void mark() {
        save.add(0); // dummy data
        number = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 0) {
                    bfs(i, j);
                    number++;
                }
            }
        }
    }

    private static void bfs(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(y, x));
        field[y][x] = number;
        int cnt = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && field[ny][nx] == 0) {
                    cnt++;
                    field[ny][nx] = number;
                    q.offer(new Node(ny, nx));
                }
            }
        }
        save.add(cnt);
    }

    private static boolean isRange(int ny, int nx) {
        return 0 <= ny && ny < N && 0 <= nx && nx < M;
    }

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
