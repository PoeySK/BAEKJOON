package DynamicProgramming;

import java.io.*;
import java.util.*;

public class ZeroOneTile {
    static int N;
    static int[] sol;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        sol = new int[N + 2];
        bw.write(tile(N)+ "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static int tile(int number) {
        if(sol[number] > 0) { // 이미 값이 존재할 경우 함수를 바로 종료
            return sol[number];
        }
        if(number == 0) {
            return 0;
        }
        if(number == 1){
            sol[number] = 1;
            return 1;
        }
        if(number == 2) {
            sol[number] = 2;
            return 2;
        }
        sol[number] = (tile(number - 1) + tile(number - 2)) % 15746; // 값이 커질 수 있기 때문에 미리 값을 나눠줌.
        return sol[number];
    }
}
