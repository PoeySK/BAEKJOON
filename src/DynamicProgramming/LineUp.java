package DynamicProgramming;

import java.io.*;
import java.util.*;

public class LineUp {
    static int N;
    static int[] sol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int[] line = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            line[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        sol = new int[N];
        int result = run(line);

        bw.write(result + "\n");
        bw.close();
    }

    private static int run(int[] line) { // LIS
        int idx = 1;
        sol[0] = line[0];
        for (int i = 1; i < N; i++) {
            if (sol[idx - 1] > line[i]) {
                int index = bs(idx, line[i]);
                sol[index] = line[i];
            } else {
                sol[idx++] = line[i];
            }
        }

        return N - idx;
    }

    private static int bs(int idx, int target) {
        int high = idx;
        int low = 0;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            if (sol[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }
}
