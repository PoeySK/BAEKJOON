package Set;

import java.io.*;
import java.util.*;

public class SpinningSushi {
    static int N, d, k, c, result;
    static int[] sushi, plates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushi = new int[d];
        plates = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            plates[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int start = 0, end = k;
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            set.add(plates[i]);
            arr.add(plates[i]);
        }

        while (start < N) {
            if (!set.contains(c)) {
                result = Math.max(result, set.size() + 1);
            } else {
                result = Math.max(result, set.size());
            }

            arr.remove((Object) plates[start]);
            if (end < N) {
                arr.add(plates[end]);
            } else {
                arr.add(plates[end - N]);
            }
            set = new HashSet<>(arr);

            start++;
            end++;
        }
    }
}
