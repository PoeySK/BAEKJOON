package BSearch;

import java.io.*;

public class BestLong2 {
    static int[] suyeol;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        suyeol = new int[N];

        for(int i =0; i< N; i++){
            suyeol[i] = Integer.parseInt(br.readLine());
        }
    }
}
