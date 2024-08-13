package Dijkstra;

import java.io.*;
import java.util.*;

public class MakeBridgeTwo {
    static int N, M;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] field;
    static boolean[][] isMark;
    static ArrayList<Node>[] link; // 연결 트리
    static Queue<Point> save = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == 1) {
                    save.offer(new Point(i, j)); // 섬의 위치들 저장
                }
            }
        }
        br.close();

        /* 작동 */
        isMark = new boolean[N][M];
        int number = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 1 && !isMark[i][j]) {
                    mark(i, j, number);
                    number++;
                }
            }
        }

        link = new ArrayList[number];
        for (int i = 0; i < number; i++) {
            link[i] = new ArrayList<>();
        }

        run();
        int result = prim(number);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() { // 저장한 섬들의 위치로 다리 이어보기
        Queue<Point> q = new ArrayDeque<>();
        int size = save.size();
        for (int i = 0; i < size; i++) {
            Point s = save.poll();
            int number = field[s.y][s.x];
            q.offer(new Point(s.y, s.x, 0));
            while (!q.isEmpty()) {
                Point now = q.poll();
                for (int j = 0; j < 4; j++) {
                    find(now, j, number);
                }
            }
        }
    }

    private static void find(Point now, int j, int number) { // 다리 잇기
        int ny = now.y;
        int nx = now.x;
        int nc = now.count;
        switch (j) {
            case 0: // 상
                ny--;
                while (ny >= 0) {
                    if (field[ny][nx] == 0) {
                        nc++;
                        ny--;
                    } else if (field[ny][nx] != number) {
                        if (nc > 1) { // 다리는 최소 2칸
                            // 연결되는 모든 정보를 인접 리스트로 저장
                            link[number].add(new Node(field[ny][nx], nc));
                        }
                        break;
                    } else { // 같은 number의 섬 탐색
                        break;
                    }
                }
                break;
            case 1: // 하
                ny++;
                while (ny < N) {
                    if (field[ny][nx] == 0) {
                        nc++;
                        ny++;
                    } else if (field[ny][nx] != number) {
                        if (nc > 1) {
                            link[number].add(new Node(field[ny][nx], nc));
                        }
                        break;
                    } else {
                        break;
                    }
                }
                break;
            case 2: // 좌
                nx--;
                while (nx >= 0) {
                    if (field[ny][nx] == 0) {
                        nc++;
                        nx--;
                    } else if (field[ny][nx] != number) {
                        if (nc > 1) {
                            link[number].add(new Node(field[ny][nx], nc));
                        }
                        break;
                    } else {
                        break;
                    }
                }
                break;
            case 3: // 우
                nx++;
                while (nx < M) {
                    if (field[ny][nx] == 0) {
                        nc++;
                        nx++;
                    } else if (field[ny][nx] != number) {
                        if (nc > 1) {
                            link[number].add(new Node(field[ny][nx], nc));
                        }
                        break;
                    } else {
                        break;
                    }
                }
                break;
        }
    }

    private static void mark(int y, int x, int number) { // 섬 번호 지정
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(y, x));
        isMark[y][x] = true;
        field[y][x] = number;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (0 <= ny && ny < N && 0 <= nx && nx < M && field[ny][nx] == 1 && !isMark[ny][nx]) {
                    q.offer(new Point(ny, nx));
                    isMark[ny][nx] = true;
                    field[ny][nx] = number;
                }
            }
        }
    }

    private static int prim(int number) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] isVisit = new boolean[number];
        pq.offer(new Node(1, 0));

        int count = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (isVisit[now.v]) {
                continue;
            }
            isVisit[now.v] = true;
            count += now.w;
            int size = link[now.v].size();
            for (int i = 0; i < size; i++) {
                Node next = link[now.v].get(i);
                pq.offer(next);
            }
        }

        for(int i=1; i<number; i++) {
            if(!isVisit[i]) { // 연결이 안된 곳이 있는 경우
                return -1;
            }
        }
        return count;
    }
    static class Point {
        int y;
        int x;
        int count;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }

    static class Node implements Comparable<Node> {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node n) {
            return this.w - n.w;
        }
    }
}
