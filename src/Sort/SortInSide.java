package Sort;

import java.io.*;
import java.util.*;

public class SortInSide {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split("");

        ArrayList<Integer> array = new ArrayList<>();

        for(int i=0; i<s.length; i++){
            array.add(Integer.parseInt(s[i]));
        }
        Collections.sort(array);
        Collections.reverse(array);
        for(int i=0; i<array.size(); i++)
            System.out.print(array.get(i));
    }
}
