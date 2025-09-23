package GraphTheory;

import java.io.*;
import java.util.*;

public class MakeMaze {
    static int N;
    static int[][] field, counting;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        field = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(input[j]);
            }
        }
        br.close();

        counting = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(counting[i], Integer.MAX_VALUE);
        }
        counting[0][0] = 0;
        run(0, 0, 0);

        bw.write(counting[N - 1][N - 1] + "\n");
        bw.close();
    }

    private static void run(int y, int x, int change) {
        if (y == N - 1 && x == N - 1) return;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (isRange(ny, nx)) {
                if (field[ny][nx] == 1) {
                    if (counting[ny][nx] > change) {
                        counting[ny][nx] = change;
                        run(ny, nx, change);
                    }
                } else {
                    if (counting[ny][nx] > change + 1) {
                        counting[ny][nx] = change + 1;
                        run(ny, nx, change + 1);
                    }
                }
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
