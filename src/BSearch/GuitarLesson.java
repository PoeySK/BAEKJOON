package BSearch;

import java.io.*;
import java.util.*;

public class GuitarLesson {
    static int N, M;
    static int[] total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        total = new int[N];
        st = new StringTokenizer(br.readLine());
        total[0] = Integer.parseInt(st.nextToken());
        int max = total[0];
        for (int i = 1; i < N; i++) {
            total[i] = total[i - 1] + Integer.parseInt(st.nextToken());
            max = Math.max(max, total[i] - total[i - 1]);
        }
        br.close();

        /* 작동 */
        int result = run(max);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run(int max) {
        int low = max;
        int high = total[N - 1];
        int mid, ans = high;

        while (low <= high) {
            mid = (low + high) / 2;

            int count = 1;
            int last = 0;
            for (int i = 0; i < N; i++) {
                int now = total[i] - last;
                if (mid < now) {
                    last = total[i - 1];
                    count++;
                    i--;
                }
            }

            if (count <= M) {
                high = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}
