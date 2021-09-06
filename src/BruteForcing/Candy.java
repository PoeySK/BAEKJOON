package BruteForcing;

import java.io.*;
import java.util.*;

public class Candy {
    public static char[][] board;    // 맵 크기
    public static int N;            // 맵 크기
    public static int max = 0;        // 먹을 수 있는 최대 사탕개수

    /* 두 문자 교환하는 메소드 */
    public static void swap(char a, char b) {
        char temp = a;
        a = b;
        b = temp;
    }

    /* 가로로, 세로로 비교하면서 먹을 수 있는 사탕의 최대 갯수 찾기 */
    public static void arrCheck() {

        // → 가로로 체크
        for (int i = 0; i < N; i++) {
            int count = 1;
            for (int j = 0; j < N - 1; j++) {

                // 이전 사탕과 동일한 경우 -> 계속 먹는다
                if (board[i][j] == board[i][j + 1])
                    count++;

                    // 이전과 다른 사탕인 경우 -> 새로 먹어야하므로 1로 초기화
                else
                    count = 1;

                // 사탕 최대 개수 저장
                max = Math.max(max, count);
            }
        }

        // ↓ 세로로 체크
        for (int i = 0; i < N; i++) {
            int count = 1;
            for (int j = 0; j < N - 1; j++) {
                if (board[j][i] == board[j + 1][i])
                    count++;
                else
                    count = 1;
                max = Math.max(max, count);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());    // 보드의 크기
        board = new char[N][N];

        /* N x N 사탕 입력받기 */
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = str[j].charAt(0);
            }
        }

        /* 가로로 인접한 두 사탕 교환하고 최대 사탕 개수 찾고 원위치 */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                // 가로로 인접한 두 문자 교환
                //swap(board[i][j], board[i][j+1]);
                char temp = board[i][j];
                board[i][j] = board[i][j + 1];
                board[i][j + 1] = temp;

                // →, ↓ 체크
                arrCheck();

                // 이전에 교환한 문자 복구
                //swap(board[i][j], board[i][j+1]);
                temp = board[i][j];
                board[i][j] = board[i][j + 1];
                board[i][j + 1] = temp;
            }
        }

        /* 세로로 인접한 두 사탕 교환하고 최대 사탕 개수 찾고 원위치 */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                //swap(board[j][i], board[j+1][i]);

                char temp = board[j][i];
                board[j][i] = board[j + 1][i];
                board[j + 1][i] = temp;

                // →, ↓ 체크
                arrCheck();

                // 이전에 교환한 문자 복구
                //swap(board[j][i], board[j+1][i]);
                temp = board[j][i];
                board[j][i] = board[j + 1][i];
                board[j + 1][i] = temp;
            }
        }

        System.out.println(max);
    }
}