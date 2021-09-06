package Math;

import java.io.*;
import java.util.*;

public class BreakEvenPoint {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");

        int A = Integer.parseInt(s[0]);
        int B = Integer.parseInt(s[1]);
        int C = Integer.parseInt(s[2]);

        int count = 0;
        int price = C - B;
        int sales = 0;
        if (price > 0) {
            while (sales <= A) {
                sales += price;
                count++;
            }
            bw.write(count + "\n");
        } else {
            bw.write("-1");
        }

        bw.flush();
        br.close();
        bw.close();

    }
}