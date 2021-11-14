package Greedy;

import java.io.*;
import java.util.*;

public class LostParenthesis {
    static int[] number; // 숫자
    static Queue<String> operator; // 연산자

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        String[] split;
        split = input.split("[-+]"); // 연산자로 문자열 분리
        number = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            number[i] = Integer.parseInt(split[i]);
        }

        split = input.split("[0-9]"); // 숫자로 문자열 분리 => null 값도 추출 됨.
        operator = new LinkedList<>(); // FIFO 구조
        for (int i = 0; i < split.length; i++) { // null 값을 제외한 -와 +만 list에 넣음.
            if (split[i].equals("-") || split[i].equals("+")) operator.offer(split[i]);
        }

        int numberIndex = 0;
        int sumIndex = 0;
        int nowSumIndex = sumIndex; // - 다음 + 하는 경우 or 처음 연산자가 +인 경우
        int[] sum = new int[number.length]; // 모두 - 연산자만 있을 경우 최대 사이즈 == number 리스트 사이즈
        sum[0] = number[0]; // 연산자가 없는 경우 number[0]값 그대로 출력, 연산자가 있을 시 replace 됨.
        while (!operator.isEmpty()) {
            /*
             * + 연산자 => sum의 인덱스를 그대로 두고 덧셈을 해줌.
             * - 연산자 => sum의 덧셈을 멈추고 다음 인덱스로 넘어감.
             */
            String op = operator.poll();
            if (op.equals("-")) { // sum 배열에 값 넣기
                if (nowSumIndex == 0) {
                    sum[sumIndex] = number[numberIndex++];
                } else {
                    nowSumIndex = 0;
                }
                sumIndex++;
                if (operator.isEmpty()) sum[sumIndex] = number[numberIndex];
            } else if (op.equals("+")) { // sum 배열의 값 증가
                if (nowSumIndex == 0) {
                    sum[sumIndex] = number[numberIndex++] + number[numberIndex++];
                } else {
                    sum[sumIndex] += number[numberIndex++];
                }
                nowSumIndex++;
            }

        }

        int index = 0;
        int result = sum[index++];
        while (index < sum.length) {
            result -= sum[index++];
        }

        bw.write(result + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}