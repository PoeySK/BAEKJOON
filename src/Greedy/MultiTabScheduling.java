package Greedy;

import java.io.*;
import java.util.*;

public class MultiTabScheduling {
    static int N, K, result;
    static int[] waiting;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        waiting = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            String product = st.nextToken();
            waiting[i] = Integer.parseInt(product);
        }
        br.close();

        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        ArrayList<Integer> plug = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int now = waiting[i];
            if (plug.contains(now)) continue;

            if (plug.size() < N) {
                plug.add(now);
            } else {
                ArrayList<Integer> keep = new ArrayList<>();
                for (int j = i + 1; j < K; j++) {
                    if (keep.size() == N - 1) break;

                    int next = waiting[j];
                    if (plug.contains(next)) {
                        if (keep.contains(next)) continue;
                        keep.add(next);
                    }
                }

                if (keep.isEmpty()) {
                    plug.remove(0);
                    plug.add(now);
                    result++;
                } else {
                    for (int j = 0; j < plug.size(); j++) {
                        if (!keep.contains(plug.get(j))) {
                            plug.remove(plug.get(j));
                            plug.add(now);
                            result++;
                            break;
                        }
                    }
                }
            }
        }
    }
}
