package List;

import java.io.*;
import java.util.*;

public class StringBoom {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedList<String> a = new LinkedList<>();

        String[] input = br.readLine().split("");

        for (int i = 0; i < input.length; i++) {
            a.offer(input[i]);
        }

        String[] delete = br.readLine().split("");
        for (int i = 0; i < delete.length; i++) {

        }

        if (a.isEmpty()) {
            bw.write("FRULA" + "\n");
        } else {
            for (int i = 0; i < a.size(); i++) {
                bw.write(a.get(i) + "");
            }
        }
        bw.flush();

    }
}