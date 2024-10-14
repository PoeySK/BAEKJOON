package Simulation;

import java.io.*;

public class TicTacToe {
    static String[][] field = new String[3][3];
    static boolean isOSuc, isXSuc; // o line, x line

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        String input;
        while ((input = br.readLine()) != null) {
            if (input.equals("end")) {
                break;
            }
            field = new String[3][3];
            int round = 0;
            int oCnt = 0;
            int xCnt = 0;
            String[] str = input.split("");
            for (int i = 0; i < 9; i++) {
                field[i / 3][i % 3] = str[i];

                if (str[i].equals(".")) {
                    round++;
                } else if (str[i].equals("O")) {
                    oCnt++;
                } else if (str[i].equals("X")) {
                    xCnt++;
                }
            }

            /* 작동 */
            isOSuc = false;
            isXSuc = false;
            boolean result = run(round, xCnt - oCnt);

            /* 출력 */
            bw.write(result ? "valid\n" : "invalid\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static boolean run(int round, int cnt) {
        if (cnt < 0 || cnt > 1) return false; // 개수

        lineCheck();
        if (isOSuc && isXSuc) return false;

        if (round == 0) {
            if (isOSuc) return false;

            return cnt == 1;
        }

        if (!isOSuc && !isXSuc) return false;

        return (isXSuc && cnt == 1) || (isOSuc && cnt == 0);
    }

    private static void lineCheck() {
        int oLineCnt = 0;
        int xLineCnt = 0;

        // 가로
        for (int i = 0; i < 3; i++) {
            int oCnt = 0;
            int xCnt = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j].equals("O")) {
                    oCnt++;
                } else if (field[i][j].equals("X")) {
                    xCnt++;
                }
            }
            oLineCnt = oCnt == 3 ? oLineCnt + 1 : oLineCnt;
            xLineCnt = xCnt == 3 ? xLineCnt + 1 : xLineCnt;
        }

        // 세로
        for (int j = 0; j < 3; j++) {
            int oCnt = 0;
            int xCnt = 0;
            for (int i = 0; i < 3; i++) {
                if (field[i][j].equals("O")) {
                    oCnt++;
                } else if (field[i][j].equals("X")) {
                    xCnt++;
                }
            }
            oLineCnt = oCnt == 3 ? oLineCnt + 1 : oLineCnt;
            xLineCnt = xCnt == 3 ? xLineCnt + 1 : xLineCnt;
        }

        // 왼위우아 대각선
        int oCnt = 0;
        int xCnt = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][i].equals("O")) {
                oCnt++;
            } else if (field[i][i].equals("X")) {
                xCnt++;
            }
        }
        oLineCnt = oCnt == 3 ? oLineCnt + 1 : oLineCnt;
        xLineCnt = xCnt == 3 ? xLineCnt + 1 : xLineCnt;

        // 오위왼아 대각선
        oCnt = 0;
        xCnt = 0;
        for (int i = 0; i < 3; i++) {
            if (field[i][2 - i].equals("O")) {
                oCnt++;
            } else if (field[i][2 - i].equals("X")) {
                xCnt++;
            }
        }
        oLineCnt = oCnt == 3 ? oLineCnt + 1 : oLineCnt;
        xLineCnt = xCnt == 3 ? xLineCnt + 1 : xLineCnt;

        // 검사
        isOSuc = oLineCnt > 0;
        isXSuc = xLineCnt > 0;
    }
}

