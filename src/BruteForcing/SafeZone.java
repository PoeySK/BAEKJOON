package BruteForcing;

import java.io.*;
import java.util.*;

public class SafeZone {
    static int N;
    static int[] nx = {0, 0, -1, 1};
    static int[] ny = {-1, 1, 0, 0};

    static class Save {
        int i;
        int j;

        public Save(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int[][] zone = new int[N][N];

        // Input
        int max = 0; // 건물 높이의 최대값
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int ipt = Integer.parseInt(input[j]);
                zone[i][j] = ipt;
                max = Math.max(max, ipt);
            }
        }

        int result = 0;
        for (int i = 0; i <= max; i++) {
            int[][] area = full(zone, i);
            int val = findSafeZone(area);
            result = Math.max(result, val);
        }

        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static int[][] full(int[][] zone, int water) { // 물 채우기
        int[][] area = new int[N][N];
        for (int i = 0; i < N; i++) { // deep copy => 얕은 복사는 주소값을 복사하므로 area를 변경하면 zone도 같이 변함.
            area[i] = zone[i].clone();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (zone[i][j] <= water) {
                    area[i][j] = 0;
                }
            }
        }
        return area;
    }

    public static int findSafeZone(int[][] area) { // 안전한 구역 찾기
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (area[i][j] != 0) {
                    dfs(i, j, area);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(int i, int j, int[][] area) {
        Queue<Save> queue = new LinkedList<>();
        queue.offer(new Save(i, j));
        area[i][j] = 0;
        while (!queue.isEmpty()) {
            Save q = queue.poll();
            for (int k = 0; k < 4; k++) {
                int mx = q.j + nx[k];
                int my = q.i + ny[k];

                if (0 <= my && my < N && 0 <= mx && mx < N) {
                    if (area[my][mx] != 0) {
                        area[my][mx] = 0;
                        queue.offer(new Save(my, mx));
                    }
                }
            }
        }
    }
}
