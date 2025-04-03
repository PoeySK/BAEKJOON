package BSearch;

import java.io.*;
import java.util.*;

public class PocketMoneyManagement {
    static int N, M, high, low;
    static int[] day;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        day = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            high += day[i];
            low = Math.max(low, day[i]);
        }
        br.close();

        run();

        bw.write(low + "\n");
        bw.close();
    }

    private static void run() {
        int mid;

        while (low < high) {
            mid = (high + low) / 2;

            int count = 1;
            int K = mid;
            for (int i = 0; i < N; i++) {
                if (K - day[i] < 0) {
                    K = mid;
                    count++;
                }
                K -= day[i];
            }

            if (count > M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

    }
}
