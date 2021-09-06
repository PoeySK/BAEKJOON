package Greedy;

import java.io.*;
import java.util.*;

public class BestOfBow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] list = new int[N];

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(s[i]);
        }

        int nowBowman = list[0];
        int count = 0;
        int maxCount = 0;
        for (int i = 1; i < N; i++) {
            if(nowBowman < list[i]) {
                nowBowman = list[i];
                maxCount = Math.max(count, maxCount);
                count = 0;
                i++;
            }
            count++;
        }
        maxCount = Math.max(count, maxCount);

        bw.write(maxCount + "\n");
        bw.flush();
    }
}