package DynamicProgramming;

import java.io.*;
import java.util.*;

public class LIS3 {
    static int N;
    static int[] numbers, sol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        /* 작동 */
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        sol = new int[N];
        sol[0] = numbers[0];
        int size = 1;
        for (int i = 1; i < N; i++) {
            if (sol[size - 1] < numbers[i]) {
                sol[size++] = numbers[i];
            } else if (sol[size - 1] > numbers[i]) {
                int idx = bs(numbers[i], size, sol);
                sol[idx] = numbers[i];
            }
        }

        return size;
    }

    private static int bs(int target, int size, int[] sol) {
        int low = 0;
        int high = size;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            if (target > sol[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return high;
    }
}
