package PriorityQueue;

import java.io.*;
import java.util.*;

public class FuelFilling {
    static int N, result;
    static Node[] stations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        stations = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            stations[i] = new Node(a, b);
        }
        st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        br.close();

        Arrays.sort(stations);
        run(L, P);

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int L, int P) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 만들 수 있는 연료로 목표에 도달할 수 있는지 파악
        int idx = 0;
        while (P < L) {
            for (int i = idx; i < N; i++) {
                Node station = stations[i];
                if (station.position <= P) {
                    pq.offer(station.fuel);
                } else {
                    idx = i;
                    break;
                }
            }

            if (pq.isEmpty()) {
                result = -1;
                return;
            }

            P += pq.poll();
            result++;
        }
    }

    static class Node implements Comparable<Node> {
        int position, fuel;

        Node(int position, int fuel) {
            this.position = position;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Node o) {
            return this.position - o.position;
        }
    }
}
