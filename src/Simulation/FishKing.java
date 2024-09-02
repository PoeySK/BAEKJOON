package Simulation;

import java.io.*;
import java.util.*;

public class FishKing {
    static int R, C, M, result;
    static Info[][] field;
    static ArrayList<Info> shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        shark = new ArrayList<>();
        field = new Info[R][C];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            shark.add(new Info(r, c, s, d, z));
            field[r - 1][c - 1] = new Info(r, c, s, d, z);
        }
        br.close();

        /* 작동 */
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        for (int i = 0; i < C; i++) {
            fish(i);
            move();
            setting();
        }
    }

    private static void fish(int c) {
        for (int i = 0; i < R; i++) {
            if (field[i][c] != null) {
                result += field[i][c].z;
                field[i][c] = null;
                return; // 바로 위 상어만 먹음
            }
        }
    }

    private static void move() {
        for (int i = 0; i < shark.size(); i++) {
            int nr = shark.get(i).r - 1;
            int nc = shark.get(i).c - 1;

            // 잡혔는데 모르는 경우 (멍청한 상어)
            if (field[nr][nc] == null || field[nr][nc].z != shark.get(i).z) {
                shark.remove(i);
                i--;
                continue;
            }

            int s = shark.get(i).s;
            field[nr][nc] = null;
            while (s > 0) { // 속도만큼 이동
                switch (shark.get(i).d) {
                    case 1: // 상
                        while (shark.get(i).r > 1) {
                            shark.get(i).r--;
                            s--;
                            if (s == 0) {
                                break;
                            }
                        }
                        if (s > 0) {
                            shark.get(i).d++;
                        }
                        break;
                    case 2: // 하
                        while (shark.get(i).r < R) {
                            shark.get(i).r++;
                            s--;
                            if (s == 0) {
                                break;
                            }
                        }
                        if (s > 0) {
                            shark.get(i).d--;
                        }
                        break;
                    case 3: // 우
                        while (shark.get(i).c < C) {
                            shark.get(i).c++;
                            s--;
                            if (s == 0) {
                                break;
                            }
                        }
                        if (s > 0) {
                            shark.get(i).d++;
                        }
                        break;
                    case 4: // 좌
                        while (shark.get(i).c > 1) {
                            shark.get(i).c--;
                            s--;
                            if (s == 0) {
                                break;
                            }
                        }
                        if (s > 0) {
                            shark.get(i).d--;
                        }
                        break;
                }
            } // while end
        } // for end
    }

    private static void setting() { // 상어 위치 재조정
        for (int i = 0; i < shark.size(); i++) {
            Info now = shark.get(i);
            if (field[now.r - 1][now.c - 1] == null) {
                field[now.r - 1][now.c - 1] = shark.get(i);
            } else { // 상어가 존재하는 경우
                if (field[now.r - 1][now.c - 1].z < now.z) {
                    field[now.r - 1][now.c - 1] = now;
                    // 삭제는 move에서 처리
                } else {
                    shark.remove(i);
                    i--; // 사이즈 처리
                }
            }
        }
    }

    static class Info {
        int r, c, s, d, z;

        Info(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
