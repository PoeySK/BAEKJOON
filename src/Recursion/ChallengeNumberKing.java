package Recursion;

import java.io.*;
import java.util.*;

public class ChallengeNumberKing {
    static int N;
    static int[] numbers;
    static boolean[] isVisit;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        int total = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            total += numbers[i];
        }
        br.close();

        isVisit = new boolean[N];
        set = new HashSet<>();
        run(0);

        bw.write((total - set.size() + 1) + "\n");
        bw.close();
    }

    private static void run(int cnt) {
        if (cnt == N) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (isVisit[i]) {
                    sum += numbers[i];
                }
            }
            set.add(sum);
            return;
        }

        isVisit[cnt] = true;
        run(cnt + 1);
        isVisit[cnt] = false;
        run(cnt + 1);
    }
}
