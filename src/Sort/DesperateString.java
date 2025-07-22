package Sort;

import java.io.*;
import java.util.*;

public class DesperateString {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        br.close();

        String result = run(st.nextToken());

        bw.write(result + "\n");
        bw.close();
    }

    private static String run(String str) {
        for (int i = 0; i < N - 1; i++) {
            char maxChar = str.charAt(i);
            for (int j = i + 1; j < N; j++) {
                if (maxChar < str.charAt(j)) {
                    maxChar = str.charAt(j);
                }
            }

            if (maxChar == str.charAt(i)) continue;

            int idx = -1;
            for (int j = i + 1; j < N; j++) {
                if (maxChar > str.charAt(j)) continue;

                if (idx == -1) {
                    idx = j;
                } else {
                    int a = j;
                    int b = idx;

                    while (a >= i && b >= i && str.charAt(a) == str.charAt(b)) {
                        a--;
                        b--;
                    }

                    if (b < i) {
                        idx = j;
                    } else if (str.charAt(a) > str.charAt(b)) {
                        idx = j;
                    }
                }
            }

            return reverse(str, i, idx);
        }

        return str;
    }

    private static String reverse(String str, int i, int j) {
        StringBuilder sb = new StringBuilder();

        sb.append(str, 0, i);
        for (int k = j; k >= i; k--) {
            sb.append(str.charAt(k));
        }
        sb.append(str, j + 1, N);

        return sb.toString();
    }
}
