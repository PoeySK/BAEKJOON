package BackTracking;

import java.io.*;
import java.util.*;

public class ResearchInstitute2 {
    static int N, M, result = Integer.MAX_VALUE;
    static int[][] field;
    static boolean[][] isVirus;
    static ArrayList<Node> save;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        save = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int state = Integer.parseInt(st.nextToken());

                if (state == 1) field[i][j] = 1;
                else if (state == 2) save.add(new Node(i, j));
            }
        }
        br.close();

        isVirus = new boolean[N][N];
        run(0, 0);

        bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n");
        bw.close();
    }

    private static void run(int cnt, int y) {
        if (cnt == M) {
            boolean[][] isVisit = new boolean[N][N];
            int rlt = infect(isVisit);
            if (isCheck(isVisit)) {
                result = Math.min(result, rlt);
            }
            return;
        }

        for (int i = y; i < save.size(); i++) {
            Node s = save.get(i);
            isVirus[s.y][s.x] = true;
            run(cnt + 1, i + 1);
            isVirus[s.y][s.x] = false;
        }
    }

    private static boolean isCheck(boolean[][] isVisit) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] == 0 && !isVisit[i][j]) return false;
            }
        }

        return true;
    }

    private static int infect(boolean[][] isVisit) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVirus[i][j]) {
                    q.offer(new Node(i, j, 0));
                    isVisit[i][j] = true;
                }
            }
        }

        int rlt = -1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            rlt = now.time;

            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;

                if (isRange(ny, nx) && !isVisit[ny][nx] && field[ny][nx] == 0) {
                    isVisit[ny][nx] = true;
                    q.offer(new Node(ny, nx, now.time + 1));
                }
            }
        }

        return rlt;
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static class Node {
        int y, x, time;

        Node(int y, int x, int time) {
            this(y, x);
            this.time = time;
        }

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    ", time=" + time +
                    '}';
        }
    }
}
