package Simulation;

import java.io.*;
import java.util.*;

public class QueenBee {
    static int M, N;
    static int[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        field = new int[M][M];
        for (int i = 0; i < M; i++) {
            Arrays.fill(field[i], 1);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            run(zero, one, two);
        }
        br.close();

        grow();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(field[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.close();
    }

    private static void run(int zero, int one, int two) {
        for (int i = M - 1; i > 0; i--) {
            if (zero > 0) {
                zero--;
            } else if (one > 0) {
                field[i][0] += 1;
                one--;
            } else if (two > 0) {
                field[i][0] += 2;
                two--;
            }
        }
        for (int j = 0; j < M; j++) {
            if (zero > 0) {
                zero--;
            } else if (one > 0) {
                field[0][j] += 1;
                one--;
            } else if (two > 0) {
                field[0][j] += 2;
                two--;
            }
        }
    }

    private static void grow() {
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < M; j++) {
                field[i][j] = field[i - 1][j];
            }
        }
    }
}
