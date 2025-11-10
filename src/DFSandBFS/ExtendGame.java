package DFSandBFS;

import java.io.*;
import java.util.*;

public class ExtendGame {
    static int N, M, P;
    static int[] players, result;
    static char[][] field;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        field = new char[N][M];
        players = new int[P + 1];
        result = new int[P + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < P + 1; i++) {
            players[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Node> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                field[i][j] = input.charAt(j);

                if (Character.isDigit(field[i][j])) {
                    arr.add(new Node(i, j, field[i][j] - '0'));
                    result[field[i][j] - '0']++;
                }
            }
        }
        br.close();

        Collections.sort(arr);
        run(arr);

        for (int i = 1; i < P + 1; i++) {
            bw.write(result[i] + " ");
        }
        bw.close();
    }

    private static void run(ArrayList<Node> arr) {
        ArrayDeque<Node> q = new ArrayDeque<>(arr);
        boolean[][] isVisit = new boolean[N][M];
        while (!q.isEmpty()) {
            Node now = q.poll();
            ArrayDeque<Node> mq = new ArrayDeque<>();
            mq.offer(now);
            while (!q.isEmpty() && q.peek().p == now.p) {
                mq.offer(q.poll());
            }
            move(mq, now.p, q, isVisit);
        }
    }

    private static void move(ArrayDeque<Node> mq, int player, ArrayDeque<Node> q, boolean[][] isVisit) {
        int level = 1;
        while (!mq.isEmpty()) {
            int size = mq.size();

            for (int i = 0; i < size; i++) {
                Node now = mq.poll();

                if (isVisit[now.y][now.x]) continue;
                isVisit[now.y][now.x] = true;

                for (int d = 0; d < 4; d++) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if (isOut(ny, nx) || field[ny][nx] == '#' || isVisit[ny][nx]) continue;
                    if (field[ny][nx] != '.') continue;

                    field[ny][nx] = (char) (now.p + '0');
                    mq.offer(new Node(ny, nx, now.p));
                    if (level == players[now.p]) q.offer(new Node(ny, nx, now.p));
                    result[now.p]++;
                }
            }
            if (level == players[player]) return;
            level++;
        }
    }

    private static boolean isOut(int y, int x) {
        return 0 > y || N <= y || 0 > x || M <= x;
    }

    static class Node implements Comparable<Node> {
        int y, x, p;

        public Node(int y, int x, int p) {
            this.y = y;
            this.x = x;
            this.p = p;
        }

        @Override
        public int compareTo(Node o) {
            return this.p - o.p;
        }
    }
}
