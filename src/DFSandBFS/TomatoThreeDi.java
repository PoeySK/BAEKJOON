package DFSandBFS;
import java.io.*;
import java.util.*;

public class TomatoThreeDi {
    static int M, N, H, result;
    static int[][][] field;
    static int[] dd = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 0, -1, 1};

    static class Node {
        int d;
        int y;
        int x;
        int time;

        public Node(int d, int y, int x, int time) {
            this.d = d;
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        field = new int[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    field[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        br.close();
        /* 작동 */
        run();

        /* 출력 */
        if (isCheck()) { // 토마토 모두 익히기 성공
            bw.write(result + "\n");
        } else {
            bw.write("-1\n");
        }
        bw.close();
    }

    private static void run() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] isVisit = new boolean[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (field[i][j][k] == 1) {
                        q.add(new Node(i, j, k, 0));
                        isVisit[i][j][k] = true;
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 6; i++) {
                int nd = now.d + dd[i];
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (0 <= nd && nd < H && 0 <= ny && ny < N && 0 <= nx && nx < M && !isVisit[nd][ny][nx]) {
                    if (field[nd][ny][nx] == 0) {
                        field[nd][ny][nx] = 1;
                        isVisit[nd][ny][nx] = true;
                        q.add(new Node(nd, ny, nx, now.time + 1));
                        result = now.time + 1;
                    }
                }
            }


        }
    }

    private static boolean isCheck() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (field[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
