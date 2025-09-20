package Simulation;

import java.io.*;
import java.util.*;

public class TreePersonalFinance {
    static int N, M, K;
    static int[][] A, nutrient;
    static ArrayDeque<Integer>[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nutrient = new int[N][N];
        A = new int[N][N];
        field = new ArrayDeque[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nutrient[i][j] = 5;
                field[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            field[y][x].add(z);
        }
        br.close();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j].isEmpty()) continue;

                ArrayList<Integer> arr = new ArrayList<>(field[i][j]);
                Collections.sort(arr);
                field[i][j].clear();
                for (int a : arr) {
                    field[i][j].offer(a);
                }
            }
        }
        run();

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count += field[i][j].size();
            }
        }

        bw.write(count + "\n");
        bw.close();
    }

    private static void run() {
        while (K-- > 0) {
            springAndSummer();
            fall();
            winter();
        }
    }

    private static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ArrayDeque<Integer> save = new ArrayDeque<>();

                while (!field[i][j].isEmpty()) {
                    int age = field[i][j].peekFirst();
                    if (nutrient[i][j] >= age) {
                        nutrient[i][j] -= age;
                        field[i][j].pollFirst();
                        save.addLast(age + 1);
                    } else {
                        break;
                    }
                }

                while (!field[i][j].isEmpty()) {
                    nutrient[i][j] += field[i][j].pollFirst() / 2;
                }
                field[i][j] = save;
            }
        }
    }

    private static void fall() {
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j].isEmpty()) continue;

                for (int age : field[i][j]) {
                    if (age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int ny = i + dy[d];
                            int nx = j + dx[d];
                            if (isRange(ny, nx)) {
                                field[ny][nx].addFirst(1);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrient[i][j] += A[i][j];
            }
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
