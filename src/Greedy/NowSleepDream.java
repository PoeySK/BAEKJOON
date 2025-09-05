package Greedy;

import java.io.*;
import java.util.*;

public class NowSleepDream {
    static int N, A, B, result;
    static int[] homework;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        homework = new int[N];
        for (int i = 0; i < N; i++) {
            homework[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        Arrays.sort(homework);
        int time = 0;
        int count = 0;
        run(0, 0, 0);
        for (int i = 0; i < N; i++) {
            if (time + A <= homework[i]) {
                time += A;
                count++;
                run(time, i + 1, count);
            }
        }

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int time, int idx, int count) {
        for (int i = 0; i < A; i++) {
            int nowTime = B * i + time;
            int ct = A - i;
            int nowCount = count;

            for (int j = idx; j < N; j++) {
                if (nowTime + ct <= homework[j]) {
                    nowTime += ct;
                    nowCount++;
                }
            }

            result = Math.max(result, nowCount);
        }
    }
}
