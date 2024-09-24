package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Application {
    static int N, M, max, result = Integer.MAX_VALUE;
    static Node[] app;
    static int[][] sol; // solution table

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        app = new Node[N];
        st = new StringTokenizer(br.readLine());
        StringTokenizer stc = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(stc.nextToken());
            Node node = new Node(m, c);
            app[i] = node;
            max += c;
        }
        br.close();

        /* 작동 */
        Arrays.sort(app);
        sol = new int[N + 1][max + 1];
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < max + 1; j++) {
                if (j >= app[i - 1].c) {
                    int next = sol[i - 1][j - app[i - 1].c] + app[i - 1].m;
                    sol[i][j] = Math.max(sol[i - 1][j], next);
                    if (sol[i][j] >= M) { // M 확보한 경우
                        result = Math.min(result, j);
                    }
                } else {
                    sol[i][j] = sol[i - 1][j];
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int m, c;

        Node(int m, int c) {
            this.m = m;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            if (this.c == o.c) {
                return this.m - o.m;
            }
            return this.c - o.c;
        }
    }
}
