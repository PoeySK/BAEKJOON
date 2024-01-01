package Sort;

import java.io.*;
import java.util.*;

public class SumOfTwoNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int X = Integer.parseInt(br.readLine());

        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(num); // 오름차순 정렬

        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = N - 1; j > i; j--) {
                int FrontPoint = num[i];
                int LastPoint = num[j];
                if (FrontPoint + LastPoint == X) {
                    count++;
                    break;
                }
            }
        }
        bw.write(count + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
