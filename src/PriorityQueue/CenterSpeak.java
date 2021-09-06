package PriorityQueue;

import java.io.*;
import java.util.*;

public class CenterSpeak {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int m = Integer.parseInt(br.readLine());

            if (max.isEmpty() && min.isEmpty()) {
                max.offer(m);
            } else {
                if (!max.isEmpty() && max.peek() > m) {
                    if (max.size() > min.size()) {
                        min.offer(max.poll());
                    }
                    max.offer(m);
                } else if (!max.isEmpty() && max.peek() < m) {
                    min.offer(m);
                    if (min.size() > max.size()) {
                        max.offer(min.poll());
                    }
                } else {
                    max.offer(m);
                    if (max.size() > min.size()) {
                        min.offer(max.poll());
                    }
                }
            }
            if (max.size() >= min.size()) {
                bw.write(max.peek() + "\n");
            } else {
                bw.write(min.peek() + "\n");
            }
        }
        bw.flush();
    }
}