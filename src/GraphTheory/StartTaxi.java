package GraphTheory;

import java.io.*;
import java.util.*;

public class StartTaxi {
    static int N, M, K;
    static int[][] field;
    static ArrayList<Save> target;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine()); // 택시 위치
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        target = new ArrayList<>();
        int start = 2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int py = Integer.parseInt(st.nextToken());
            int px = Integer.parseInt(st.nextToken());
            int ty = Integer.parseInt(st.nextToken());
            int tx = Integer.parseInt(st.nextToken());
            field[py - 1][px - 1] = start++;
            target.add(new Save(py, px, ty, tx));
        }
        br.close();

        /* 작동 */
        run(y - 1, x - 1);

        /* 출력 */
        bw.write((K < 0 ? -1 : K) + "\n");
        bw.close();
    }

    private static void run(int y, int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] isVisit = new boolean[N][N];
        q.offer(new Node(y, x, 0, false));
        isVisit[y][x] = true;

        int TY = -1;
        int TX = -1;

        for (Save s : target) {
            int len = findLen(y, x, s.py - 1, s.px - 1);
            if (len == 0) {
                TY = s.ty - 1;
                TX = s.tx - 1;
                q.peek().isGet = true;
                field[y][x] = 0;
                break;
            }
        }

        int count = 0; // 완료한 손님 개수
        while (!q.isEmpty()) {
            int size = q.size();

            L:
            while (size-- > 0) { // level 단위 검사
                Node now = q.poll();

                for (int i = 0; i < 4; i++) {
                    int ny = now.y + dy[i];
                    int nx = now.x + dx[i];
                    if (0 <= ny && ny < N && 0 <= nx && nx < N && !isVisit[ny][nx] && field[ny][nx] != 1) {
                        // 손님
                        if (!now.isGet && field[ny][nx] > 1) {
                            pq.offer(new Node(ny, nx, now.fue + 1, true)); // 가까운 손님 찾기
                        }

                        // 도착
                        if (now.isGet && ny == TY && nx == TX) {
                            TY = TX = -1; // 초기화
                            K -= now.fue + 1;
                            if (K < 0) {
                                return;
                            }
                            K += (now.fue + 1) * 2;

                            q.clear();
                            q.offer(new Node(ny, nx, 0, false));
                            isVisit = new boolean[N][N];

                            if (field[ny][nx] > 1) { // 도착지에 손님이 있는 경우
                                TY = target.get(field[ny][nx] - 2).ty - 1;
                                TX = target.get(field[ny][nx] - 2).tx - 1;
                                q.peek().isGet = true;
                                field[ny][nx] = 0;
                            }

                            count++;
                            if (count == M) {
                                return;
                            }

                            break L;
                        }

                        // 이동
                        q.offer(new Node(ny, nx, now.fue + 1, now.isGet));
                        isVisit[ny][nx] = true;
                    } // if end
                } // for end
            } // size while end

            if (!pq.isEmpty()) {
                Node tg = pq.poll();
                TY = target.get(field[tg.y][tg.x] - 2).ty - 1;
                TX = target.get(field[tg.y][tg.x] - 2).tx - 1;
                K -= tg.fue;
                if (K <= 0) { // 손님 태우고 연료가 없으면 출발 못함
                    K = -1;
                    return;
                }

                // 초기화 과정
                field[tg.y][tg.x] = 0;
                pq.clear();

                isVisit = new boolean[N][N];
                isVisit[tg.y][tg.x] = true;

                q.clear(); // 새로운 이동
                q.offer(new Node(tg.y, tg.x, 0, true));
            }
        } // while end

        if (count != M) {
            K = -1;
        }
    }

    private static int findLen(int y1, int x1, int y2, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }

    static class Save {
        int py, px, ty, tx;

        Save(int py, int px, int ty, int tx) {
            this.py = py;
            this.px = px;
            this.ty = ty;
            this.tx = tx;
        }
    }

    static class Node implements Comparable<Node> {
        int y, x, fue;
        boolean isGet; // 손님 태운 상황

        Node(int y, int x, int fue, boolean isGet) {
            this.y = y;
            this.x = x;
            this.fue = fue;
            this.isGet = isGet;
        }

        @Override
        public int compareTo(Node o) { // 우선 순위: 거리 -> 행 -> 열
            if (this.y == o.y) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
    }
}
