package DFSandBFS;

import java.io.*;
import java.util.*;

public class BabyShark {
    static int N, result;
    static int[][] field;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    static class Node implements Comparable<Node> {
        int y;
        int x;
        int time;
        int level;

        public Node(int y, int x, int time, int level) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.level = level;
        }

        @Override
        public int compareTo(Node n) {
            if (this.y == n.y) return this.x - n.x;
            return this.y - n.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();

    }

    private static void run() {
        Queue<Node> q = new ArrayDeque<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 상어 찾기
        findShark(q);
        int size = 2;
        int eat = 0;
        boolean[][] isVisit = new boolean[N][N];
        while (!q.isEmpty()) {
            Node now = q.poll();

            // 레벨 단위 먹이 탐색
            if (!pq.isEmpty() && (q.isEmpty() || now.time > pq.peek().time)) {
                q.clear();
                Node pqNow = pq.poll();
                pq.clear();
                field[pqNow.y][pqNow.x] = 0;
                pqNow.level = 0;
                q.add(pqNow);
                eat++;
                if (eat == size) {
                    size++;
                    eat = 0;
                }
                result = pqNow.time;
                isVisit = new boolean[N][N];
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (0 <= ny && ny < N && 0 <= nx && nx < N && !isVisit[ny][nx]) {
                    isVisit[ny][nx] = true;
                    if (field[ny][nx] == 0 || field[ny][nx] == size) {
                        q.add(new Node(ny, nx, now.time + 1, now.level + 1));
                    } else if (field[ny][nx] < size) {
                        q.add(new Node(ny, nx, now.time + 1, now.level + 1));
                        if (pq.isEmpty()) {
                            pq.add(new Node(ny, nx, now.time + 1, now.level + 1));
                        } else {
                            if (pq.peek().level == now.level + 1) { // 같은 bfs 레벨만 탐색
                                pq.add(new Node(ny, nx, now.time + 1, now.level + 1));
                            }
                        }
                    }
                }
            }
        }
    }

    private static void findShark(Queue<Node> q) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] == 9) {
                    field[i][j] = 0;
                    q.add(new Node(i, j, result, 1));
                    break;
                }
            }
        }
    }
}
