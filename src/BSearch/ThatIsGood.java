package BSearch;

import java.io.*;
import java.util.*;

public class ThatIsGood {
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        /* 작동 */
        Arrays.sort(numbers);
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (find(numbers[i], i)) {
                count++;
            }
        }

        return count;
    }

    private static boolean find(int number, int idx) {
        int start = 0;
        int end = N - 1;
        while (start < end) {
            if (start == idx) start++;
            if (end == idx) end--;
            if (start == end) break;

            int sum = numbers[start] + numbers[end];
            if (sum == number) {
                return true;
            } else if (sum > number) {
                end--;
            } else { // sum < number
                start++;
            }
        }
        return false;
    }

}
