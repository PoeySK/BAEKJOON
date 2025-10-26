package Simulation;

import java.io.*;
import java.util.*;

public class Gaaaaaaaaaarden {
    static final int[] dy = {1, 0, -1, 0};
    static final int[] dx = {0, 1, 0, -1};
    static int N, M, G, R, result;
    static int[][] field;
    static ArrayList<Node> save;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        save = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == 2) {
                    save.add(new Node(i, j));
                }
            }
        }
        br.close();
        run(0, 0, new boolean[save.size()]);

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int idx, int cnt, boolean[] sel) {
        if (idx == save.size()) return;
        if (cnt == R + G) {
            permutationRG(0, 0, new boolean[save.size()], sel);
            return;
        }

        for (int i = idx; i < save.size(); i++) {
            if (sel[i]) continue;

            sel[i] = true;
            run(i, cnt + 1, sel);
            sel[i] = false;
        }
    }

    private static void permutationRG(int idx, int cnt, boolean[] rg, boolean[] sel) {
        if (idx == save.size()) return;
        if (cnt == R) {
            int[][] copy = new int[N][M];
            for (int i = 0; i < N; i++) {
                copy[i] = field[i].clone();
            }
            ArrayDeque<Node> q = new ArrayDeque<>();
            for (int i = 0; i < save.size(); i++) {
                if (!sel[i]) continue;
                int y = save.get(i).y;
                int x = save.get(i).x;
                if (rg[i]) {
                    copy[y][x] = 5;
                } else {
                    copy[y][x] = 6;
                }
                q.offer(new Node(y, x, copy[y][x], 0));
            }
            result = Math.max(result, bfs(q, copy));
            return;
        }
        for (int i = idx; i < save.size(); i++) {
            if (rg[i] || !sel[i]) continue;
            rg[i] = true;
            permutationRG(i, cnt + 1, rg, sel);
            rg[i] = false;
        }
    }

    private static int bfs(ArrayDeque<Node> q, int[][] arr) {
        int count = 0;
        int[][] level = new int[N][M];
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node now = q.poll();
                if (arr[now.y][now.x] == 10) continue;

                for (int d = 0; d < 4; d++) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if (isOut(ny, nx) || arr[ny][nx] == 0) continue;
                    if (arr[ny][nx] == 1 || arr[ny][nx] == 2) {
                        arr[ny][nx] = now.isRG;
                        level[ny][nx] = now.lv + 1;
                        q.offer(new Node(ny, nx, now.isRG, now.lv + 1));
                    } else if (arr[ny][nx] < 10 && arr[ny][nx] != now.isRG && level[ny][nx] == now.lv + 1) {
                        count++;
                        arr[ny][nx] = 10; // 꽃
                    }
                }
            }
        }

        return count;
    }

    private static boolean isOut(int y, int x) {
        return 0 > y || N <= y || 0 > x || M <= x;
    }

    private static class Node {
        int y, x, isRG, lv;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Node(int y, int x, int isRG, int lv) {
            this(y, x);
            this.isRG = isRG;
            this.lv = lv;
        }
    }
}
