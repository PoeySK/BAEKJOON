package Greedy;

import java.io.*;
import java.util.*;

public class InstantCupRamen {
    static int N;
    static ArrayList<Integer>[] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        info = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            info[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            info[deadline].add(cup);
        }

        long result = run();

        bw.write(result + "\n");
        bw.close();
    }

    private static long run() {
        int total = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = N; i > 0; i--) {
            if (!info[i].isEmpty()) {
                for (int j = 0; j < info[i].size(); j++) {
                    pq.offer(info[i].get(j));
                }
                total += pq.poll();
            } else {
                if (!pq.isEmpty()) total += pq.poll();
            }
        }

        return total;
    }
}
