package BruteForcing;

import java.io.*;
import java.util.*;

public class ShootingStar {
    static int N, M, L, K, result;
    static Node[] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stars = new Node[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars[i] = new Node(x, y);
        }
        br.close();

        run();

        bw.write(K - result + "\n");
        bw.close();
    }

    private static void run() {
        int count;
        for (int i = 0; i < K; i++) {
            for (int l = 0; l < L; l++) {
                count = 1;
                if (stars[i].x - l < 0) break;

                for (int j = 0; j < K; j++) {
                    if (i == j) continue;

                    int sx = stars[j].x - (stars[i].x - l);
                    int sy = stars[j].y - stars[i].y;

                    if (0 <= sx && sx <= L && 0 <= sy && sy <= L) {
                        count++;
                    }
                }
                result = Math.max(result, count);
            }

            for (int l = 0; l <= L; l++) {
                count = 1;
                if (stars[i].y - l < 0) break;

                for (int j = 0; j < K; j++) {
                    if (i == j) continue;

                    int sx = stars[j].x - stars[i].x;
                    int sy = stars[j].y - (stars[i].y - l);

                    if (0 <= sx && sx <= L && 0 <= sy && sy <= L) count++;
                }
                result = Math.max(result, count);
            }
        }
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
