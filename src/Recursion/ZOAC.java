package Recursion;

import java.io.*;
import java.util.*;

public class ZOAC {
    static int N;
    static String word;
    static boolean[] isCheck;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        word = st.nextToken();
        N = word.length();
        isCheck = new boolean[N];

        run(0, N - 1);
    }
    private static void run(int left, int right) {
        if (left > right) return;

        int minIdx = left;
        for (int i = left; i <= right; i++) {
            if (isCheck[i]) continue;
            char c = word.charAt(i);
            if (word.charAt(minIdx) > c) {
                minIdx = i;
            }
        }
        isCheck[minIdx] = true;
        print();
        run(minIdx + 1, right);
        run(left, minIdx - 1);
    }

    private static void print() {
        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (isCheck[i]) sb.append(word.charAt(i));
        }
        System.out.println(sb.toString());
    }
}
