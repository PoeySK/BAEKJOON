package Math;

import java.io.*;
import java.util.*;

public class Turret {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            /* 작동 */
            int rlt = run(x1, y1, r1, x2, y2, r2);

            /* 출력 */
            bw.write(rlt + "\n");
        }
        bw.close();
    }

    private static int run(int x1, int y1, int r1, int x2, int y2, int r2) {
        int dist = (int) Math.pow(x1 - x2, 2) + (int) Math.pow(y1 - y2, 2);
        int r = (int) Math.pow(r1 + r2, 2);

        if (x1 == x2 && y1 == y2 && r1 == r2) {
            return -1;
        }

        if (dist == r) {
            return 1;
        }
        if (dist > r) {
            return 0;
        }

        if (dist == (int) Math.pow(r2 - r1, 2)) return 1;
        if (dist > (int) Math.pow(r2 - r1, 2)) return 2;
        return 0;
    }
}
