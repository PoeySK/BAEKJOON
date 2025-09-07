package Samsung;

import java.io.*;
import java.util.*;

public class MagicalForest {
    static int R, C, K, result;
    static int[][] field;
    static final int[][] CHECK = {
            { -2, 0 }, { -1, -1 },
            { -1, 0 }, { -1, 1 },
            { 0,-1 }, { 0, 0 },
            { 0, 1 }, { 1, 0 }
    };
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        field = new int[R + 3][C];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            run(c, d, i + 1);
        }
        br.close();


        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int c, int d, int number) {
        int y = 1;

        while (true) {
            if (isGo(y + 1, c)) { // 남
                y++;
            } else if (isGo(y + 1, c - 1)) { // 서
                y++;
                c--;
                d = (d - 1 + 4) % 4;
            } else if (isGo(y + 1, c + 1)){ // 동
                y++;
                c++;
                d = (d + 1) % 4;
            } else { // 실패
                break;
            }
        }

        if (y < 4) {
            field = new int[R + 3][C];
            return;
        }

        field[y][c] = number; // center
        field[y - 1][c] = d == 0 ? -number : number;
        field[y][c + 1] = d == 1 ? -number : number;
        field[y + 1][c] = d == 2 ? -number : number;
        field[y][c - 1] = d == 3 ? -number : number;

        result += move(y, c);
    }

    private static int move(int y, int x) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(y, x, field[y][x]));

        boolean[][] isVisit = new boolean[R + 3][C];
        isVisit[y][x] = true;
        int maxY = y;
        while(!q.isEmpty()) {
            Node now = q.poll();
            maxY = Math.max(maxY, now.y);

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && field[ny][nx] != 0 && !isVisit[ny][nx]) {
                    if (Math.abs(now.number) == Math.abs(field[ny][nx]) || now.number < 0) {
                        isVisit[ny][nx] = true;
                        q.offer(new Node(ny, nx, field[ny][nx]));
                    }
                }
            }
        }

        return maxY - 2;
    }

    private static boolean isGo(int y, int x) {
        for (int i = 0; i < 8; i++) {
            int ny = y + CHECK[i][0];
            int nx = x + CHECK[i][1];

            if (!isRange(ny, nx) || field[ny][nx] != 0) return false;
        }

        return true;
    }
    private static boolean isRange(int y, int x) {
        return 0 <= y && y < R + 3 && 0 <= x && x < C;
    }

    private static class Node {
        int y, x, number;

        Node (int y, int x, int number) {
            this.y = y;
            this.x = x;
            this.number = number;
        }
    }
}

