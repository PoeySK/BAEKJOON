package Think;

import java.io.*;
import java.util.*;

public class B_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int v = N / 2;
        int count = v - 1;
        int a = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i == 0 || i == N - 1) || (j == 0 || j == N - 1)) { // 테두리
                    bw.write("*");
                } else {
                    if(N%2 == 1){
                        if (j == v - count + a || j == v + count - a) {
                            bw.write("*");
                        } else {
                            bw.write(" ");
                        }
                    } else {
                        if (j == v - count + a || j == v + count - a - 1) {
                            bw.write("*");
                        } else {
                            bw.write(" ");
                        }

                    }
                }
            }
            if(i != 0){
                a++;
            }
            bw.write("\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
