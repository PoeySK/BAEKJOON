package Sort;

import java.io.*;
import java.util.*;

public class NumSort01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] array = new int[N];

        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());
            array[i] = x;
        }

        Arrays.sort(array);

        for(int i=0; i<N; i++){
            System.out.println(array[i]);
        }
    }
}
