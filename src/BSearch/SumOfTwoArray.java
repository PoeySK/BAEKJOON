package BSearch;

import java.io.*;
import java.util.*;

public class SumOfTwoArray {
    static int T, N, M;
    static long count;
    static ArrayList<Integer> sumB, sumA;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sumA = new ArrayList<>();
        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int a = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + a;
            sumA.add(sum[i]);
        }
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                sumA.add(sum[j] - sum[i]);
            }
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sumB = new ArrayList<>();
        sum = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            int b = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + b;
            sumB.add(sum[i]);
        }
        for (int i = 1; i < M; i++) {
            for (int j = i + 1; j <= M; j++) {
                sumB.add(sum[j] - sum[i]);
            }
        }
        br.close();

        /* 작동 */
        Collections.sort(sumA);
        Collections.sort(sumB);
        run();

        /* 출력 */
        bw.write(count + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < sumA.size(); i++) {
            int target = T - sumA.get(i);
            int start = lower(target);
            int end = upper(target);
            count += (end - start);
        }
    }

    private static int lower(int target) {
        int low = 0;
        int high = sumB.size();
        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            if (target <= sumB.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int upper(int target) {
        int low = 0;
        int high = sumB.size();
        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            if (target < sumB.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
