package Simulation;

import java.io.*;
import java.util.*;

public class Cogwheel {
    static int[][] wheel = new int[4][8];
    static Node[] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = Integer.parseInt(input[j]);
            }
        }

        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken()) - 1; // 1~4 -> 0~3
            int direction = Integer.parseInt(st.nextToken());

            info = new Node[4];
            for (int i = 0; i < 4; i++) {
                info[i] = new Node(false, 0);
            }

            /* 작동 */
            run(target, direction);
            for (int i = 0; i < 4; i++) {
                if (info[i].isCheck) {
                    wheel[i] = spin(i, info[i].d);
                }
            }
        }


        /* 출력 */
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += wheel[i][0] == 1 ? (int) Math.pow(2, i) : 0;
        }
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int target, int direction) {
        // 돌릴 수 있는 경우 true
        info[target].isCheck = true;
        info[target].d = direction;
        int reverse = direction * (-1);
        switch (target) {
            case 0:
                if (!info[target + 1].isCheck && wheel[target][2] != wheel[target + 1][6]) {
                    run(target + 1, reverse);
                }
                break;
            case 1:
            case 2:
                if (!info[target - 1].isCheck && wheel[target][6] != wheel[target - 1][2]) {
                    run(target - 1, reverse);
                }
                if (!info[target + 1].isCheck && wheel[target][2] != wheel[target + 1][6]) {
                    run(target + 1, reverse);
                }
                break;
            case 3:
                if (!info[target - 1].isCheck && wheel[target][6] != wheel[target - 1][2]) {
                    run(target - 1, reverse);
                }
                break;
        }
    }

    private static int[] spin(int target, int direction) { // 톱니바퀴 돌리기
        int[] temp = new int[8];
        if (direction == 1) { // 시계 방향
            temp[0] = wheel[target][7];
            for (int i = 0; i < 7; i++) {
                temp[i + 1] = wheel[target][i];
            }
        } else { // direction == -1, 반시계 방향
            temp[7] = wheel[target][0];
            for (int i = 1; i < 8; i++) {
                temp[i - 1] = wheel[target][i];
            }
        }

        return temp;
    }

    static class Node {
        boolean isCheck;
        int d;

        Node(boolean isCheck, int d) {
            this.isCheck = isCheck;
            this.d = d;
        }
    }
}
