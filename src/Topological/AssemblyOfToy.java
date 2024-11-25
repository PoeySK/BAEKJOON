package Topological;

import java.io.*;
import java.util.*;

public class AssemblyOfToy {
    static int N, M;
    static ArrayList<Node>[] adj;
    static int[] result, degree;
    static boolean[] isMiddle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        isMiddle = new boolean[N + 1];
        degree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            isMiddle[X] = true;
            adj[X].add(new Node(Y, K));
            degree[Y]++;
        }
        br.close();

        /* 작동 */
        result = new int[N + 1];
        run();

        /* 출력 */
        for (int i = 1; i < N + 1; i++) {
            if (!isMiddle[i]) bw.write(i + " " + result[i] + "\n");
        }
        bw.close();
    }

    private static void run() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(N, 1));
        result[N] = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (Node next : adj[now.y]) {
                result[next.y] += result[now.y] * next.k;
                degree[next.y]--;
                if (degree[next.y] == 0) {
                    q.offer(new Node(next.y, result[next.y]));
                }
            }
        }
    }

    static class Node {
        int y, k;

        Node(int y, int k) {
            this.y = y;
            this.k = k;
        }
    }
}
