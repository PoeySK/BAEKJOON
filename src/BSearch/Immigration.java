package BSearch;

import java.io.*;
import java.util.*;

public class Immigration {
    static int N, M;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        check = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            check[i] = T;
        }
        br.close();

        /* 작동 */
        Arrays.sort(check);
        long result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static long run() {
        long low = check[0];
        long high = (long) check[check.length - 1] * M;
        long mid;
        while (low <= high) {
            mid = (low + high) / 2;

            long total = 0; // 심사할 수 있는 인원 수
            for (int c : check) {
                total += mid / c; // 심사대별 심사 가능한 수

                if (total > M || c > mid) { // 심사 초과
                    break;
                }
            }

            if (total >= M) { // 모두 심사 가능
                high = mid - 1;
            } else { // 심사 불가능
                low = mid + 1;
            }
        }

        return low;
    }
}
