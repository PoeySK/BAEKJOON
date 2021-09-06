package Stack;

import java.io.*;
import java.util.*;

public class ObigNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] NGE = br.readLine().split(" ");

        Stack<Integer> basic = new Stack<>();
        Stack<Integer> change = new Stack<>();
        Stack<Integer> Ostack = new Stack<>();

        for (int i = 0; i < N; i++) {
            basic.push(Integer.parseInt(NGE[i]));
        }

        int max = -2147483648;
        for (int i = 0; i < N; i++) {
            if (Ostack.isEmpty()) {
                Ostack.push(-1);
                change.push(basic.pop());
                max = change.peek();
            }
            else if (basic.peek() < change.peek()) {
                Ostack.push(change.peek());
                change.push(basic.pop());

                if (max < Ostack.peek()) {
                    max = Ostack.peek();
                }
            }
            else {
                if (basic.peek() < Ostack.peek()) {
                    Ostack.push(Ostack.peek());
                    if (max < Ostack.peek()) {
                        max = Ostack.peek();
                    }
                }

                else if (basic.peek() < max) {
                    Ostack.push(max);
                }

                else {
                    Ostack.push(-1);
                }

                change.push(basic.pop());
            }
        }
        for (int i = 0; i < N; i++) {
            if (!Ostack.isEmpty())
                bw.write(Ostack.pop() + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}