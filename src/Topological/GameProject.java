package Topological;

import java.io.*;
import java.util.*;

public class GameProject {
    static int N;
    static int[] times, degree, save;
    static ArrayList<Integer>[] games;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        times = new int[N + 1];
        degree = new int[N + 1];
        save = new int[N + 1];
        games = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) games[i] = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            boolean flag = false; // 첫 입력 플래그
            while (st.hasMoreTokens()) {
                int input = Integer.parseInt(st.nextToken());
                if (input == -1) break;
                if (!flag) { // 첫 입력
                    flag = true;
                    times[i] = input;
                } else {
                    degree[i]++;
                    games[input].add(i);
                }
            }
        }
        br.close();

        /* 작동 */
        run();

        /* 출력 */
        for (int i = 1; i < N + 1; i++) {
            bw.write(save[i] + "\n");
        }
        bw.close();
    }

    private static void run() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if (degree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            save[now] += times[now];

            for (int next : games[now]) {
                degree[next]--;
                save[next] = Math.max(save[next], save[now]);
                if (degree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}
