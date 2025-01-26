package BSearch;

import java.io.*;
import java.util.*;

public class Firefly {
    static int N, H, result, total;
    static int[] up, down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[N / 2];
        down = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            st = new StringTokenizer(br.readLine());
            down[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            up[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        /* 작동 */
        Arrays.sort(down);
        Arrays.sort(up);
        result = N;
        run();

        /* 출력 */
        bw.write(result + " " + total + "\n"); // 파괴할 수, 가능한 구간
        bw.close();
    }

    private static void run() {
        for (int i = 1; i <= H; i++) {
            int downCount = cntStone(down, i);
            int upCount = cntStone(up, H + 1 - i);

            int count = upCount + downCount;
            if (result == count) {
                total++;
                continue;
            }

            if (result > count) {
                result = count;
                total = 1;
            }
        }
    }

    private static int cntStone(int[] position, int height) {
        int low = 0;
        int high = N / 2;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            if (position[mid] >= height) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return N / 2 - high;
    }
}
