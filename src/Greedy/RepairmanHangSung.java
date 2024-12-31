package Greedy;

import java.io.*;
import java.util.*;

public class RepairmanHangSung {
    static int N, L;
    static int[] leak;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        leak = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            leak[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        /* 작동 */
        if (L == 1) { // 테이프 길이가 1이면 구멍당 테이프 필요
            bw.write(N + "\n");
            bw.close();
            return;
        }
        Arrays.sort(leak);
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int count = 1;
        int start = leak[0] + L - 1;
        for (int i = 1; i < N; i++) {
            if (start < leak[i]) {
                start = leak[i] + L - 1;
                count++;
            }
        }

        return count;
    }
}
