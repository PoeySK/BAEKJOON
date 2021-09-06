package PriorityQueue;

import java.io.*;
import java.util.*;

public class AbsHeapPQ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> p = new PriorityQueue<>(
                (o1, o2) -> Math.abs(o1) == Math.abs(o2) ? Integer.compare(o1, o2) : Integer.compare(Math.abs(o1), Math.abs(o2))
        );

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int m = Integer.parseInt(br.readLine());

            if (m == 0) {
                if (p.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(p.peek());
                    p.poll();
                }
            } else {
                p.add(m);
            }
        }
    }
}