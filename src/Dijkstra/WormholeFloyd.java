package Dijkstra;

import java.io.*;
import java.util.*;

public class WormholeFloyd {
    static final int INF = 1000000000;
    static int N, M, W;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        while (TC-- > 0) {
            /* 입력 */
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            dist = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = i == j ? 0 : INF;
                }
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                // 양방향
                dist[S][E] = Math.min(dist[S][E], T);
                dist[E][S] = Math.min(dist[E][S], T);
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken()) * -1;

                // 단방향
                dist[S][E] = Math.min(dist[S][E], T);
            }

            /* 작동 */
            run();
            boolean result = false;
            for (int i = 1; i <= N; i++) {
                if (dist[i][i] < 0) { // 자신 -> 자신 확인
                    result = true;
                    break;
                }
            }

            /* 출력 */
            bw.write(result ? "YES\n" : "NO\n");
        }
        br.close();
        bw.close();
    }

    private static void run() {
        for (int s = 1; s <= N; s++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][s] < INF && dist[s][j] < INF) { // overflow
                        dist[i][j] = Math.min(dist[i][j], dist[i][s] + dist[s][j]);
                    }
                }
            }
        }
    }
}
