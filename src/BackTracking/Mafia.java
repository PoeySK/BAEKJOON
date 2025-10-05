package BackTracking;

import java.io.*;
import java.util.*;

public class Mafia {
    static int N, result;
    static int[] guilt;
    static boolean[] isDead;
    static int[][] R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        guilt = new int[N];
        for (int i = 0; i < N; i++) {
            guilt[i] = Integer.parseInt(st.nextToken());
        }

        R = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int number = Integer.parseInt(st.nextToken());
        br.close();

        isDead = new boolean[N];
        run(number, N, 0);

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int ej, int people, int nc) {
        if (isDead[ej] || people == 1) {
            result = Math.max(result, nc);
            return;
        }

        if (people % 2 == 0) {
            night(ej, people, nc);
        } else {
            int idx = morning();

            isDead[idx] = true;
            run(ej, people - 1, nc);
            isDead[idx] = false;
        }
    }

    private static void night(int ej, int people, int nc) {
        for (int i = 0; i < N; i++) {
            if (isDead[i] || ej == i) continue;

            isDead[i] = true;
            for (int j = 0; j < N; j++) {
                if (isDead[j]) continue;

                guilt[j] += R[i][j];
            }
            run(ej, people - 1, nc + 1);

            for (int j = 0; j < N; j++) {
                if (isDead[j]) continue;

                guilt[j] -= R[i][j];
            }
            isDead[i] = false;
        }
    }

    private static int morning() {
        int idx = -1;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (isDead[i]) continue;

            if (max < guilt[i]) {
                max = guilt[i];
                idx = i;
            }
        }
        return idx;
    }
}
