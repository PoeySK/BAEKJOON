package Greedy;

import java.io.*;
import java.util.*;

public class AAndB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] S = br.readLine().split("");
        String[] T = br.readLine().split("");
        ArrayList<Character> s = new ArrayList<>();
        ArrayList<Character> t = new ArrayList<>();
        int len = T.length;
        for (int i = 0; i < S.length; i++) {
            s.add(S[i].charAt(0));
        }
        for (int i = 0; i < len; i++) {
            t.add(T[i].charAt(0));
        }
        boolean isCheck = true;
        while (!s.equals(t)) {
            if (t.isEmpty()) { // 바꾸지 못하는 경우
                isCheck = false;
                break;
            }
            int lastIdx = t.size() - 1;
            if (t.get(lastIdx) == 'B') {
                t.remove(lastIdx);
                Collections.reverse(t);
            } else {
                t.remove(lastIdx);
            }
        }

        if (isCheck) {
            bw.write("1");
        } else {
            bw.write("0");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
