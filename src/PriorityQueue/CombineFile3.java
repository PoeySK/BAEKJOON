package PriorityQueue;

import java.io.*;
import java.util.*;

public class CombineFile3 {
    static int K;
    static PriorityQueue<Long> files;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());

        while (TC-- > 0) {
            /* 입력 */
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            files = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                files.offer(Long.parseLong(st.nextToken()));
            }

            /* 작동 */
            long result = run();

            /* 출력 */
            bw.write(result + "\n");
        }
        br.close();
        bw.close();
    }

    private static long run() {
        long total = 0;
        while (files.size() > 1) {
            long f1 = files.poll();
            long f2 = files.poll();

            long sum = f1 + f2;
            total += sum;
            files.offer(sum);
        }

        return total;
    }
}
