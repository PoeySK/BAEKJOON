package String;

import java.io.*;
import java.util.*;

public class Constant {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");

        int[] n = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
        int[] next = new int[2];

        for(int i=0; i<2; i++){
            int reverse1 = 0;
            int reverse10 = 0;
            int reverse100 = 0;

            reverse1 = n[i]/100; // 일의 자리
            reverse10 = ((n[i]%100)/10) * 10; // 십의 자리
            reverse100 = (n[i]%10) * 100; // 백의 자리

            next[i] = reverse1 + reverse10 + reverse100;
        }
        int max = next[0];
        for(int i=0; i<2; i++){
            if(max < next[i]){
                max = next[i];
            }
        }

        System.out.println(max);

    }
}
