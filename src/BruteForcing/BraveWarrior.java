package BruteForcing;

import java.io.*;
import java.util.*;

public class BraveWarrior {
    static int N, K, result = Integer.MAX_VALUE;
    static Node[] soldiers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        soldiers = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int STR = Integer.parseInt(st.nextToken());
            int DEX = Integer.parseInt(st.nextToken());
            int INT = Integer.parseInt(st.nextToken());

            soldiers[i] = new Node(STR, DEX, INT);
        }
        br.close();

        Arrays.sort(soldiers);
        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int k = 0; k < N; k++) {
                    int nowSTR = soldiers[i].st;
                    int nowDEX = soldiers[j].de;
                    int nowINT = soldiers[k].in;

                    if (soldiers[k].st <= nowSTR && soldiers[k].de <= nowDEX) {
                        count++;
                    }

                    if (count == K) {
                        result = Math.min(result, nowSTR + nowDEX + nowINT);
                        break;
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int st, de, in;

        Node(int st, int de, int in) {
            this.st = st;
            this.de = de;
            this.in = in;
        }

        @Override
        public int compareTo(Node o) {
            return this.in - o.in;
        }
    }
}
