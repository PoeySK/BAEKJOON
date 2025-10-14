package SlidingWindow;

import java.io.*;
import java.util.*;

public class SpinSushi {
    static int N, d, k, c, cnt, result;
    static int[] sushi, conSushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sushi[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        conSushi = new int[d + 1];

        for (int i = 0; i < k; i++) {
            if (conSushi[sushi[i]] == 0) cnt++;
            conSushi[sushi[i]]++;
        }

        if (conSushi[c] == 0) {
            result = cnt + 1;
        } else {
            result = cnt;
        }
        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = k; i < N + k; i++) {
            int idx = i > N - 1 ? i - N : i;
            int prev = idx - k < 0 ? N + (idx - k) : idx - k;

            conSushi[sushi[prev]]--;
            if (conSushi[sushi[prev]] == 0) cnt--;
            if (conSushi[sushi[idx]] == 0) cnt++;
            conSushi[sushi[idx]]++;

            if (conSushi[c] == 0) {
                result = Math.max(result, cnt + 1);
            } else {
                result = Math.max(result, cnt);
            }
        }
    }
}
