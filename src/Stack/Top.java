package Stack;

import java.io.*;
import java.util.*;

class SaveTop {
    int index;
    int height;

    public SaveTop(int index, int height) {
        this.index = index;
        this.height = height;
    }
}

public class Top {
    static int N;
    static Stack<SaveTop> top;
    static Stack<Integer> sol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        top = new Stack<>();
        sol = new Stack<>();

        sol.push(0);
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(input[i]);
            while (!top.isEmpty()) { // height보다 작으면 stack에서 제거
                if (height > top.peek().height) {
                    top.pop();
                } else { // 큰 값이 나오면 break
                    break;
                }
            }
            if (top.isEmpty()) {
                bw.write(0 + " ");
            } else {
                bw.write(top.peek().index + " ");
            }

            top.push(new SaveTop((i + 1), height));
        }

        bw.flush();
        br.close();
        br.close();
    }
}