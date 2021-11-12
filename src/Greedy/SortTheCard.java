package Greedy;

import java.io.*;
import java.util.*;

public class SortTheCard {
    static int N; // 카드 개수
    static PriorityQueue<Integer> card = new PriorityQueue<>(); // 최솟값 기준 힙 정렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            card.offer(Integer.parseInt(br.readLine()));
        }

        int result = 0;
        while ((card.size() != 1) && (!card.isEmpty())) { // card의 size가 1개면 비교를 안함.
            int temp = card.poll() + card.poll(); // root를 두 번 삭제함.
            card.offer(temp); // 삭제된 두 값을 더한 값을 넣어줌.
            result += temp; // 넣어준 값을 계속 더함.
        }

        bw.write(result + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

}