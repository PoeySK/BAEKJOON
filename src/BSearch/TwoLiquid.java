package BSearch;

import java.io.*;
import java.util.*;

public class TwoLiquid {
    static int K, left, right;
    static int[] liquids;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        liquids = new int[K];
        st = new StringTokenizer(br.readLine());
        int alk = 0, aci = 0;
        for (int i = 0; i < K; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
            if (liquids[i] < 0) alk++;
            else aci++;
        }
        br.close();

        /* 작동 */
        Arrays.sort(liquids);

        if (alk == 0) { // only 산성
            bw.write(liquids[0] + " " + liquids[1]);
            bw.close();
            return;
        }
        if (aci == 0) { // only 알칼리
            left = liquids[K - 2];
            right = liquids[K - 1];
            bw.write(liquids[K - 2] + " " + liquids[K - 1]);
            bw.close();
            return;
        }
        if (alk > 0 && aci > 0) run();

        /* 출력 */
        bw.write(liquids[left] + " " + liquids[right] + "\n");
        bw.close();
    }

    private static void run() {
        int low = 0;
        int high = K - 1;
        int min = Integer.MAX_VALUE;
        left = low;
        right = high;
        while (low < high) {
            int sum = liquids[low] + liquids[high];
            int abs = Math.abs(sum);
            if (sum > 0) {
                if(abs < min) {
                    min = abs;
                    left = low;
                    right = high;
                }
                high--;
            } else if (sum < 0) {
                if(abs < min) {
                    min = abs;
                    left = low;
                    right = high;
                }
                low++;
            } else { // sum == 0
                left = low;
                right = high;
                return;
            }
        }
    }
}
