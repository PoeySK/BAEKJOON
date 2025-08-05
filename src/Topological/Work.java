package Topological;

import java.io.*;
import java.util.*;

public class Work {
    static int N, result;
    static ArrayList<Integer>[] works;
    static int[] time, degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        works = new ArrayList[N + 1];
        degree = new int[N + 1];
        time = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            works[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            time[i + 1] = w;
            for (int j = 0; j < m; j++) {
                int next = Integer.parseInt(st.nextToken());
                works[next].add(i + 1);
                degree[i + 1]++;
            }
        }
        br.close();

        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 1; i < N + 1; i++) {
            if (degree[i] == 0) {
                q.offer(new int[]{i, time[i]});
            }
        }
        boolean[] isCheck = new boolean[N + 1];
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int number = now[0];
            int t = now[1];

            if (isCheck[number]) {
                continue;
            }
            isCheck[number] = true;
            result = Math.max(result, t);

            for (int i = 0; i < works[number].size(); i++) {
                int next = works[number].get(i);

                if (degree[next] != 0) {
                    degree[next]--;
                }
                if (degree[next] == 0) {
                    q.offer(new int[]{next, time[next] + t});
                }
            }
        }
    }
}
