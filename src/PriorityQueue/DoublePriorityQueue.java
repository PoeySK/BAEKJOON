package PriorityQueue;

import java.io.*;
import java.util.*;

public class DoublePriorityQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());


        while (N-- > 0) {
            PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> min = new PriorityQueue<>();
            int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                String[] Q = br.readLine().split(" ");
                char type = Q[0].charAt(0);
                int value = Integer.parseInt(Q[1]);
                if(type == 'I'){

                } else {

                }

            }

        }

    }

}
