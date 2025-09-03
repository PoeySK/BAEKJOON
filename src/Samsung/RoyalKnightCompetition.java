package Samsung;

import java.util.*;
import java.io.*;

public class RoyalKnightCompetition {
    static int L, N, Q;
    static int[][] field, place;
    static Node[] knights;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        field = new int[L][L];
        place = new int[L][L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        knights = new Node[N];
        int number = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            knights[i] = new Node(r, c, h, w, k, 0, true);
            for (int p = r; p < r + h; p++) {
                for (int q = c; q < c + w; q++) {
                    place[p][q] = number;
                }
            }
            number++;
        }
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            run(i, d);
        }
        br.close();

        int total = 0;
        for (int i = 0; i < N; i++) {
            if (knights[i].isLive) {
                total += knights[i].damage;
            }
        }
        bw.write(total + "\n");
        bw.close();
    }

    private static void run(int i, int d) {
        if (!knights[i].isLive) return;
        ArrayList<Integer> moveKnights = new ArrayList<>();
        if (!isMove(i, d, moveKnights)) {
            return;
        }

        move(d, moveKnights);
        for (int p = 0; p < moveKnights.size(); p++) {
            deploy(moveKnights.get(p));
        }
        suffer(i, moveKnights);
    }

    private static void suffer(int target, ArrayList<Integer> moveKnights) {
        for (int i = 0; i < moveKnights.size(); i++) {
            int number = moveKnights.get(i);
            if (number == target) continue;

            Node knight = knights[number];
            int totalDamage = 0;
            for (int r = knight.r; r < knight.r + knight.h; r++) {
                for (int c = knight.c; c < knight.c + knight.w; c++) {
                    if (field[r][c] == 1) totalDamage += 1;
                }
            }

            knights[number].k -= totalDamage;
            knights[number].damage += totalDamage;
            if (knights[number].k < 1) {
                knights[number].isLive = false;
                knights[number].damage += knights[number].k;
                for (int r = knight.r; r < knight.r + knight.h; r++) {
                    for (int c = knight.c; c < knight.c + knight.w; c++) {
                        place[r][c] = 0;
                    }
                }
            }
        }
    }

    private static void move(int d, ArrayList<Integer> moveKnights) {
        for (int i = moveKnights.size() - 1; i >= 0; i--) {
            int number = moveKnights.get(i);
            Node knight = knights[number];

            switch (d) {
                case 0: // 위
                    for (int p = knight.c; p < knight.c + knight.w; p++) {
                        place[knight.r + knight.h - 1][p] = 0;
                    }
                    break;
                case 1: // 오른
                    for (int p = knight.r; p < knight.r + knight.h; p++) {
                        place[p][knight.c] = 0;
                    }
                    break;
                case 2: // 아래
                    for (int p = knight.c; p < knight.c + knight.w; p++) {
                        place[knight.r][p] = 0;
                    }
                    break;
                case 3: // 왼
                    for (int p = knight.r; p < knight.r + knight.h; p++) {
                        place[p][knight.c + knight.w - 1] = 0;
                    }
                    break;
            }
            knights[number].r += dy[d];
            knights[number].c += dx[d];
        }
    }

    private static boolean isMove(int i, int d, ArrayList<Integer> moveKnights) {
        if (moveKnights.contains(i)) return true;
        moveKnights.add(i);
        Node knight = knights[i];
        int row, col;
        switch (d) {
            case 0: // 위
                row = knight.r;
                for (int p = knight.c; p < knight.c + knight.w; p++) {
                    if (row - 1 == -1 || field[row - 1][p] == 2) return false;

                    boolean isCheck = true;
                    if (place[row - 1][p] > 0) {
                        isCheck = isMove(place[row - 1][p] - 1, d, moveKnights);
                    }

                    if (!isCheck) return false;
                }
                break;
            case 1: // 오른
                col = knight.c + knight.w - 1;
                for (int p = knight.r; p < knight.r + knight.h; p++) {
                    if (col + 1 == L || field[p][col + 1] == 2) return false;
                    boolean isCheck = true;
                    if (place[p][col + 1] > 0) {
                        isCheck = isMove(place[p][col + 1] - 1, d, moveKnights);
                    }

                    if (!isCheck) return false;
                }
                break;
            case 2: // 아래
                row = knight.r + knight.h - 1;
                for (int p = knight.c; p < knight.c + knight.w; p++) {
                    if (row + 1 == L || field[row + 1][p] == 2) return false;

                    boolean isCheck = true;
                    if (place[row + 1][p] > 0) {
                        isCheck = isMove(place[row + 1][p] - 1, d, moveKnights);
                    }

                    if (!isCheck) return false;
                }
                break;
            case 3: // 왼
                col = knight.c;
                for (int p = knight.r; p < knight.r + knight.h; p++) {
                    if (col - 1 == -1 || field[p][col - 1] == 2) return false;

                    boolean isCheck = true;
                    if (place[p][col - 1] > 0) {
                        isCheck = isMove(place[p][col - 1] - 1, d, moveKnights);
                    }

                    if (!isCheck) return false;
                }
                break;
        }
        return true;
    }

    private static void deploy(int i) {
        Node knight = knights[i];
        if (!knight.isLive) return;

        for (int r = knight.r; r < knight.r + knight.h; r++) {
            for (int c = knight.c; c < knight.c + knight.w; c++) {
                place[r][c] = i + 1;
            }
        }
    }

    static class Node {
        int r, c, h, w, k, damage;
        boolean isLive;

        Node(int r, int c, int h, int w, int k, int damage, boolean isLive) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.damage = damage;
            this.isLive = isLive;
        }
    }
}
