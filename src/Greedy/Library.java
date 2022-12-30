package Greedy;

import java.util.*;
import java.io.*;

public class Library {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);

        String[] input2 = br.readLine().split(" ");

        ArrayList<Integer> minus = new ArrayList<>();
        ArrayList<Integer> plus = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(input2[i]);
            if (value < 0) {
                minus.add(value);
            } else {
                plus.add(value);
            }
        }
        plus.sort(Collections.reverseOrder());
        Collections.sort(minus);

        int distance = 0;
        boolean isCheck = false; // 양수가 더 크면 true 음수가 더 크면 false

        // 양수만 or 음수만 있는 경우 체크크
        if (!plus.isEmpty() && !minus.isEmpty()) {
            if (plus.get(0) > Math.abs(minus.get(0))) { // 양수 쪽과 음수 쪽 중 더 먼 곳 찾기
                isCheck = true;
            }
        } else if (plus.isEmpty()) {
            isCheck = false;
        } else if (minus.isEmpty()) {
            isCheck = true;
        }

        // 편도
        if (isCheck) {
            distance = plus.get(0);
            for (int i = 0; i < m; i++) {
                if (!plus.isEmpty()) {
                    plus.remove(0);
                }
            }
        } else {
            distance = Math.abs(minus.get(0));
            for (int i = 0; i < m; i++) {
                if (!minus.isEmpty()) {
                    minus.remove(0);
                }
            }
        }

        // 왕복
        while (!plus.isEmpty()) {
            distance += (plus.get(0) * 2);
            for (int j = 0; j < m; j++) {
                if (!plus.isEmpty()) {
                    plus.remove(0);
                }
            }
        }
        while (!minus.isEmpty()) {
            distance += Math.abs((minus.get(0) * 2));
            for (int j = 0; j < m; j++) {
                if (!minus.isEmpty()) {
                    minus.remove(0);
                }
            }

        }

        bw.write(distance + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

}
