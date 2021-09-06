package DynamicProgramming;

import java.io.*;

public class ChangeToOne {
    static int N;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        count = new int[N + 1];

        /*
         함수를 사용해 재귀로하면 시간복잡도가 더 걸려서 반복문을 사용

         3가지 경우를 모두 검사해서 그 때 숫자의 카운트가 더 적은 것을 사용한다.
         */

        for (int i = 2; i <= N; i++) {
            count[i] = count[i - 1] + 1; // -1의 경우를 미리 구해서 값에 넣어준다.
            if (i % 3 == 0) {
                count[i] = Math.min(count[i], count[i / 3] + 1); // 위에서 이미 값이 저장되어 있는데 그것과 비교를 한다.
            }
            if (i % 2 == 0) {
                count[i] = Math.min(count[i], count[i / 2] + 1);
            }
        }

        bw.write(count[N] + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}