package Dijkstra;

import java.io.*;
import java.util.*;

public class ShortPath {
    static ArrayList<Node>[] path;
    static int[] result;
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        path = new ArrayList[V + 1];
        result = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            path[i] = new ArrayList<>();
            result[i] = -1;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            path[u].add(new Node(v, w));
        }
        br.close();

        /* 작동 */
        result[K] = 0;
        isVisit = new boolean[V + 1];
        run(K);


        /* 출력 */
        for (int i = 1; i < V + 1; i++) {
            if (result[i] == -1) {
                bw.write("INF\n");
            } else {
                bw.write(result[i] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static void run(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (isVisit[now.v]) {
                continue;
            }
            isVisit[now.v] = true;

            for (int i = 0; i < path[now.v].size(); i++) {
                int nextV = path[now.v].get(i).v;
                int weight = path[now.v].get(i).w;
                if (result[nextV] == -1 || result[nextV] > result[now.v] + weight) { // 첫 방문 or 이전보다 더 작은 경로
                    result[nextV] = result[now.v] + weight;
                    q.add(new Node(nextV, result[nextV]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node n) {
            return this.w - n.w;
        }
    }
}
