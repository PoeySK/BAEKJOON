package PriorityQueue;

import java.io.*;
import java.util.*;

public class JewelThief {
    static int N, K;
    static long result;
    static PriorityQueue<Node> jewels;
    static int[] bags;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewels = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            jewels.offer(new Node(M, V));
        }

        bags = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        /* 작동 */
        Arrays.sort(bags);
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        PriorityQueue<Integer> save = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < K; i++) {
            int bag = bags[i];
            while (!jewels.isEmpty()) {
                if (bag < jewels.peek().m) break;
                save.offer(jewels.poll().v);
            }

            if (!save.isEmpty()) result += save.poll();
        }
    }

    static class Node implements Comparable<Node> {
        int m, v;

        Node(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.m - o.m;
        }
    }
}
