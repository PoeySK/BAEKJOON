package Simulation;

import java.io.*;
import java.util.*;

public class MonkeyWant {
    static int K, W, H, result;
    static int[][] field;
    static boolean[][][] isVisit;
    static int[] dy = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1}; // 이동할 수 있는 모든 경우
    static int[] dx = {0, 0, -1, 1, -2, -1, 1, 2, -2, -1, 1, 2}; // 이동할 수 있는 모든 경우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        field = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        if (H == 1 && W == 1) { // 1 1 입력 -> 다음 값은 0 고정
            result = 0;
        } else { // 1x1 보다 큰 field인 경우
            isVisit = new boolean[K + 1][H][W];
            run();
        }
        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 0, K)); // [0, 0]에서 시작
        isVisit[K][0][0] = true;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 12; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                int nextJump = now.jump;
                if (i > 3) { // 말처럼 뛰어야 하는 경우
                    if (now.jump == 0) { // 말처럼 못뛰는 경우
                        break; // 이후 말 처럼 뛰는 경우를 모두 이행하지 못함
                    } else { // 말처럼 뛸 수 있는 경우
                        nextJump--;
                    }
                }
                if (0 <= ny && ny < H && 0 <= nx && nx < W && !isVisit[nextJump][ny][nx] && field[ny][nx] == 0) {
                    if (ny == H - 1 && nx == W - 1) { // 목적지 도달 시 종료
                        result = now.time + 1;
                        return;
                    }
                    isVisit[nextJump][ny][nx] = true;
                    q.offer(new Node(ny, nx, now.time + 1, nextJump));

                }
            }
        }
        result = -1;
    }

    static class Node {
        int y;
        int x;
        int time;
        int jump; // 말처럼 뛸 수 있는 횟수

        Node(int y, int x, int time, int jump) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.jump = jump;
        }
    }
}
