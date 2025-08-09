package DynamicProgramming;

import java.io.*;
import java.util.*;

public class LargestSquare {
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

        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int[][] sq = new int[N][M];
        int maxSquare = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 0) continue;
                sq[i][j] = 1;
                if (i > 0 && j > 0) {
                    int up = sq[i - 1][j];
                    int left = sq[i][j - 1];
                    int diagonal = sq[i - 1][j - 1];
                    if (up > 0 && left > 0 && diagonal > 0) {
                        if (up == left && left == diagonal) {
                            sq[i][j] = up + 1;
                        } else {
                            int minSquare = Math.min(up, left);
                            minSquare = Math.min(minSquare, diagonal);
                            sq[i][j] = minSquare + 1;
                        }
                    }
                }
                maxSquare = Math.max(maxSquare, sq[i][j]);
            }
        }

        result = maxSquare * maxSquare;
    }
}
