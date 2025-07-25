package Sweeping;

import java.io.*;
import java.util.*;

public class WaterTaxi {
    static int N, M;
    static long result;
    static ArrayList<Node> people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        people = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (u > v) {
                people.add(new Node(u, v));
            }
        }
        br.close();

        Collections.sort(people);
        if (!people.isEmpty()) {
            run();
        }

        bw.write((result + M) + "\n");
        bw.close();
    }

    private static void run() {
        long left = people.get(0).v;
        long right = people.get(0).u;

        for (int i = 1; i < people.size(); i++) {
            Node node = people.get(i);
            if (left <= node.u) {
                left = Math.min(left, node.v);
            } else {
                result += (right - left) * 2;
                left = node.v;
                right = node.u;
            }
        }
        result += (right - left) * 2;
    }

    static class Node implements Comparable<Node> {
        int u, v;

        Node(int u, int v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            if (o.u == this.u) return this.v - o.v;
            return o.u - this.u;
        }
    }
}
