package BSearch;

import java.io.*;
import java.util.*;

public class KthNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Set<Long> b = new TreeSet<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                b.add((long)i*j);
            }
        }

        int k = Integer.parseInt(br.readLine());
        int index = 0;
        Iterator<Long> iter = b.iterator();
        while(iter.hasNext()){
            index++;
            if(index == k) {
                System.out.println(iter.next());
                break;
            }
            iter.next();
        }

    }
}