package BSearch;

import java.io.*;
import java.util.*;

public class NumberCard2 {
    static int N, M;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String num = st.nextToken();
            if (map.containsKey(num)) {
                map.replace(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            String num = st.nextToken();
            if (map.containsKey(num)) {
                bw.write(map.get(num) + " ");
            } else {
                bw.write("0 ");
            }
        }
        br.close();
        bw.close();
    }
}
