package Stack;

import java.io.*;
import java.util.*;

public class StringBoom {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        String input = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String target = st.nextToken();

        /* 작동 */
        String result = run(input, target);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static String run(String input, String target) {
        int len = target.length();
        char last = target.charAt(len - 1);
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            dq.push(input.charAt(i));
            if (input.charAt(i) == last) {
                Deque<Character> q = new ArrayDeque<>();
                for (int j = 0; j < len; j++) {
                    if (!dq.isEmpty() && target.charAt(len - 1 - j) == dq.peek()) {
                        q.offer(dq.pop());
                    } else {
                        break;
                    }
                }
                if (q.size() != len) {
                    while (!q.isEmpty()) {
                        dq.push(q.pollLast());
                    }
                }
            } // last if end
        } // i for end

        if (dq.isEmpty()) {
            return "FRULA";
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append((dq.pollLast()));
        }

        return sb.toString();
    }
}
