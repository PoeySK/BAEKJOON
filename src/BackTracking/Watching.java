package BackTracking;

import java.io.*;
import java.util.*;

public class Watching {
    static int N, M, D, result;
    static int E; // 적의 수
    static int isCheck; // 제거된 적 수 확인
    static int[][] field;
    static int[] dy = {0, -1, 0};
    static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        field = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j] == 1) {
                    E++;
                }
            }
        }
        br.close();

        /* 작동 */
        run(0, 0, new Node[3], bw); // 3명의 궁수

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int idx, int cnt, Node[] sel, BufferedWriter bw) throws IOException {
        // 플레이 단계
        if (cnt == 3) {
            int[][] copy = new int[N + 1][M];
            for (int i = 0; i < N + 1; i++) {
                copy[i] = Arrays.copyOf(field[i], M);
            }
            isCheck = E;
            int count = 0; // 공격한 적 수
            while (isCheck != 0) {
                count += play(copy, sel); // 제거 단계
                forward(copy); // 전진 단계
            }

            if (count == E) { // 공격한 수와 전체 적이 같으면 바로 종료
                bw.write(count + "\n");
                bw.close();
                System.exit(0);
            }

            result = Math.max(result, count);
            return;
        }

        if (idx == M) {
            return;
        }

        // 배치 단계
        if (field[N][idx] == 0) {
            field[N][idx] = 8;
            sel[cnt] = new Node(N, idx, 0);
            run(idx + 1, cnt + 1, sel, bw);
            field[N][idx] = 0;
            run(idx + 1, cnt, sel, bw);
        }

    }

    private static int play(int[][] copy, Node[] sel) {
        ArrayList<Node> save = new ArrayList<>();
        Queue<Node> q;
        for (Node s : sel) { // 궁수 한 명씩 적 탐색
            q = new ArrayDeque<>();
            q.offer(s);
            L:
            while (!q.isEmpty()) {
                Node now = q.poll();
                if (now.range == D) { // 사거리가 끝났을 때
                    break;
                }

                for (int i = 0; i < 3; i++) {
                    int ny = now.y + dy[i];
                    int nx = now.x + dx[i];

                    if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                        if (copy[ny][nx] == 1) {
                            save.add(new Node(ny, nx));
                            break L;
                        }
                        if (copy[ny][nx] == 0) {
                            q.offer(new Node(ny, nx, now.range + 1));
                        }
                    }
                }
            } // q while end
        } // for each end

        // 탐색한 적 제거
        int count = 0;
        for (Node node : save) {
            if (copy[node.y][node.x] == 1) {
                copy[node.y][node.x] = 0;
                count++;
                isCheck--;
            }
        }
        return count;
    }

    private static void forward(int[][] copy) {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 1) {
                    copy[i][j] = 0;
                    copy[i + 1][j] = 1;
                    if (i + 1 == N) { // 성에 도착한 경우 제거
                        copy[i + 1][j] = 0;
                        isCheck--;
                    }
                }
            }
        }
    }

    static class Node {
        int y, x, range;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Node(int y, int x, int range) {
            this.y = y;
            this.x = x;
            this.range = range;
        }
    }
}
