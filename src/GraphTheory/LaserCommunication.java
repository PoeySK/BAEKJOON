package GraphTheory;

import java.io.*;
import java.util.*;

public class LaserCommunication {
    static int W, H, result = Integer.MAX_VALUE;
    static int[][] c;
    static char[][] field;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        field = new char[H][W];
        c = new int[2][2];
        int idx = 0;
        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                field[i][j] = input.charAt(j);
                if (field[i][j] == 'C') {
                    c[idx][0] = i;
                    c[idx++][1] = j;
                }
            }
        }
        br.close();

        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(c[0][0], c[0][1], 0, -1));
        int[][] mirror = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(mirror[i], Integer.MAX_VALUE);
        }
        mirror[c[0][0]][c[0][1]] = 0;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.y == c[1][0] && now.x == c[1][1]) {
                result = Math.min(result, mirror[now.y][now.x]);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isOutRange(ny, nx) || field[ny][nx] == '*') continue;

                if (i == now.d || now.d == -1) {
                    if (mirror[ny][nx] >= now.cnt) {
                        mirror[ny][nx] = now.cnt;
                        q.offerFirst(new Node(ny, nx, now.cnt, i));
                    }
                } else {
                    if (mirror[ny][nx] > now.cnt + 1) {
                        mirror[ny][nx] = now.cnt + 1;
                        q.offer(new Node(ny, nx, now.cnt + 1, i));
                    }
                }
            }
        }
    }

    private static boolean isOutRange(int y, int x) {
        return 0 > y || H <= y || 0 > x || W <= x;
    }

    private static class Node {
        int y, x, cnt, d;

        public Node(int y, int x, int cnt, int d) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.d = d;
        }
    }
}
