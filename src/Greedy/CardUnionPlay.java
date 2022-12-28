package Greedy;

import java.util.*;
import java.io.*;

public class CardUnionPlay {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");

        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);

        PriorityQueue<Long> pq = new PriorityQueue<>(); // n == 1000, m == 15*n, ai == 1000000(100만) 인 경우 int 범위 초과

        for (int i = 0; i < n; i++) {
            pq.offer(Long.parseLong(input2[i]));
        }

        while (m-- > 0) {
            long x = pq.poll();
            long y = pq.poll();

            long sum = x + y;

            pq.offer(sum);
            pq.offer(sum);
        }

        long result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
/*
3 1
3 2 6

5 9 8

 */