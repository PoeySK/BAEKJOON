package etc;

import java.io.*;
import java.util.*;

public class FastAPlusB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        while(N-->0){
            String[] s = br.readLine().split(" ");
            bw.write(Integer.parseInt(s[0]) + Integer.parseInt(s[1]) + "\n");
        }
        bw.flush();
    }
}