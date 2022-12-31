package Greedy;

import java.io.*;
import java.util.*;

public class Treasure {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] inputA = br.readLine().split(" ");
        String[] inputB = br.readLine().split(" ");

        int[] A = new int[n];
        Integer[] B = new Integer[n];

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(inputA[i]);
            B[i] = Integer.parseInt(inputB[i]);
        }

        Arrays.sort(A); // 오름차순
        Arrays.sort(B, Collections.reverseOrder()); // 내림차순

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += A[i] * B[i];
        }
        bw.write(result + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}
