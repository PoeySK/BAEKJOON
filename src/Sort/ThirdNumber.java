package Sort;

import java.util.*;
import java.io.*;

public class ThirdNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            String[] input = br.readLine().split(" ");
            int[] num = new int[input.length];

            for(int i=0; i<num.length; i++) {
                num[i] = Integer.parseInt(input[i]);
            }

            Arrays.sort(num);

            bw.write(num[num.length - 3] + "\n"); // 정렬 후 뒤에서 3번째 값
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
