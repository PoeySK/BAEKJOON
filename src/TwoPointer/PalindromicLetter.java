package TwoPointer;

import java.io.*;
import java.util.*;

public class PalindromicLetter {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            bw.write(isPalindrome(input, 0, input.length() - 1, false) + "\n");
        }
        br.close();
        bw.close();
    }

    private static int isPalindrome(String letter, int left, int right, boolean isCheck) {
        while (left < right) {
            if (letter.charAt(left) != letter.charAt(right)) {
                if (isCheck) return 2;

                if (isPalindrome(letter, left + 1, right, true) == 0) {
                    return 1;
                }

                if (isPalindrome(letter, left, right - 1, true) == 0) {
                    return 1;
                }

                return 2;
            } else {
                left++;
                right--;
            }
        }
        return 0;
    }
}
