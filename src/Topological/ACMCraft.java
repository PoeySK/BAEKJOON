package Topological;

import java.io.*;
import java.util.*;

public class ACMCraft {
    static int N, K, W, result;
    static int[] building, degree;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            /* 입력 */
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            building = new int[N + 1];
            for (int i = 1; i < N + 1; i++) {
                building[i] = Integer.parseInt(st.nextToken());
            }

            adj = new ArrayList[N + 1];
            for (int i = 1; i < N + 1; i++) {
                adj[i] = new ArrayList<>();
            }
            degree = new int[N + 1]; // 진입 차수 배열
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adj[X].add(Y);
                degree[Y]++;
            }

            W = Integer.parseInt(br.readLine());

            /* 작동 */
            result = 0;
            run();
            /* 출력 */
            bw.write(result + "\n");
        }
        br.close();
        bw.close();
    }

    private static void run() {
        // 빨리 끝난 건물 먼저 처리
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 1; i < N + 1; i++) {
            if (degree[i] == 0) {
                q.offer(new Node(building[i], i));
            }
        }
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.number == W) {
                result = now.time;
                return;
            }

            for (int next : adj[now.number]) { // 위상 정렬 이용
                degree[next]--;
                if (degree[next] == 0) {
                    q.offer(new Node(now.time + building[next], next));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int time;
        int number;

        Node(int time, int number) {
            this.time = time;
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
