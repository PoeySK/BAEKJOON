package DFSandBFS;

import java.io.*;
import java.util.*;

public class PopulationMove {
    static int N, L, R;
    static boolean isCheck;
    static int[][] field;
    static boolean[][] isVisit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 출력 */
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);

        field = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int p = Integer.parseInt(st.nextToken());
                field[i][j] = p;
            }
        }
        br.close();

        /* 작동 */
        int day = 0;
        while (true) {
            day++;
            isVisit = new boolean[N][N];
            isCheck = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!isVisit[i][j]) {
                        run(i, j);
                    }
                }
            }

            if (!isCheck) { // 실패시 종료
                day--;
                break;
            }
        }

        /* 출력 */
        bw.write(day + "\n");
        bw.close();
    }

    private static void run(int y, int x) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(y, x));
        ArrayList<Node> save = new ArrayList<>();
        save.add(new Node(y, x));
        isVisit[y][x] = true;

        int sum = field[y][x];
        int count = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && !isVisit[ny][nx]) {
                    int P = Math.abs(field[now.y][now.x] - field[ny][nx]);
                    if (L <= P && P <= R) {
                        isVisit[ny][nx] = true;
                        q.offer(new Node(ny, nx));
                        sum += field[ny][nx];
                        count++;
                        isCheck = true;
                        save.add(new Node(ny, nx));
                    }
                }
            }
        }

        int d = sum / count;
        for (int i = 0; i < save.size(); i++) {
            Node n = save.get(i);
            field[n.y][n.x] = d;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
