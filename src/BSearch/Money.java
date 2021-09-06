package BSearch;

import java.io.*;
import java.util.*;

public class Money {
    static long[] m;
    static int N;
    static long max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        m = new long[N];

        String[] s = br.readLine().split(" ");

        long m_max = 0;
        for (int i = 0; i < N; i++) {
            m[i] = Long.parseLong(s[i]);
            m_max = Math.max(m[i], m_max);
        }
        Arrays.sort(m);

        max = Long.parseLong(br.readLine());

        getMoney();
    }


    static void getMoney() {
        long low = 1;
        long high = m[m.length - 1];
        long mid;
        long check;
        long real = 0;

        while (low <= high) {
            check = 0;
            mid = (low + high) / 2;

            for (int i = 0; i < N; i++) {
                check += Math.min(m[i], mid);
                // 45line 주석과 같음.
                // if (m[i] >= mid) {
                //    check += mid;
                // } else {
                //   check += m[i];
                // }
            }
            if (check > max) {
                high = mid - 1;
            } else {
                real = Math.max(real,mid);
                low = mid + 1;
            }

        }

        System.out.println(real);
    }
}