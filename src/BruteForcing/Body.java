package BruteForcing;

import java.io.*;
import java.util.*;

public class Body {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] inBody = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int weight = Integer.parseInt(input[0]);
            int height = Integer.parseInt(input[1]);

            inBody[i][0] = weight;
            inBody[i][1] = height;
            inBody[i][2] = -1;
        }


        for (int i = 0; i < N; i++) {
            int rank = 1;
            for (int j = 0; j < N; j++) {
                if (inBody[i][0] < inBody[j][0] && inBody[i][1] < inBody[j][1]) {
                    rank++;
                }
            }
            inBody[i][2] = rank;
        }

        for (int i = 0; i < N; i++) {
            bw.write(inBody[i][2] + " ");
        }

        bw.flush();
        br.close();
        bw.close();

    }
}
