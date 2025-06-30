package Simulation;

import java.io.*;
import java.util.*;

public class WizardSharkRain {
    static int N, M;
    static int[][] field;
    static ArrayList<int[]> clouds;
    static boolean[][] ableClouds;
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new ArrayList<>();
        ableClouds = new boolean[N][N];
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            run(d, s);
        }
        br.close();

        int result = total();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int d, int s) {
        // 1. 구름 이동
        ArrayList<int[]> prevClouds = new ArrayList<>();
        cloudMove(dy[d], dx[d], s, prevClouds);

        // 2. 구름 칸 물 양 증가
        for (int i = 0; i < prevClouds.size(); i++) {
            int y = prevClouds.get(i)[0];
            int x = prevClouds.get(i)[1];
            field[y][x]++;
        }

        // 3. 구름 물 복사 (대각선 탐방)
        copyWater(prevClouds);

        // 4. 기존 구름 제외한 나머지 영역에서 물의 양이 2 이상인 곳에 구름 생성
        // 4-1. 생성된 구름에서 물의 양 -2
        // 4-2. 기존 구름 삭제
        findNewClouds(prevClouds);
        reduceWater();

        // 5. 종료
    }

    private static void reduceWater() {
        for (int i = 0; i < clouds.size(); i++) {
            int y = clouds.get(i)[0];
            int x = clouds.get(i)[1];

            field[y][x] -= 2;
        }
    }

    private static void findNewClouds(ArrayList<int[]> prevClouds) {
        clouds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j] > 1 && !ableClouds[i][j]) {
                    clouds.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < prevClouds.size(); i++) {
            int y = prevClouds.get(i)[0];
            int x = prevClouds.get(i)[1];

            ableClouds[y][x] = false;
        }
    }

    private static void copyWater(ArrayList<int[]> prevClouds) {
        for (int i = 0; i < prevClouds.size(); i++) {
            int y = prevClouds.get(i)[0];
            int x = prevClouds.get(i)[1];

            int count = 0;
            for (int j = 2; j < 9; j += 2) {
                int ny = y + dy[j];
                int nx = x + dx[j];

                if (isRange(ny, nx) && field[ny][nx] > 0) count++;
            }

            field[y][x] += count;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static void cloudMove(int dy, int dx, int s, ArrayList<int[]> prevClouds) {
        for (int i = 0; i < clouds.size(); i++) {
            int[] cloud = clouds.get(i);
            cloud[0] = cloud[0] + dy * (s % N);
            cloud[1] = cloud[1] + dx * (s % N);

            if (cloud[0] < 0) cloud[0] += N;
            if (cloud[0] >= N) cloud[0] -= N;
            if (cloud[1] < 0) cloud[1] += N;
            if (cloud[1] >= N) cloud[1] -= N;

            ableClouds[cloud[0]][cloud[1]] = true;
            prevClouds.add(cloud);
        }
    }

    private static int total() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += field[i][j];
            }
        }
        return sum;
    }
}
