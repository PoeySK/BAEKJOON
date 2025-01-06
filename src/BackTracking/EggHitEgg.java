package BackTracking;

import java.io.*;
import java.util.*;

public class EggHitEgg {

    static int N;
    static Node[] eggs;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        eggs = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Node(d, w);
        }
        br.close();

        /* 작동 */
        run(0, 0);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int idx, int count) {
        if (idx == N) return;
        if (eggs[idx].d <= 0) {
            run(idx + 1, count);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i == idx || eggs[i].d <= 0) continue;

            eggs[idx].d -= eggs[i].w;
            eggs[i].d -= eggs[idx].w;

            int nc = count;
            if (eggs[idx].d <= 0) nc++;
            if (eggs[i].d <= 0) nc++;
            result = Math.max(result, nc);

            run(idx + 1, nc);

            eggs[idx].d += eggs[i].w;
            eggs[i].d += eggs[idx].w;
        }
    }

    static class Node {

        int d;
        int w;

        Node(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "d=" + d +
                    ", w=" + w +
                    '}';
        }
    }
}
