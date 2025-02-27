package Stack;

import java.io.*;
import java.util.*;

public class MakeBig {
    static int N, K;
    static ArrayDeque<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String[] input = st.nextToken().split("");
        br.close();

        /* 작동 */
        int printSize = N - K;
        stack = new ArrayDeque<>();
        run(input);

        /* 출력 */
        StringBuilder sb = new StringBuilder();
        while(printSize-- > 0) {
            sb.append(stack.pollLast());
        }
        bw.write(sb + "\n");
        bw.close();
    }

    private static void run(String[] input) {
        stack.push(Integer.parseInt(input[0]));
        for (int i = 1; i < N; i++) {
            int next = Integer.parseInt(input[i]);
            while (K > 0 && !stack.isEmpty() && stack.peek() < next) {
                K--;
                stack.pop();
            }
            stack.push(next);
        }
    }
}
