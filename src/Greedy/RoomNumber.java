package Greedy;

import java.io.*;
import java.util.*;

public class RoomNumber {
    static int N;
    static StringBuilder result;
    static Node[] rooms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        rooms = new Node[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            rooms[i] = new Node(p, i);
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        br.close();

        Arrays.sort(rooms);
        result = new StringBuilder();
        run(M);


        bw.write(result.toString() + "\n");
        bw.close();
    }

    private static void run(int M) {
        int[] comb = new int[51];
        int[] info = new int[51];
        int combIdx = 0;
        if (M >= rooms[0].price) {
            if (N > 1 && rooms[0].number == 0) {
                if (M >= rooms[1].price) {
                    info[combIdx] = 1;
                    comb[combIdx++] = rooms[1].number;
                    M -= rooms[1].price;
                }
            } else {
                info[combIdx] = 0;
                comb[combIdx++] = rooms[0].number;
                M -= rooms[0].price;
            }

            if (comb[0] == 0) {
                result.append(0);
                return;
            }

            while (M >= rooms[0].price) {
                info[combIdx] = 0;
                comb[combIdx++] = rooms[0].number;
                M -= rooms[0].price;
            }

            if (M == 0) {
                for (int i = 0; i < combIdx; i++) {
                    result.append(comb[i]);
                }
                return;
            }
            for (int i = 0; i < combIdx; i++) {
                int idx = info[i];
                Node room = rooms[idx];

                for (int j = 0; j < N; j++) {
                    if (room.number == rooms[j].number) continue;
                    int diff = rooms[j].price - room.price;
                    if (0 < diff && diff <= M && room.number < rooms[j].number) {
                        comb[i] = rooms[j].number;
                        room = rooms[j];
                        M -= diff;
                    } else if (diff >= M) {
                        break;
                    }
                }
            }
            for (int i = 0; i < combIdx; i++) {
                result.append(comb[i]);
            }
        } else {
            result.append("0");
        }
    }

    private static class Node implements Comparable<Node> {
        int price, number;

        Node(int price, int number) {
            this.price = price;
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            if (this.price == o.price) return o.number - this.number;
            return this.price - o.price;
        }
    }
}
