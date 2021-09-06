package BSearch;

import java.io.*;
import java.util.*;

public class Share {
    static int N, C;
    static int[] share;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        C = Integer.parseInt(s[1]);

        share = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            share[i] = Integer.parseInt(br.readLine());
            max = Math.max(share[i], max);
        }

        Arrays.sort(share);
        getSharing(max);
    }

    static void getSharing(int max) {
        int low = 1; // 최소 길이
        int high = max - share[0]; // 최대 길이
        int mid; // 탐색 값
        int check; // 확인
        int countSave = 0;

        while (low <= high) {
            int count = 1; // 공유기 개수, 1은 초기값: 첫 인덱스에는 공유기를 무조건 설치
            mid = (low + high) / 2;
            System.out.println(low + " " + high + " " +mid);
            check = share[0] + mid; // 처음있는 공유기에서 mid 값만큼 벗어난 곳부터 공유기 설치 가능

            for (int i = 0; i < N; i++) {
                if(share[i] >= check){ // 확인 값보다 크거나 같은 곳에서 공유기 설치 가능
                    check = share[i] + mid; // 설치를 했다면 그 설치한 곳에서 mid 값만큼 더 벗어나야 함.
                    count++; // 공유기 개수 증가
                }
            }
            if(count >= C){ // 공유기 개수가 C값보다 크면 mid값이 작은 것이기 때문에 low값 증가
                countSave = mid;
                low = mid + 1;
            } else { // 공유기 개수가 C값보다 작으면 mid값이 큰 것이기 때문에 high값을 감소
                high = mid - 1;
            }
        }
        System.out.println();
        System.out.println(countSave);
    }

}