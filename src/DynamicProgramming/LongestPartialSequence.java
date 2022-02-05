package DynamicProgramming;

import java.io.*;

public class LongestPartialSequence {
    static int N;
    static int[] array;
    static int[] sol; // solution table

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        array = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        sol = new int[N];
        sol[0] = 1; // 초기값

        for (int i = 1; i < N; i++) {
            sol[i] = 1;
            for (int j = 0; j < i; j++) {
                /*
                 '1 2 5 6 3 4 5 6'입력인 경우
                 단순'1 2 5 6'이 아닌 '1 2 3 4 5 6'처럼 더 큰 수열을 만들 수 있도록 함.
                 array를 처음부터 검사하여 중간 값을 제거했을 경우 더 큰 수열이 나오는지 판단함.
                 */
                if (array[j] < array[i] && sol[i] <= sol[j]) {
                    sol[i] = sol[j] + 1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, sol[i]);
        }

        bw.write(result + "\n");
        br.close();
        bw.close();
    }
}