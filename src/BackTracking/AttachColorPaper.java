package BackTracking;

import java.io.*;
import java.util.*;

public class AttachColorPaper {
    static int result = Integer.MAX_VALUE;
    static int[] color = {0, 5, 5, 5, 5, 5};
    static int[][] paper = new int[10][10];
    static ArrayList<Point> save = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                if (paper[i][j] == 1) { // 붙여할 위치
                    save.add(new Point(i, j));
                }
            }
        }
        br.close();

        /* 작동 */
        run(0, 0);

        /* 출력 */
        bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n"); // MAX_VALUE -> 색종이를 다 못붙인 경우
        bw.close();
    }

    private static void run(int idx, int count) {
        if (idx == save.size()) { // 필요한 모든 위치에 붙인 경우
            result = Math.min(result, count);
            return;
        }

        if (result <= count) { // 앞으로 붙어야 할 개수가 지금 최소 개수보다 많거나 같은 경우
            return;
        }

        Point now = save.get(idx);
        if (paper[now.y][now.x] == 0) { // 이미 붙인 경우
            run(idx + 1, count);
            return;
        }

        for (int i = 5; i > 0 && color[i] > 0; i--) { // 가능한 색종이로 개수에 맞게 붙이기
            if (isAttach(now.y, now.x, i)) {
                useColorPaper(now.y, now.x, i, 0);
                color[i]--;
                run(idx + 1, count + 1);
                color[i]++;
                useColorPaper(now.y, now.x, i, 1);
            }
        }
    }

    private static void useColorPaper(int y, int x, int level, int isUse) { // isUse: 0->사용, 1->사용안함
        for (int i = y; i < y + level; i++) {
            for (int j = x; j < x + level; j++) {
                paper[i][j] = isUse;
            }
        }
    }

    private static boolean isAttach(int y, int x, int level) { // 네모 범위로 확인
        for (int i = y; i < y + level; i++) {
            for (int j = x; j < x + level; j++) {
                if (i >= 10 || j >= 10 || paper[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
