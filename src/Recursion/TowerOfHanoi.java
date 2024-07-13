package Recursion;

import java.io.*;
import java.util.*;

public class TowerOfHanoi {
    static ArrayList<Integer> from, to;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        int K = Integer.parseInt(br.readLine());
        br.close();

        /* 작동 */
        from = new ArrayList<>();
        to = new ArrayList<>();
        hanoi(K, 1, 2, 3);

        /* 출력 */
        bw.write(((int) Math.pow(2, K) - 1) + "\n");
        for (int i = 0; i < from.size(); i++) {
            bw.write(from.get(i) + " " + to.get(i) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void hanoi(int K, int start, int temp, int end) {
        if (K == 1) {
            from.add(start);
            to.add(end);
            return;
        }

        hanoi(K - 1, start, end, temp); // n-1 의 목적
        from.add(start);
        to.add(end);
        hanoi(K - 1, temp, start, end); // 모아주기
    }
}
