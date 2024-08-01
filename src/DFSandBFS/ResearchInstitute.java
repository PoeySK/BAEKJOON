package DFSandBFS;

import java.io.*;
import java.util.*;

// 두 번째 해결
public class ResearchInstitute {
    static int N, M, result;
    static int[][] field;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Queue<Node> virus = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == 2) {
                    virus.add(new Node(i, j));
                }
            }
        }
        br.close();

        /* 작동 */
        run(0);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int count) {
        if (count == 3) {
            int b = bfs();
            if (result < b) {
                result = b;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 0) { // 벽 세우기 (with. 조합)
                    field[i][j] = 1;
                    run(count + 1);
                    field[i][j] = 0;
                }
            }
        }
    }

    private static int bfs() { // 감염시키기
        int[][] copy = new int[N][];
        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(field[i], M);
        }
        Queue<Node> q = new ArrayDeque<>(virus);

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                    if (copy[ny][nx] == 0) {
                        copy[ny][nx] = 2;
                        q.add(new Node(ny, nx));
                    }
                }
            }
        }

        return counting(copy);
    }

    private static int counting(int[][] copy) { // 안전 영역 찾기
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
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
