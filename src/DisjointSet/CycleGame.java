package DisjointSet;

import java.io.*;
import java.util.*;

public class CycleGame {
    static int N, M;
    static int[] games, parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        games = new int[M];

        parents = new int[N];
        for (int i = 0; i < N; i++) parents[i] = i;

        int result = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            /* 작동 */
            if (union(a, b)) {
                result = i + 1;
                break;
            }
        }
        br.close();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static boolean union(int a, int b) {
        int pa = find(parents[a]);
        int pb = find(parents[b]);

        if (pa != pb) {
            parents[pb] = pa;
            return false;
        } else {
            return true;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) return a;

        return parents[a] = find(parents[a]);
    }
}
