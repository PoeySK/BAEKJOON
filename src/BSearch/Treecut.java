package BSearch;

import java.io.*;
import java.util.*;

public class Treecut {
    static long[] tree;
    static long N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Long.parseLong(s[0]);
        M = Long.parseLong(s[1]);
        tree = new long[(int) N];
        String[] input_N = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(input_N[i]);
        }
        Arrays.sort(tree);

        System.out.println(getHight(tree));
    }

    static long getHight(long[] array) {
        long low = 1;
        long high = array[(int) N - 1];
        long mid = 0;
        long check;

        while (low <= high) {
            check = 0;
            mid = (low + high) / 2;
            for (int i = 0; i < N; i++) {
                if (array[i] >= mid) {
                    check += array[i] - mid;
                }
            }

            if (check >= M) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }


        }
        return high;

    }
}
/*
4 7
20 15 10 17

max = 20
 */