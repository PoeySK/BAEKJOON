package Sort;

import java.io.*;
import java.util.*;

public class NumSort02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> array = new ArrayList<>();

        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());
            array.add(x);
        }
        br.close();

        Collections.sort(array);

        for(int i=0; i<N; i++){
            bw.write(array.get(i)+"\n");
        }
        bw.flush();
        bw.close();
    }
}
