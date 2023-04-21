package Greedy;

import java.io.*;
import java.util.*;

public class HappyKindergarten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        String[] p = br.readLine().split(" ");
        br.close();
        int[] people = new int[n];
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(p[i]);
        }
        // Arrays.sort(people); 입력이 정렬로 주어짐

        int[] high = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            high[i] = people[i + 1] - people[i];
        }
        Arrays.sort(high);

        int result = 0;
        for (int i = 0; i < n - k; i++) { // 범위가 짧은 것을 고름
            /*
             * n - k 의미 (n - 1) - (k - 1) / (n - 1) : high 길이, (k - 1) : 범위 나눴을 시 사이 공간 개수
             * ex) n == 7, k == 3, 1 2 3 4 5 6 7
             * high = [1, 1, 1, 1, 1, 1], high의 길이 6
             * [1 2 3][4 5 6][7] 이렇게 나눈다.
             * 이러면 사이 공간은 2이다.
             * 범위를 만들기 위해선 총 4개의 차이가 필요하므로
             * (n - k)번 반복한다.
             */
            result += high[i];
        }

        bw.write(result + "\n");

        bw.flush();
        bw.close();
    }
}
