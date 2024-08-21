package Simulation;

import java.io.*;
import java.util.*;

public class Game2048 {
    static int N, result;
    static int[][] board;
    static char[] move = {'u', 'd', 'l', 'r'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        permutation(0, new char[5]);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void permutation(int idx, char[] sel) { // 이동 중복 순열
        if (idx == sel.length) {
            run(sel);
            return;
        }

        for (int i = 0; i < 4; i++) { // 5번 이동
            sel[idx] = move[i];
            permutation(idx + 1, sel);
        }
    }

    private static void run(char[] sel) {
        int[][] field = new int[N][N];
        for (int i = 0; i < N; i++) {
            field[i] = Arrays.copyOf(board[i], N);
        }
        for (int i = 0; i < 5; i++) {
            char dir = sel[i];
            switch (dir) {
                case 'u':
                    up(field);
                    break;
                case 'd':
                    down(field);
                    break;
                case 'l':
                    left(field);
                    break;
                case 'r':
                    right(field);
                    break;
            }
        }

        result = Math.max(result, bigBlock(field));
    }

    private static int bigBlock(int[][] field) { // 가장 큰 블록 찾기
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, field[i][j]);
            }
        }
        return max;
    }

    // 방향별 보드 수정
    private static void up(int[][] field) {
        for (int j = 0; j < N; j++) {
            // 숫자 합치기
            for (int i = 0; i < N - 1; i++) {
                if (field[i][j] == 0) {
                    continue;
                }
                int idx = i;
                while (idx < N - 1) {
                    if (field[i][j] == field[idx + 1][j]) {
                        field[i][j] = field[idx + 1][j] * 2;
                        field[idx + 1][j] = 0;
                        break;
                    } else if (field[idx + 1][j] != 0 && field[i][j] != field[idx + 1][j]) {
                        break;
                    }
                    idx++;
                }
            } // i for end

            // 숫자 옮기기
            for (int i = 0; i < N - 1; i++) {
                if (field[i][j] != 0) {
                    continue;
                }
                int idx = i;
                while (idx < N) {
                    if (field[idx][j] != 0) {
                        field[i][j] = field[idx][j];
                        field[idx][j] = 0;
                        break;
                    }
                    idx++;
                }
            } // i for end
        } // j for end
    }

    private static void down(int[][] field) {
        for (int j = 0; j < N; j++) {
            // 숫자 합치기
            for (int i = N - 1; i > 0; i--) {
                if (field[i][j] == 0) {
                    continue;
                }
                int idx = i;
                while (idx > 0) {
                    if (field[i][j] == field[idx - 1][j]) {
                        field[i][j] = field[idx - 1][j] * 2;
                        field[idx - 1][j] = 0;
                        break;
                    } else if (field[idx - 1][j] != 0 && field[i][j] != field[idx - 1][j]) {
                        break;
                    }
                    idx--;
                }
            } // i for end

            // 숫자 옮기기
            for (int i = N - 1; i > 0; i--) {
                if (field[i][j] != 0) {
                    continue;
                }
                int idx = i;
                while (idx >= 0) {
                    if (field[idx][j] != 0) {
                        field[i][j] = field[idx][j];
                        field[idx][j] = 0;
                        break;
                    }
                    idx--;
                }
            } // i for end
        } // j for end
    }

    private static void left(int[][] field) {
        for (int i = 0; i < N; i++) {
            // 숫자 합치기
            for (int j = 0; j < N - 1; j++) {
                if (field[i][j] == 0) {
                    continue;
                }
                int idx = j;
                while (idx < N - 1) {
                    if (field[i][j] == field[i][idx + 1]) {
                        field[i][j] = field[i][idx + 1] * 2;
                        field[i][idx + 1] = 0;
                        break;
                    } else if (field[i][idx + 1] != 0 && field[i][j] != field[i][idx + 1]) {
                        break;
                    }
                    idx++;
                }
            } // i for end

            // 숫자 옮기기
            for (int j = 0; j < N - 1; j++) {
                if (field[i][j] != 0) {
                    continue;
                }
                int idx = j;
                while (idx < N) {
                    if (field[i][idx] != 0) {
                        field[i][j] = field[i][idx];
                        field[i][idx] = 0;
                        break;
                    }
                    idx++;
                }
            } // i for end
        } // j for end
    }

    private static void right(int[][] field) {
        for (int i = 0; i < N; i++) {
            // 숫자 합치기
            for (int j = N - 1; j > 0; j--) {
                if (field[i][j] == 0) {
                    continue;
                }
                int idx = j;
                while (idx > 0) {
                    if (field[i][j] == field[i][idx - 1]) {
                        field[i][j] = field[i][idx - 1] * 2;
                        field[i][idx - 1] = 0;
                        break;
                    } else if (field[i][idx - 1] != 0 && field[i][j] != field[i][idx - 1]) {
                        break;
                    }
                    idx--;
                }
            } // i for end

            // 숫자 옮기기
            for (int j = N - 1; j > 0; j--) {
                if (field[i][j] != 0) {
                    continue;
                }
                int idx = j;
                while (idx >= 0) {
                    if (field[i][idx] != 0) {
                        field[i][j] = field[i][idx];
                        field[i][idx] = 0;
                        break;
                    }
                    idx--;
                }
            } // i for end
        } // j for end
    }
}
