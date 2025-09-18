package BruteForcing;

import java.io.*;
import java.util.*;

public class PaperPiece {
    static int N, M, result;
    static int[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(input[j]);
            }
        }
        br.close();

        run(0, 0, N, M, 0);

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int sr, int sc, int dr, int dc, int value) {
        if (dr <= sr || dc <= sc) {
            result = Math.max(result, value);
            return;
        }
        int tmp = 1, num = 0;
        for (int i = dc - 1; i >= sc; i--) {
            num += tmp * field[sr][i];
            tmp *= 10;
        }
        run(sr + 1, sc, dr, dc, value + num);

        tmp = 1;
        num = 0;
        for (int i = dr - 1; i >= sr; i--) {
            num += tmp * field[i][dc - 1];
            tmp *= 10;
        }
        run(sr, sc, dr, dc - 1, value + num);

        tmp = 1;
        num = 0;
        for (int i = dc - 1; i >= sc; i--) {
            num += tmp * field[dr - 1][i];
            tmp *= 10;
        }
        run(sr, sc, dr - 1, dc, value + num);

        tmp = 1;
        num = 0;
        for (int i = dr - 1; i >= sr; i--) {
            num += tmp * field[i][sc];
            tmp *= 10;
        }
        run(sr, sc + 1, dr, dc, value + num);
    }
}
