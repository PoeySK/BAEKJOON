package String;

import java.io.*;
import java.util.*;

public class PPAP {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        String target = st.nextToken();
        br.close();

        if (target.equals("PPAP") || target.equals("P")) {
            bw.write("PPAP");
            bw.close();
            return;
        }
        boolean result = run(target);

        bw.write(result ? "PPAP\n" : "NP\n");
        bw.close();
    }

    private static boolean run(String target) {
        if (target.charAt(0) == 'A') return false;

        Stack<Character> stack = new Stack<>();
        stack.push(target.charAt(0));
        int idx = 1;
        while (idx < target.length()) {
            char c = target.charAt(idx);
            if (c == 'A') {
                if (stack.size() > 1 && idx + 1 < target.length() && target.charAt(idx + 1) == 'P') {
                    stack.pop();
                    stack.pop();
                    stack.push('P');
                    idx++;
                } else {
                    return false;
                }
            } else {
                stack.push('P');
            }
            idx++;
        }

        return stack.size() == 1;
    }
}
