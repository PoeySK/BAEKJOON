package Samsung;

import java.io.*;
import java.util.*;

public class ExplorationOfAntiquities {
    static int K, M, pIdx;
    static int[] pieces;
    static int[][] field;
    static StringBuilder sb;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        field = new int[5][5];
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pieces = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            pieces[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        sb = new StringBuilder();
        run();

        bw.write(sb.toString() + "\n");
        bw.close();
    }

    private static void run() {
        int[][] copyArr = new int[5][5];
        while (K-- > 0) {
            int max = 0, minS = 4;
            Node node = new Node(0, 0);
            for (int j = 1; j < 4; j++) {
                for (int i = 1; i < 4; i++) {
                    copy(copyArr, field);
                    for (int s = 0; s < 3; s++) {
                        spin(i, j, s, copyArr);

                        boolean[][] isVisit = new boolean[5][5];
                        int total = 0;
                        for (int p = -1; p < 2; p++) {
                            for (int q = -1; q < 2; q++) {
                                int y = i + p, x = j + q;
                                if (isVisit[y][x]) continue;

                                int count = find(copyArr, y, x, isVisit);
                                if (count > 2) total += count;
                            }
                        }

                        if (max < total) {
                            max = total;
                            minS = s;
                            node.y = i;
                            node.x = j;
                        }

                        if (max == total && minS > s) {
                            minS = s;
                            node.y = i;
                            node.x = j;
                        }
                    }
                }
            }

            if (max == 0) return;

            copy(copyArr, field);
            spin(node.y, node.x, minS, copyArr);
            copy(field, copyArr);

            sb.append(obtain()).append(" ");
        }
    }

    private static int obtain() {
        int total = 0;
        boolean flag = true;
        while (flag) {
            boolean[][] isVisit = new boolean[5][5];
            ArrayList<Node> save = new ArrayList<>();
            flag = false;
            for (int i = 4; i >= 0; i--) {
                for (int j = 0; j < 5; j++) {
                    if (isVisit[i][j]) continue;

                    int count = find(field, i, j, isVisit);
                    if (count > 2) {
                        flag = true;
                        fill(i, j, save);
                        total += count;
                    }
                }
            }
            Collections.sort(save);
            for (Node s : save) {
                field[s.y][s.x] = pieces[pIdx++];
            }
        }

        return total;
    }

    private static void fill(int startY, int startX, ArrayList<Node> save) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(startY, startX));
        int target = field[startY][startX];
        boolean[][] isVisit = new boolean[5][5];
        isVisit[startY][startX] = true;
        while (!q.isEmpty()) {
            Node now = q.poll();
            save.add(now);
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && !isVisit[ny][nx] && field[ny][nx] == target) {
                    isVisit[ny][nx] = true;
                    q.offer(new Node(ny, nx));
                }
            }
        }
    }

    private static int find(int[][] arr, int startY, int startX, boolean[][] isVisit) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        int target = arr[startY][startX];
        q.offer(new Node(startY, startX));
        isVisit[startY][startX] = true;
        int count = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (isRange(ny, nx) && !isVisit[ny][nx] && arr[ny][nx] == target) {
                    isVisit[ny][nx] = true;
                    count++;
                    q.offer(new Node(ny, nx));
                }
            }
        }

        return count;
    }

    private static void spin(int y, int x, int s, int[][] arr) {
        switch (s) {
            case 0: // 90
                for (int i = 0; i < 3; i++) {
                    arr[y - 1][x - 1 + i] = field[y + 1 - i][x - 1];
                }
                arr[y][x + 1] = field[y - 1][x];
                for (int i = 0; i < 3; i++) {
                    arr[y + 1][x + 1 - i] = field[y - 1 + i][x + 1];
                }
                arr[y][x - 1] = field[y + 1][x];
                break;
            case 1: // 180
                for (int i = 0; i < 3; i++) {
                    arr[y - 1][x - 1 + i] = field[y + 1][x + 1 - i];
                }
                arr[y][x + 1] = field[y][x - 1];
                for (int i = 0; i < 3; i++) {
                    arr[y + 1][x - 1 + i] = field[y - 1][x + 1 - i];
                }
                arr[y][x - 1] = field[y][x + 1];
                break;
            case 2: // 270, -90
                for (int i = 0; i < 3; i++) {
                    arr[y - 1][x - 1 + i] = field[y - 1 + i][x + 1];
                }
                arr[y][x + 1] = field[y + 1][x];
                for (int i = 0; i < 3; i++) {
                    arr[y + 1][x - 1 + i] = field[y - 1 + i][x - 1];
                }
                arr[y][x - 1] = field[y - 1][x];
                break;
        }
    }

    private static boolean isRange(int y, int x) {
        return 0 <= y && y < 5 && 0 <= x && x < 5;
    }

    private static void copy(int[][] arr, int[][] target) {
        for (int i = 0; i < 5; i++) {
            arr[i] = target[i].clone();
        }
    }

    private static class Node implements Comparable<Node> {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x == o.x) return o.y - this.y;
            return this.x - o.x;
        }
    }
}
