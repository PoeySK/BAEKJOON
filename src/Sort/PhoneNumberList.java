package Sort;

import java.io.*;
import java.util.*;

public class PhoneNumberList {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] list = new int[n];
            for (int j = 0; j < n; j++) {
                int number = Integer.parseInt(br.readLine());
                list[j] = number;
            }
            if (isCheck(list)) {
                bw.write("YES");
            } else {
                bw.write("NO");
            }

        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean isCheck(int[] list) {
        Arrays.sort(list);
        for (int i = 0; i < list.length; i++) {
        }
        return true;
    }
}