package PriorityQueue;

import java.io.*;
import java.util.*;

public class MaxHeap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int m = Integer.parseInt(br.readLine());

            if (m == 0) {
                if (pq.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(pq.peek());
                    pq.remove(pq.peek());
                }
            } else {
                pq.add(m);
            }
        }

    }
}