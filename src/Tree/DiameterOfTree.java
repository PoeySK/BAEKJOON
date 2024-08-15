package Tree;

import java.io.*;
import java.util.*;

public class DiameterOfTree {
    static int N, start, result;
    static ArrayList<Node>[] graph;
    static boolean[] isCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        if (N == 1) { // 노드가 하나인 경우 지름은 0
            bw.write(0 + "\n");
            bw.close();
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }
        br.close();

        /* 작동 */
        // 루트에서 가장 먼 leaf 찾기
        isCheck = new boolean[N + 1];
        isCheck[1] = true;
        run(1, 0);

        // 위에서 찾은 leaf에서 가장 먼 leaf 찾기
        result = 0; // 다시 찾기
        isCheck = new boolean[N + 1];
        isCheck[start] = true;
        run(start, 0); // 찾은 노드 이후

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int now, int total) { // leaf 찾기
        if (result < total) { // leaf node 도달
            result = total;
            start = now;
        }

        int size = graph[now].size();
        for (int i = 0; i < size; i++) {
            Node next = graph[now].get(i);
            if (!isCheck[next.v]) {
                isCheck[next.v] = true;
                run(next.v, total + next.w);
            }
        }
    }

    static class Node {
        int v; // 연결 노드
        int w; // 가중치

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
