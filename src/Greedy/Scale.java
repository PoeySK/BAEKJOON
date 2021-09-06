package Greedy;

import java.io.*;
import java.util.*;

public class Scale {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");

        int[] weight = new int[N];

        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(s[i]);
        }

        Arrays.sort(weight);

        int check = 1;

        for (int i = 0; i < N; i++) {
            if (check < weight[i]) {
                break;
            }
            check += weight[i];
        }
        bw.write(check + "\n");
        bw.flush();

    }
}
/*
1 1 2 3 6 7 30
5
 */