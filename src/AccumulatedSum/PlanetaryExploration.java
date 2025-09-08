package AccumulatedSum;

import java.io.*;
import java.util.*;

public class PlanetaryExploration {
    static int M, N, K;
    static int[][][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        field = new int[3][M][N];

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        field[0][0][0] = input.charAt(0) == 'J' ? 1 : 0;
        field[1][0][0] = input.charAt(0) == 'O' ? 1 : 0;
        field[2][0][0] = input.charAt(0) == 'I' ? 1 : 0;
        for (int j = 1; j < N; j++) {
            char c = input.charAt(j);
            switch (c) {
                case 'J':
                    field[0][0][j] = field[0][0][j - 1] + 1;
                    field[1][0][j] = field[1][0][j - 1];
                    field[2][0][j] = field[2][0][j - 1];
                    break;
                case 'O':
                    field[0][0][j] = field[0][0][j - 1];
                    field[1][0][j] = field[1][0][j - 1] + 1;
                    field[2][0][j] = field[2][0][j - 1];
                    break;
                case 'I':
                    field[0][0][j] = field[0][0][j - 1];
                    field[1][0][j] = field[1][0][j - 1];
                    field[2][0][j] = field[2][0][j - 1] + 1;
                    break;
            }
        }
        for (int i = 1; i < M; i++) {
            input = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = input.charAt(j);
                if (j == 0) {
                    switch (c) {
                        case 'J':
                            field[0][i][0] = field[0][i - 1][0] + 1;
                            field[1][i][0] = field[1][i - 1][0];
                            field[2][i][0] = field[2][i - 1][0];
                            break;
                        case 'O':
                            field[0][i][0] = field[0][i - 1][0];
                            field[1][i][0] = field[1][i - 1][0] + 1;
                            field[2][i][0] = field[2][i - 1][0];
                            break;
                        case 'I':
                            field[0][i][0] = field[0][i - 1][0];
                            field[1][i][0] = field[1][i - 1][0];
                            field[2][i][0] = field[2][i - 1][0] + 1;
                            break;
                    }
                } else {
                    switch (c) {
                        case 'J':
                            field[0][i][j] = field[0][i][j - 1] + field[0][i - 1][j] - field[0][i - 1][j - 1] + 1;
                            field[1][i][j] = field[1][i][j - 1] + field[1][i - 1][j] - field[1][i - 1][j - 1];
                            field[2][i][j] = field[2][i][j - 1] + field[2][i - 1][j] - field[2][i - 1][j - 1];
                            break;
                        case 'O':
                            field[0][i][j] = field[0][i][j - 1] + field[0][i - 1][j] - field[0][i - 1][j - 1];
                            field[1][i][j] = field[1][i][j - 1] + field[1][i - 1][j] - field[1][i - 1][j - 1] + 1;
                            field[2][i][j] = field[2][i][j - 1] + field[2][i - 1][j] - field[2][i - 1][j - 1];
                            break;
                        case 'I':
                            field[0][i][j] = field[0][i][j - 1] + field[0][i - 1][j] - field[0][i - 1][j - 1];
                            field[1][i][j] = field[1][i][j - 1] + field[1][i - 1][j] - field[1][i - 1][j - 1];
                            field[2][i][j] = field[2][i][j - 1] + field[2][i - 1][j] - field[2][i - 1][j - 1] + 1;
                            break;
                    }
                }
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            int[] rlt = run(a, b, c, d);
            bw.write(rlt[0] + " " + rlt[1] + " " + rlt[2] + "\n");
        }
        br.close();
        bw.close();
    }

    private static int[] run(int a, int b, int c, int d) {
        int[] result = new int[3];

        if (a > 0 && b > 0) {
            result[0] = field[0][c][d] - field[0][a - 1][d] - field[0][c][b - 1] + field[0][a - 1][b - 1];
            result[1] = field[1][c][d] - field[1][a - 1][d] - field[1][c][b - 1] + field[1][a - 1][b - 1];
            result[2] = field[2][c][d] - field[2][a - 1][d] - field[2][c][b - 1] + field[2][a - 1][b - 1];
        } else if (a > 0) {
            result[0] = field[0][c][d] - field[0][a - 1][d];
            result[1] = field[1][c][d] - field[1][a - 1][d];
            result[2] = field[2][c][d] - field[2][a - 1][d];
        } else if (b > 0) {
            result[0] = field[0][c][d] - field[0][c][b - 1];
            result[1] = field[1][c][d] - field[1][c][b - 1];
            result[2] = field[2][c][d] - field[2][c][b - 1];
        } else {
            result[0] = field[0][c][d];
            result[1] = field[1][c][d];
            result[2] = field[2][c][d];
        }

        return result;
    }
}
