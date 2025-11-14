package Simulation;

import java.io.*;
import java.util.*;

public class MooGame {
    static int N;
    static char result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        br.close();

        if (N > 3) {
            run();
        } else if (N == 1) {
            result = 'm';
        } else {
            result = 'o';
        }

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int[] save = new int[30];
        int k = 3;
        int moo = 3;
        int idx = 0;
        while (k < N) {
            save[idx++] = k;
            k = k * 2 + ++moo;
        }

        N -= save[idx - 1];
        N -= moo;

        while (N > 3) {
            idx = 0;
            while (N > save[idx]) {
                idx++;
            }
            moo = idx + 3;
            N -= save[--idx];
            N -= moo;
        }

        if (N < 0) N += moo;
        if (N == 1) result = 'm';
        else result = 'o';
    }
}

/*
moo 3
moomooomoo 10
moomooomoomoooomoomooomoo 25
moomooomoomoooomoomooomoomooooomoomooomoomoooomoomooomoo 56
moomooomoomoooomoomooomoomooooomoomooomoomoooomoomooomoomoooooomoomooomoomoooomoomooomoomooooomoomooomoomoooomoomooomoo 119

N: 9999
moo = 14
9999(N) - 8177(save[idx -1]) - 14(moo) = 1808(N)
1808(N) - 1012(save[idx]) - 11(moo) = 785(N)
785(N) - 501(save[idx]) - 10(moo) = 274(N)
274(N) - 246(save[idx]) - 9(moo) = 19(N)
19(N) - 10(save[idx]) - 5(moo) = 4(N)
4(N) - 3(save[idx]) - 4(moo) = -3(N)

-------------------------------

N: 79
moo = 7
79(N) - 56(save[idx - 1]) = 23(num)
23(num) - 7(moo) = 16(num)
num > moo => 반복된 곳 다시 탐색
moo = 5
16(num) - 10(save[idx]) = 13(num)
13(num) - 5(moo) = 8(num)
moo = 4
8(num) - 3(save[idx]) = 5(num)
5(num) - 4(moo) = 1(num)

----------------------

N: 44
moo = 6
44(N) - 25(prev) = 19(N)
19(N) - 6(moo) = 13(N)
num > moo => 다시 탐색
moo = 5
13(N) - 10(save[idx]) = 3(N)
N < moo => moooo 에서 찾기

---

if (moo + save[idx - 1] >= N) { N -= save[idx - 1] }
 */