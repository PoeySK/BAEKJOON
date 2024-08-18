package Recursion;

import java.io.*;
import java.util.*;

public class CreateParenthesis {
    static int N, before, result = Integer.MIN_VALUE;
    static int[] numbers;
    static char[] ops;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        String[] input = br.readLine().split("");
        numbers = new int[N / 2 + 1];
        ops = new char[N / 2];

        int opsIdx = 0;
        int numbersIdx = 0;
        for (int i = 0; i < N; i++) {
            switch (input[i]) {
                case "+":
                case "-":
                case "*":
                    ops[opsIdx++] = input[i].charAt(0);
                    break;
                default:
                    numbers[numbersIdx++] = Integer.parseInt(input[i]);
            }
        }
        br.close();

        /* 작동 */
        if (N == 1) { // 입력 숫자 출력
            bw.write(numbers[0] + "\n");
            bw.close();
            return;
        }
        priorityOp(0, new boolean[N / 2]);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }


    private static void priorityOp(int idx, boolean[] useOp) { // 가능한 연산자 우선순위 구하기
        if (idx == useOp.length) {
            run(useOp);
            return;
        }

        if (idx > 0 && useOp[idx - 1]) { // 바로 이전이 사용되면 현재 연산자 사용 불가
            priorityOp(idx + 1, useOp);
            return;
        }

        useOp[idx] = true;
        priorityOp(idx + 1, useOp);
        useOp[idx] = false;
        priorityOp(idx + 1, useOp);
    }

    private static void run(boolean[] selOps) { // 연산
        Deque<String> selPostfix = new ArrayDeque<>();
        for (int i = 0; i < selOps.length; i++) {
            if (selOps[i]) { // 우선순위인 경우
                int n = numbers[i];
                switch (ops[i]) {
                    case '+':
                        n += numbers[i + 1];
                        break;
                    case '-':
                        n -= numbers[i + 1];
                        break;
                    case '*':
                        n *= numbers[i + 1];
                        break;
                }
                selPostfix.offer(String.valueOf(n));
            } else {
                // 이전 연산자가 우선순위가 아닌 경우, 숫자 먼저 저장 (중위 표현식)
                if (i == 0 || !selOps[i - 1]) {
                    selPostfix.offer(String.valueOf(numbers[i]));
                }
                selPostfix.offer(String.valueOf(ops[i]));
            }
        }
        if (!selOps[selOps.length - 1]) {
            selPostfix.offer(String.valueOf(numbers[selOps.length]));
        }
        cals(selPostfix);
    }

    private static void cals(Deque<String> postfix) {
        Deque<String> op = new ArrayDeque<>(); // 연산자 저장
        before = Integer.parseInt(postfix.poll());
        while (!postfix.isEmpty()) {
            String p = postfix.poll();
            switch (p) {
                case "+":
                case "-":
                case "*":
                    op.offer(p);
                    break;
                default: // 숫자
                    calOp(op, p);
            }
        }
        result = Math.max(result, before);
    }

    private static void calOp(Deque<String> op, String p) { // 저장된 연산자로 연산
        switch (op.poll()) {
            case "+":
                before += Integer.parseInt(p);
                break;
            case "-":
                before -= Integer.parseInt(p);
                break;
            case "*":
                before *= Integer.parseInt(p);
                break;
        }
    }
}
