package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Hotel {
    static int C, N, result;
    static Node[] cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cities = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            cities[i] = new Node(cost, value);
        }
        br.close();

        Arrays.sort(cities);
        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int[] sol = new int[C + 1];
        Arrays.fill(sol, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            Node city = cities[i];
            for (int j = 1; j < C + 1; j++) {
                if (j <= city.v) {
                    sol[j] = Math.min(sol[j], city.c);
                } else if (sol[j - city.v] != Integer.MAX_VALUE) {
                    sol[j] = Math.min(sol[j], sol[j - city.v] + city.c);
                }
            }
        }

        result = sol[C];
    }

    private static class Node implements Comparable<Node> {
        int c, v;

        Node(int c, int v) {
            this.c = c;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            if (this.c == o.c) return o.v - this.v;
            return this.c - o.c;
        }
    }
}
