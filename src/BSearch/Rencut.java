package BSearch;

import java.io.*;
import java.util.*;

public class Rencut {
    static int K,N;
    static int[] ren;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        K = Integer.parseInt(s[0]);
        N = Integer.parseInt(s[1]);

        ren = new int[K];
        int max = 0;
        for(int i=0; i<K; i++){
            ren[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, ren[i]);
        }

        Arrays.sort(ren);

        System.out.println(getRen(max));
    }

    static long getRen(int max){
        long low = 1;
        long high = max;
        long mid = 0;
        long getren = 0;

        while(low <= high){
            mid = (low + high) / 2;

            int check = 0;
            for(int i =0; i<K; i++){
                check += ren[i] / mid;
            }
            if(check >= N){
                getren = Math.max(mid, getren);
                low = mid + 1;
            }else if(check < N){
                high = mid - 1;
            }

        }
        return getren;
    }

}
