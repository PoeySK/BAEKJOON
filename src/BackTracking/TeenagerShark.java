package BackTracking;

import java.io.*;
import java.util.*;

public class TeenagerShark {
    static int result;
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        Node[] fishInfo = new Node[17]; // 물고기 정보
        int[][] field = new int[4][4]; // 필드 정보
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int fish = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                fishInfo[fish] = new Node(i, j, fish, direction, false);
                field[i][j] = fish;
            }
        }

        /* 작동 */
        Node shark = new Node(0, 0, field[0][0], fishInfo[field[0][0]].direction, field[0][0]);
        fishInfo[field[0][0]].isShark = true;
        field[0][0] = -1;
        Save save = move(field, fishInfo, shark);
        run(save.field, save.info, shark);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int[][] field, Node[] fishInfo, Node shark) {
        result = Math.max(result, shark.eat);

        /* 이동 */
        int sy = shark.y;
        int sx = shark.x;
        int sd = shark.direction;
        int se = shark.eat;
        int[][] copyField = new int[4][4];
        for (int i = 0; i < 4; i++) {
            copyField[i] = field[i].clone();
        }
        Node[] copyFishInfo = new Node[17];
        for (int i = 1; i <= 16; i++) {
            copyFishInfo[i] = new Node(fishInfo[i].y, fishInfo[i].x, fishInfo[i].number, fishInfo[i].direction, fishInfo[i].isShark);
        }
        /* 먹기 */
        for (int i = 1; i <= 3; i++) {
            int ny = sy + (dy[sd] * i);
            int nx = sx + (dx[sd] * i);
            if (0 <= ny && ny < 4 && 0 <= nx && nx < 4 && copyField[ny][nx] > 0) {
                int next = copyField[ny][nx];

                // 상어 물고기 먹기
                copyField[sy][sx] = 0;
                copyField[ny][nx] = -1;

                // 상어 정보 변경
                shark = new Node(ny, nx, next, copyFishInfo[next].direction, se + next);
                copyFishInfo[next].isShark = true;

                Save save = move(copyField, copyFishInfo, shark);
                run(save.field, save.info, shark);

                copyField[sy][sx] = -1;
                copyField[ny][nx] = next;
                copyFishInfo[next].isShark = false;
            }
        }

    }

    private static Save move(int[][] nowField, Node[] fishInfo, Node Shark) { // 물고기 이동
        int[][] nextField = new int[4][4];
        for (int i = 0; i < 4; i++) {
            nextField[i] = nowField[i].clone();
        }
        Node[] copyFish = new Node[17];
        for (int i = 1; i <= 16; i++) {
            copyFish[i] = new Node(fishInfo[i].y, fishInfo[i].x, fishInfo[i].number, fishInfo[i].direction, fishInfo[i].isShark);
        }

        for (int i = 1; i <= 16; i++) {
            if (copyFish[i].isShark || Shark.number == i) { // 죽은 물고기 or 상어
                continue;
            }
            while (true) {
                Node now = copyFish[i];
                int ny = now.y + dy[now.direction];
                int nx = now.x + dx[now.direction];

                if (0 <= ny && ny < 4 && 0 <= nx && nx < 4 && nextField[ny][nx] != -1) {
                    int next = nextField[ny][nx];
                    if (next == 0) {
                        nextField[now.y][now.x] = 0;
                        nextField[ny][nx] = i;
                        copyFish[i].y = ny;
                        copyFish[i].x = nx;

                        break;
                    }
                    /* 교체 */
                    int tempY = copyFish[i].y;
                    int tempX = copyFish[i].x;

                    // 현 위치
                    nextField[now.y][now.x] = next;
                    copyFish[i].y = copyFish[next].y;
                    copyFish[i].x = copyFish[next].x;

                    // 타겟 위치
                    copyFish[next].y = tempY;
                    copyFish[next].x = tempX;
                    nextField[ny][nx] = i;

                    break;
                } else {
                    copyFish[i].direction++;
                    if (copyFish[i].direction == 9) {
                        copyFish[i].direction = 1;
                    }
                }
            }
        }

        return new Save(nextField, copyFish);
    }

    static class Save {
        int[][] field;
        Node[] info;

        Save(int[][] field, Node[] info) {
            this.field = field;
            this.info = info;
        }
    }

    static class Node {
        int y;
        int x;
        int number;
        int direction;
        boolean isShark;
        int eat;

        Node(int y, int x, int number, int direction, boolean isShark) { // 물고기 정보
            this.y = y;
            this.x = x;
            this.number = number;
            this.direction = direction;
            this.isShark = isShark;
        }

        Node(int y, int x, int number, int direction, int eat) { // 상어 정보
            this.y = y;
            this.x = x;
            this.number = number;
            this.direction = direction;
            this.eat = eat;
        }
    }
}