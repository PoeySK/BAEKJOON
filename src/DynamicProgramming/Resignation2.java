package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Resignation2 {
    static int N;
    static Node[] schedule;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        schedule = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            schedule[i] = new Node(t, p);
        }
        br.close();

        /* 작동 */
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int[] sol = new int[N + 2];
        for (int i = 1; i < N + 1; i++) {
            Node now = schedule[i - 1];
            int next = i + now.t;
            sol[i] = Math.max(sol[i - 1], sol[i]);
            if (next > N + 1) {
                continue;
            }
            sol[next] = Math.max(sol[next], now.p + sol[i]);
        }
        sol[N + 1] = Math.max(sol[N + 1], sol[N]);
        return sol[N + 1];
    }

    static class Node {
        int t, p;

        Node(int t, int p) {
            this.t = t;
            this.p = p;
        }
    }
}
