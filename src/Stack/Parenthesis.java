package Stack;

import java.io.*;
import java.util.*;

public class Parenthesis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            Stack<String> stack_char = new Stack<String>();
            boolean ischeck = true;
            String[] str = br.readLine().split("");

            for (int j = 0; j < str.length; j++) {
                if (str[j].equals("(")) {
                    stack_char.push(str[j]);
                } else if (str[j].equals(")")) {
                    if (!stack_char.isEmpty())
                        stack_char.pop();
                    else {
                        ischeck = false;
                        break;
                    }
                }
            }

            if (!stack_char.isEmpty()) {
                ischeck = false;
            }

            if (ischeck) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
    }
}