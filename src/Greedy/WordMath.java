package Greedy;

import java.util.*;
import java.io.*;

public class WordMath {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String[] list = new String[N];
        int[] alpha = new int[26];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            list[i] = s;
        }

        for (int i = 0; i < N; i++) {
            int digits = (int) Math.pow(10, list[i].length() - 1); // 자릿수 값.
            for (int j = 0; j < list[i].length(); j++) { // 입력된 문자의 길이만큼 자릿수가 정해짐.
                alpha[list[i].charAt(j) - 65] += digits; // 모두 대문자로 입력이므로 -65를 해주면 0부터 25까지 들어감.
                digits /= 10; // 다음 자릿수로 넘어가기.
            }
        }

        Arrays.sort(alpha);

        int sum = 0;
        int nineToZero = 9; // 순서대로 9~0 넣기.
        for (int i = alpha.length - 1; i >= 0; i--){ // 리버스 정렬을 못하므로 반대로 반복문 실행
            if(alpha[i] == 0){ // 0이 나오면 그 이후는 숫자가 없으므로 종료
                break;
            }
            sum += alpha[i]*nineToZero;
            nineToZero--;
        }
        bw.write(sum + "\n");
        bw.flush();

    }
}