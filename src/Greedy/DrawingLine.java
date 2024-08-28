package Greedy;

import java.io.*;
import java.util.*;

public class DrawingLine {
    static int N, result;
    static ArrayList<Save> save;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        save = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            save.add(new Save(x, y));
        }
        br.close();

        /* 작동 */
        Collections.sort(save);
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int start = save.get(0).x;
        int end = save.get(0).y;
        for (int i = 1; i < N; i++) {
            if (save.get(i).x <= end) {
                end = Math.max(end, save.get(i).y);
            } else { // x > end
                result += end - start;
                start = save.get(i).x;
                end = save.get(i).y;
            }
        }
        result += end - start;
    }

    static class Save implements Comparable<Save> {
        int x, y;

        Save(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Save o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }
}
