package BruteForcing;

import java.io.*;
import java.util.*;

public class ResearchInstitute3 {
    static int N, M, zeroCnt, result;
    static int[][] field;
    static ArrayList<Node> save; // 바이러스 위치 저장
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][N];
        save = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == 2) save.add(new Node(i, j));
                else if (field[i][j] == 0) zeroCnt++;
            }
        }
        br.close();

        /* 작동 */
        if (zeroCnt == 0) {
            bw.write(0 + "\n");
            bw.close();
            return;
        }
        result = Integer.MAX_VALUE;
        run(0, new Node[M], 0);

        /* 출력 */
        bw.write(result == Integer.MAX_VALUE ? -1 + "\n" : result + "\n");
        bw.close();
    }

    private static void run(int cnt, Node[] sel, int idx) {
        if (cnt == M) {
            spread(sel);
            if (result == 1) {
                System.out.println(1);
                System.exit(0);
            }
            return;
        }

        if (idx == N) return;

        for (int i = idx; i < save.size(); i++) {
            sel[cnt] = save.get(i);
            run(cnt + 1, sel, i + 1);
        }
    }

    private static void spread(Node[] sel) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] isVisit = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            q.offer(sel[i]);
            isVisit[sel[i].y][sel[i].x] = true;
        }
        int time = 0;
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) { // level search
                Node now = q.poll();

                for (int i = 0; i < 4; i++) {
                    int ny = now.y + dy[i];
                    int nx = now.x + dx[i];

                    if (isRange(ny, nx) && field[ny][nx] != 1 && !isVisit[ny][nx]) {
                        isVisit[ny][nx] = true;
                        q.offer(new Node(ny, nx));
                        if (field[ny][nx] == 0) {
                            count++;
                        }
                    }
                }
            }
            time++;
            if (zeroCnt == count) {
                result = Math.min(result, time);
                return;
            }
        }
    }

    private static boolean isRange(int ny, int nx) {
        return 0 <= ny && ny < N && 0 <= nx && nx < N;
    }

    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
