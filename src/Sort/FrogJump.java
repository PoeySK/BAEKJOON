package Sort;

import java.io.*;
import java.util.*;

public class FrogJump {
    static int N, Q;
    static int[] group;
    static Node[] logs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        logs = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            logs[i] = new Node(x1, x2, y, i + 1);
        }
        Arrays.sort(logs);
        run();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            bw.write((group[from] == group[to] ? 1 : 0) + "\n");
        }
        br.close();
        bw.close();
    }

    private static void run() {
        int end = logs[0].x2;
        int prev = 0;
        group = new int[N + 1];
        int groupNumber = 1;
        for (int i = 1; i < N; i++) {
            if (end < logs[i].x1) {
                for (int j = prev; j < i; j++) {
                    group[logs[j].number] = groupNumber;
                }
                groupNumber++;
                end = logs[i].x2;
                prev = i;
            } else {
                end = Math.max(end, logs[i].x2);
            }
        }

        for (int i = prev; i < N; i++) {
            group[logs[i].number] = groupNumber;
        }
    }

    static class Node implements Comparable<Node> {
        int x1, x2, y, number;

        Node(int x1, int x2, int y, int number) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x1 == o.x1) return this.x2 - o.x2;
            return this.x1 - o.x1;
        }
    }
}
