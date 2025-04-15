package QueueAndDeque;

import java.io.*;
import java.util.*;

public class FireFactorial {
    static int R, C;
    static char[][] field;
    static ArrayDeque<Node> q = new ArrayDeque<>();
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        field = new char[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            for (int j = 0; j < C; j++) {
                field[i][j] = input.charAt(j);
                if (field[i][j] == 'J') {
                    q.offerFirst(new Node('J', i, j, 1));
                } else if (field[i][j] == 'F') {
                    q.offerLast(new Node('F', i, j));
                }
            }
        }
        br.close();

        int result = run();

        bw.write((result == -1 ? "IMPOSSIBLE" : result) + "\n");
        bw.close();
    }

    private static int run() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.word == 'J' && field[now.y][now.x] == 'F') continue;

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;

                if (!isRange(ny, nx)) {
                    if (now.word == 'J') return now.count;
                } else {
                    if (now.word == 'J') {
                        if (field[ny][nx] == '.') {
                            field[ny][nx] = 'J';
                            q.offerLast(new Node('J', ny, nx, now.count + 1));
                        }
                    } else { // now.word == 'F'
                        if (field[ny][nx] == '.' || field[ny][nx] == 'J') {
                            field[ny][nx] = 'F';
                            q.offerLast(new Node('F', ny, nx));
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }

    static class Node {
        char word;
        int y, x, count;

        Node(char word, int y, int x, int count) {
            this(word, y, x);
            this.count = count;
        }

        Node(char word, int y, int x) {
            this.word = word;
            this.y = y;
            this.x = x;
        }
    }
}
