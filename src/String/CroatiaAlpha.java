package String;

import java.io.*;
import java.util.*;

public class CroatiaAlpha {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<String> stack = new Stack<>(); // 마지막에 들어간 단어를 확인하기 위해 스택을 사용

        String[] s = br.readLine().split("");

        for (int i = 0; i < s.length; i++) {
            String input = s[i]; // input 값 설정.

            if (!stack.isEmpty() && input.equals("=")) { // 변경되는 단어 검사.
                if (stack.peek().equals("c") || stack.peek().equals("s")) {
                    stack.pop();
                    stack.push("element"); // 변경되는 단어가 검출되면 element를 stack에 넣음.
                } else if (stack.peek().equals("z")) {
                    stack.pop();
                    if(!stack.isEmpty() && stack.peek().equals("d")){
                        stack.pop();
                    }
                    stack.push("element");
                }
            } else if (!stack.isEmpty() && input.equals("-")) {
                if (stack.peek().equals("c") || stack.peek().equals("d")) {
                    stack.pop();
                    stack.push("element");
                }
            } else if (!stack.isEmpty() && input.equals("j")){
                if(stack.peek().equals("l") || stack.peek().equals("n")){
                    stack.pop();
                    stack.push("element");
                } else {
                    stack.push(input); // j가 검사로 쓸 수 있으나 단어로도 쓰일 수 있음.
                }
            }
            else {
                stack.push(input); // 그 외의 값은 stack에 넣음.
            }

        }

        bw.write(stack.size() + "\n");
        bw.flush();
    }
}