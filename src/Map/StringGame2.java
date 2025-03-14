package Map;

import java.io.*;
import java.util.*;

public class StringGame2 {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            String W = st.nextToken();
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            /* 작동 */
            Map<Character, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < W.length(); i++) {
                char c = W.charAt(i);

                if (!map.containsKey(c)) {
                    map.put(c, new ArrayList<>());
                }
                map.get(c).add(i);
            }

            sb = new StringBuilder();
            run(map, K);

            /* 출력 */
            bw.write(sb.toString() + "\n");
        }
        br.close();
        bw.close();
    }

    private static void run(Map<Character, ArrayList<Integer>> map, int K) {
        boolean isPlay = false;
        int min = Integer.MAX_VALUE, max = 0;
        for (Character c : map.keySet()) {
            if (map.get(c).size() >= K) {
                isPlay = true;
                for (int i = 0; i < map.get(c).size() - (K - 1); i++) {
                    min = Math.min(min, map.get(c).get(i + (K - 1)) - map.get(c).get(i));
                    max = Math.max(max, map.get(c).get(i + (K - 1)) - map.get(c).get(i));
                }
            }
        }
        if (isPlay) {
            sb.append((min + 1) + " " + (max + 1));
        } else {
            sb.append(-1);
        }
    }
}
