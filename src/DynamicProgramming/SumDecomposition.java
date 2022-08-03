package DynamicProgramming;

import java.io.*;

public class SumDecomposition {
    static int N, K;
    static long[][] table;
    static long div = 1000000000; // 나눌 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        table = new long[K + 1][N + 1];

        calculate();

        bw.write(table[K][N] + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

    public static void calculate() {
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == 0) { // 초기값은 0이므로 i를 1부터 시작해도 됨.
                    // K == 0 => 사용할 숫자가 0개면 존재하지 않음.
                    table[i][j] = 0;
                } else if (j == 0 || i == 1) {
                    // N == 1 => 더해서 0으로 만드는 방식
                    // K == 1 => 1개의 숫자만 사용하여 N을 만드는 방식
                    table[i][j] = 1;
                } else {
                    /* array[K][N]은 K(사용할 숫자의 개수)로 N(현재 만들어낼 값)을 구할 수 있는 방법의 개수를 저장한다.
                     *
                     * ex) array[2][5] / K = 2, N = 5 / 5를 숫자 2개를 이용해 만들 수 있는 방식의 개수를 저장한다.
                     *
                     * array[x][y]는 array[x - 1][0] + ... + array[x - 1][y]로 정해진다. (중복 조합)
                     *
                     * 즉, array[x][0] + ... + array[x][y - 1]이 저장되어 있는 array[x][y - 1]과
                     * array[x - 1][y]를 더하는 것이 더 간단하다.
                     */
                    table[i][j] = (table[i - 1][j] + table[i][j - 1]) % div; // 숫자가 매우 커질수도 있으니 div로 나눠줌.
                }
            }
        }

    }
}
