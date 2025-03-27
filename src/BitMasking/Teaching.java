package BitMasking;

import java.io.*;
import java.util.*;

public class Teaching {
    static int N, K, check, allCheck, result;
    static int[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // a c i n t
        check = 1; // a
        check |= (1 << 2); // c
        check |= (1 << 8); // i
        check |= (1 << 13); // n
        check |= (1 << 19); // t
        K -= 5;
        if (K < 0) {
            bw.write(0 + "\n");
            bw.close();
            return;
        }
        if (K == 21) {
            bw.write(N + "\n");
            bw.close();
            return;
        }

        words = new int[N];
        allCheck = check;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();

            for (int j = 4; j < input.length() - 4; j++) {
                int c = input.charAt(j) - 'a';
                words[i] |= (1 << c);
                allCheck |= (1 << c);
            }
        }

        if (allCheck == check) { // check 외에는 배워야 할 알파벳이 없는 경우
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if ((words[i] & check) == words[i]) {
                    cnt++;
                }
            }
            bw.write(cnt + "\n");
            bw.close();
            return;
        }

        br.close();
        run(0, 0);

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int idx, int count) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if ((words[i] & check) == words[i]) {
                cnt++;
            }
        }
        result = Math.max(result, cnt);
        
        if (count == K) {
            return;
        }

        for (int i = idx; i < 26; i++) {
            if ((allCheck & (1 << i)) == 0) continue; // 단어들 중 없는 알파벳

            if ((check & (1 << i)) == 0) {
                check |= (1 << i);
                run(i + 1, count + 1);
                check &= ~(1 << i);
            }
        }
    }
}
