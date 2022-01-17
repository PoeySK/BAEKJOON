package DynamicProgramming;

import java.util.*;
import java.io.*;

public class MoveWithCandy {
    static int N, M;
    static int[][] maze;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] size = br.readLine().split(" ");
        N = Integer.parseInt(size[0]); // col
        M = Integer.parseInt(size[1]); // row

        maze = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(input[j]);
            }
        }
        bw.write(FindCandy() + "\n");
        br.close();
        bw.close();
    }

    public static int FindCandy() {
        int[][] sol = new int[N + 1][M + 1]; // solution table

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                int up = sol[i - 1][j] + maze[i - 1][j - 1];
                int left = sol[i][j - 1] + maze[i - 1][j - 1];
                int diagonal = sol[i - 1][j - 1] + maze[i - 1][j - 1];
                int maxOfUpAndLeft = Math.max(up, left);
                int realMax = Math.max(maxOfUpAndLeft, diagonal);
                sol[i][j] = realMax;
            }
        }

        return sol[N][M];
    }

}