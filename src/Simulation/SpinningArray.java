package Simulation;

import java.io.*;
import java.util.*;

public class SpinningArray {
    static int N, M, K, result = Integer.MAX_VALUE;
    static int[][] range;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] field = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        range = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            range[i] = new int[]{r, c, s};
        }
        br.close();

        /* 작동 */
        per(0, new int[K], new boolean[K], field);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int[][] origin, int[] isSel) { // 경우의 수 적용
        for (int i = 0; i < K; i++) {
            int idx = isSel[i];
            int topY = range[idx][0] - range[idx][2] - 1;
            int topX = range[idx][1] - range[idx][2] - 1;
            int bottomY = range[idx][0] + range[idx][2] - 1;
            int bottomX = range[idx][1] + range[idx][2] - 1;
            for (int j = 0; j < range[idx][2]; j++) { // 네모 좁히기
                spin(origin, topY + j, topX + j, bottomY - j, bottomX - j);
            }
        }

        // row 값 구하기
        for (int i = 0; i < N; i++) {
            int total = 0;
            for (int j = 0; j < M; j++) {
                total += origin[i][j];
            }
            result = Math.min(result, total);
        }

    }

    private static void spin(int[][] copyField, int topY, int topX, int bottomY, int bottomX) { // 배열 돌리기
        // 반대로 탐색 (주석에서 이동은 이론상 이동)
        int i = topY;
        int j = topX;
        int temp = copyField[i + 1][j]; // 마지막에 넣을 값 저장
        while (i != bottomY) { // 위쪽 이동
            i++;
            if (i == bottomY) {
                copyField[i][j] = copyField[i][j + 1];
            } else {
                copyField[i][j] = copyField[i + 1][j];
            }
        }
        while (j != bottomX) { // 왼쪽 이동
            j++;
            if (j == bottomX) {
                copyField[i][j] = copyField[i - 1][j];
            } else {
                copyField[i][j] = copyField[i][j + 1];
            }
        }
        while (i != topY) { // 아래쪽 이동
            i--;
            if (i == topY) {
                copyField[i][j] = copyField[i][j - 1];
            } else {
                copyField[i][j] = copyField[i - 1][j];
            }
        }
        while (j != topX) { // 오른쪽 이동
            j--;
            if (j == topX) {
                copyField[i][j] = temp;
            } else {
                copyField[i][j] = copyField[i][j - 1];
            }
        }
    }

    private static void per(int idx, int[] sel, boolean[] isVisit, int[][] field) { // 순열, 모든 돌리기 순서 정하기
        if (idx == sel.length) {
            int[] copy = sel.clone();
            int[][] copyField = new int[N][M];
            for (int i = 0; i < N; i++) {
                copyField[i] = field[i].clone();
            }
            run(copyField, copy);
            return;
        }

        for (int i = 0; i < sel.length; i++) {
            if (!isVisit[i]) {
                isVisit[i] = true;
                sel[idx] = i;
                per(idx + 1, sel, isVisit, field);
                isVisit[i] = false;
            }
        }
    }
}
