package DFSandBFS;
import java.io.*;
import java.util.*;

public class CountOfIsland {
    static int N, M, result;
    static int[][] field;
    static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            /* 입력 */
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            /* 종료 */
            if (N == 0) {
                break;
            }
            field = new int[M][N];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            /* 작동 */
            result = 0;
            run();

            /* 출력 */
            bw.write(result + "\n");
        }
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] == 1) {
                    find(i, j);
                    result++;
                }
            }
        }

    }

    private static void find(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(y, x));
        field[y][x] = 2; // 방문
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 9; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (0 <= ny && ny < M && 0 <= nx && nx < N) {
                    if (field[ny][nx] == 1) {
                        field[ny][nx] = 2;
                        q.offer(new Node(ny, nx));
                    }
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
