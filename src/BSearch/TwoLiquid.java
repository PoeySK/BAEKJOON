package BSearch;

import java.io.*;
import java.util.*;

public class TwoLiquid {
    static int N;
    static long[] Acid, Alkaline;
    static int zero;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String[] liquid = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            if (Long.parseLong(liquid[i]) > 0) {
                Acid[i] = Long.parseLong(liquid[i]);
            }
            else if (Long.parseLong(liquid[i]) < 0){
                Alkaline[i] = Long.parseLong(liquid[i]);
            } else {
                zero = Integer.parseInt(liquid[i]);
            }
        }
        Arrays.sort(Acid);
        Arrays.sort(Alkaline);
    }

    static void getSpecial(){

    }


}
