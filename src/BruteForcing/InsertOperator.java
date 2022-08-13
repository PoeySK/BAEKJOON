package BruteForcing;

import java.io.*;

public class InsertOperator {
    static int[] number, operator;
    static int N;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        number = new int[N];
        String[] input = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(input[i]);
        }

        String[] op = br.readLine().split(" ");
        operator = new int[4];

        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(op[i]);
        }

        operate(number[0], 1, 0);

        bw.write(max + "\n" + min);

        bw.flush();
        br.close();
        bw.close();

    }

    public static void operate(int num, int index, int count) {
        if (count == N - 1) { // 연산자 개수가 충당되면 max, min 검사
            max = Math.max(num, max);
            min = Math.min(num, min);
            return;
        }

        for (int j = 0; j < 4; j++) { // 연산자 검사
            if (operator[j] > 0) { // 0보다 크면 연산자 사용 가능
                operator[j]--; // 사용한 연산자이므로 개수 감소
                int rlt = calculate(num, number[index], j); // 계산
                operate(rlt, index + 1, count + 1);
                operator[j]++; // 다른 곳에서 사용 가능하므로 개수 증가
            }
        }

    }

    public static int calculate(int num1, int num2, int op) {
        switch (op) {
            case 0:
                return num1 + num2;
            case 1:
                return num1 - num2;
            case 2:
                return num1 * num2;
            case 3:
                return num1 / num2;
        }

        return 0;
    }
}
