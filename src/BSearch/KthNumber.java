package BSearch;

import java.io.*;
import java.util.*;

public class KthNumber {
    static int N, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        /* 작동 */
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int low = 1;
        int high = k;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;

            int count = 0;
            for (int i = 1; i <= N; i++) { // mid 보다 작은 값 찾기
                count += Math.min(mid / i, N); // 최대 행의 개수까지
            }

            if (count < k) {
                low = mid + 1;
            } else { // count >= k
                high = mid - 1;
            }
        }

        return low;
    }
}