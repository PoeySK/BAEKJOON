package Dijkstra;

import java.io.*;
import java.util.*;

public class SpaceProbe {
    static int N, result = Integer.MAX_VALUE;
    static boolean[] isCheck;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        isCheck = new boolean[N];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        floyd();
        isCheck[K] = true;
        dfs(K, 0, 1);

        bw.write(result + "\n");
        bw.close();
    }

    private static void dfs(int now, int total, int cnt) {
        if (cnt == N) {
            result = Math.min(result, total);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (isCheck[i]) continue;

            isCheck[i] = true;
            dfs(i, total + dist[now][i], cnt + 1);
            isCheck[i] = false;
        }
    }

    private static void floyd() {
        for (int s = 0; s < N; s++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][s] + dist[s][j]);
                }
            }
        }
    }
}
