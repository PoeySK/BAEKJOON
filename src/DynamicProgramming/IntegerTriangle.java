package DynamicProgramming;

import java.io.*;

public class IntegerTriangle {
    static int N;
    static int[][] triangle, table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        triangle = new int[N][N]; // 5x5 입력 배열
        table = new int[N][N]; // 5x5 솔루션 배열

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                triangle[i][j] = Integer.parseInt(input[j]); // j 인덱스로 인해 계단식 배열로 입력됨.
            }
        }

        bw.write(findSum() + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static int findSum() {
        table[0][0] = triangle[0][0]; // root값은 고정

        for (int i = 1; i < N; i++) { // level 0은 root
            for (int j = 0; j < N; j++) {
                if (j != 0) { // 자신의 인덱스 위치에서 오는 경로 두 가지 중 큰 값을 골라서 더하기.
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - 1]) + triangle[i][j];
                } else { // j 인덱스가 0인 경우 다가오는 경로는 한 가지로 예외 처리
                    table[i][j] = table[i - 1][j] + triangle[i][j];
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, table[N - 1][i]); // leaf노드에서 max값 찾기
        }

        return result;
    }
}