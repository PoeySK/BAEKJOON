package Greedy;

import java.io.*;
import java.util.*;

public class Ship {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 크레인 개수
        String[] sn = br.readLine().split(" "); // 크레인 무게

        int M = Integer.parseInt(br.readLine()); // 화물 개수
        String[] sm = br.readLine().split(" "); // 화물 무게

        int[] crane = new int[N];
        int[] cargo = new int[M];

        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(sn[i]);
        }

        for (int i = 0; i < M; i++) {
            cargo[i] = Integer.parseInt(sm[i]);
        }

        Arrays.sort(crane);
        Arrays.sort(cargo);

        int index = 0;
        int count = 1;
        boolean check = true;

        if(crane[crane.length - 1] < cargo[cargo.length - 1]){
            bw.write("-1");
        }

        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if(crane[j] >= cargo[i]){
                    break;
                }
            }
        }


        bw.flush();
        br.close();
        bw.close();
    }
}
/*
1 2
1 1 2 2


 */