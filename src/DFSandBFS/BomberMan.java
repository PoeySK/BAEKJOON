package DFSandBFS;

import java.io.*;
import java.util.*;

public class BomberMan {
    static int R, C, N;
    static String[][] BoomFirst, BoomSecond;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());


        String[][] field = new String[R][C];
        for (int i = 0; i < R; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                field[i][j] = input[j];
            }
        }
        br.close();

        /* 작동 */
        if (N == 1) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    bw.write(field[i][j]);
                }
                bw.write("\n");
            }
            bw.flush();
            bw.close();
            return;
        }
        if (N % 2 == 0) { // 짝수는 모든 공간에 폭탄 설치
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    bw.write("O");
                }
                bw.write("\n");
            }
            bw.flush();
            bw.close();
            return;
        }

        BoomFirst = new String[R][C];
        BoomSecond = new String[R][C];
        firstBoom(field);

        /* 출력 */
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if ((N - 1) % 4 == 0) {
                    bw.write(BoomSecond[i][j]);
                } else {
                    bw.write(BoomFirst[i][j]);
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void firstBoom(String[][] pre) {
        ArrayList<Node> save = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (pre[i][j].equals("O")) {
                    save.add(new Node(i, j));
                }
            }
        }
        if (save.isEmpty()) { // 터트릴 폭탄이 없음.
            for (int i = 0; i < R; i++) {
                Arrays.fill(BoomFirst[i], "."); // 터트릴 폭탄이 없으니 모두 "."
                Arrays.fill(BoomSecond[i], ".");
            }
            return;
        }

        for (int i = 0; i < R; i++) {
            Arrays.fill(BoomFirst[i], "O"); // 모두 폭탄으로 설치해놓기
        }

        // field의 "O" 위치 였으면 해당 위치 4방으로 "." 찍기
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for (int i = 0; i < save.size(); i++) {
            int y = save.get(i).y;
            int x = save.get(i).x;
            BoomFirst[y][x] = ".";

            for (int j = 0; j < 4; j++) {
                int ny = y + dy[j];
                int nx = x + dx[j];

                if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                    BoomFirst[ny][nx] = ".";
                }
            }
        }

        secondBoom();
    }

    private static void secondBoom() {
        ArrayList<Node> save = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (BoomFirst[i][j].equals("O")) {
                    save.add(new Node(i, j));
                }
            }
        }
        if (save.isEmpty()) { // 터트릴 폭탄이 없음.
            for (int i = 0; i < R; i++) {
                Arrays.fill(BoomFirst[i], "."); // 터트릴 폭탄이 없으니 모두 "."
                Arrays.fill(BoomSecond[i], ".");
            }
            return;
        }

        for (int i = 0; i < R; i++) {
            Arrays.fill(BoomSecond[i], "O"); // 모두 폭탄으로 설치해놓기
        }

        // field의 "O" 위치 였으면 해당 위치 4방으로 "." 찍기
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for (int i = 0; i < save.size(); i++) {
            int y = save.get(i).y;
            int x = save.get(i).x;
            BoomSecond[y][x] = ".";

            for (int j = 0; j < 4; j++) {
                int ny = y + dy[j];
                int nx = x + dx[j];

                if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                    BoomSecond[ny][nx] = ".";
                }
            }
        }

    }

    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
/*
1 -> 초기

3 -> 후기

5 -> 초기

7 -> 후기

9 -> 초기

11 -> 후기

1 5 9 13 16 -> (N - 1) % 4 == 0 -> 초기
3 7 11 15 -> (N + 1) % 4 == 0 ->후기

2 2 5
O.
.O

[result]
..
..

 */