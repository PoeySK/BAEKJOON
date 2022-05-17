package GraphTheory;

import java.io.*;
import java.util.*;

public class CowArt {
    static int N;
    static char[][] array;
    static boolean[][] check;
    // 상, 하, 좌 우 좌표 이동
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {-1, 1, 0, 0};

    static class Coordinate {
        int y;
        int x;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        array = new char[N][N];
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                char c = s[j].charAt(0);
                array[i][j] = c;
            }
        }
        check = new boolean[N][N];

        // nonCow
        int resultNon = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                /*
                 * 모든 좌표를 검사하여 bfs가 일어난 횟수를 결과로 출력.
                 * 같은 색으로 군집된 색은 check배열로 확인하여 거름.
                 */
                if (!check[i][j]) {
                    nonCowView(i, j, array[i][j]);
                    resultNon++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(check[i], false); // 초기화
        }
        // cow
        int resultCow = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                /*
                 * nonCow에서 한 것과 구조는 비슷하지만,
                 * R과 G를 같은 색으로 취급해줌.
                 */
                if (!check[i][j]) {
                    check[i][j] = true;
                    cowView(i, j, array[i][j]);
                    resultCow++;
                }
            }
        }
        bw.write(resultNon + " " + resultCow);
        bw.flush();
        br.close();
        bw.close();
    }

    public static void cowView(int row, int col, char color) {
        Queue<Coordinate> cowQ = new LinkedList<>();
        cowQ.offer(new Coordinate(row, col));
        if (color == 'R' || color == 'G') { // R과 G는 같은 것으로 봐야하기 때문에 같은 값으로 변경
            color = 'T';
        }
        while (!cowQ.isEmpty()) {
            Coordinate coo = cowQ.poll();

            for (int i = 0; i < 4; i++) {
                int ny = coo.y + my[i];
                int nx = coo.x + mx[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    /*
                     * nonCowView부터 실행하기 때문에 array배열을 직접적으로 변경해도 무관함.
                     */
                    if (array[ny][nx] == 'R' || array[ny][nx] == 'G') {
                        array[ny][nx] = 'T';
                    }
                    if (color == array[ny][nx] && !check[ny][nx]) {
                        cowQ.offer(new Coordinate(ny, nx));
                        check[ny][nx] = true;
                    }
                }
            }
        }
    }

    public static void nonCowView(int row, int col, char color) {
        Queue<Coordinate> nonCowQ = new LinkedList<>();
        nonCowQ.offer(new Coordinate(row, col));

        while (!nonCowQ.isEmpty()) {
            Coordinate coo = nonCowQ.poll();

            for (int i = 0; i < 4; i++) {
                int ny = coo.y + my[i];
                int nx = coo.x + mx[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) { // index 범위 검사
                    /*
                     * 현재 color와 탐색하는 color를 비교하고 그 값을 방문했는지 확인.
                     */
                    if (color == array[ny][nx] && !check[ny][nx]) {
                        nonCowQ.offer(new Coordinate(ny, nx));
                        check[ny][nx] = true;
                    }
                }
            }
        }
    }
}