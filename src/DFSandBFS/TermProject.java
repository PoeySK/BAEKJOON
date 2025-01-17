package DFSandBFS;

import java.io.*;
import java.util.*;

public class TermProject {
    static int N, start, end;
    static int[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        while (TC-- > 0) {
            /* 입력 */
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            students = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                students[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            /* 작동 */
            int result = run();

            /* 출력 */
            bw.write(result + "\n");
        }
        br.close();
        bw.close();
    }

    private static int run() {
        int count = 0;
        boolean[] never = new boolean[N];
        boolean[] team = new boolean[N];
        int[] isVisit = new int[N];
        for (int i = 0; i < N; i++) {
            if (team[i]) continue;
            if (never[i] || team[students[i]]) {
                never[i] = true;
                count++;
                continue;
            }
            if (isVisit[i] > 0) continue;
            start = -1;
            end = -1;
            dfs(isVisit, i, 1, team, never);
            if (never[i]) count++;
        }
        return count;
    }

    private static void dfs(int[] isVisit, int now, int idx, boolean[] team, boolean[] never) {
        if (team[students[now]]) return;
        if (never[students[now]]) {
            never[now] = true;
            return;
        }
        if (isVisit[now] != 0) {
            start = isVisit[now];
            end = idx;
            return;
        }
        isVisit[now] = idx;

        dfs(isVisit, students[now], idx + 1, team, never);

        if (start <= idx && idx < end) {
            team[now] = true;
        } else {
            never[now] = true;
        }
    }
}
