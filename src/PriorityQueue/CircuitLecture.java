package PriorityQueue;

import java.io.*;
import java.util.*;

public class CircuitLecture {
    static int N, result;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.offer(new Node(p, d));
        }
        br.close();

        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int space = N;
        boolean[] isCheck = new boolean[10001];
        isCheck[0] = true;
        while (!pq.isEmpty() && space > 0) {
            Node now = pq.poll();

            int idx = now.d;
            while (idx >= 0) {
                if (isCheck[idx]) {
                    idx--;
                } else {
                    isCheck[idx] = true;
                    result += now.p;
                    break;
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int p, d;

        public Node(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            if (this.p == o.p) return this.d - o.d;
            return o.p - this.p;
        }
    }
}
