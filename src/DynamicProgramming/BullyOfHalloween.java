package DynamicProgramming;

import java.io.*;
import java.util.*;

public class BullyOfHalloween {
    static int N, M, K, count, size;
    static int[] candy;
    static ArrayList<Integer>[] adj;
    static ArrayList<Node> info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        candy = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        br.close();

        /* 작동 */
        info = new ArrayList<>();
        findInfo();
        Collections.sort(info);
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int[] sol = new int[K];
        for (int i = 1; i < info.size() + 1; i++) {
            for (int j = K - 1; j >= 0; j--) {
                if (j >= info.get(i - 1).size) {
                    int next = sol[j - info.get(i - 1).size] + info.get(i - 1).count;
                    sol[j] = Math.max(sol[j], next);
                }
            }
        }

        return sol[K - 1];
    }

    private static void findInfo() {
        boolean[] isCheck = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (isCheck[i]) continue;
            isCheck[i] = true;
            count = 0;
            size = 0;
            bfs(i, isCheck);
            info.add(new Node(count, size));
        }
    }

    private static void bfs(int idx, boolean[] isCheck) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(idx);
        count = candy[idx];
        size = 1;
        isCheck[idx] = true;
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : adj[now]) {
                if (!isCheck[next]) {
                    isCheck[next] = true;
                    q.offer(next);
                    size++;
                    count += candy[next];
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int count;
        int size;

        Node(int count, int size) {
            this.count = count;
            this.size = size;
        }

        @Override
        public int compareTo(Node o) {
            return this.size - o.size;
        }
    }
}
