package GraphTheory;

import java.io.*;
import java.util.*;

public class BipartiteGraph {
    static int V, E;
    static char[] isColor;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adj = new ArrayList[V + 1];
            for (int i = 1; i < V + 1; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                adj[u].add(v);
                adj[v].add(u);
            }

            /* 작동 */
            isColor = new char[V + 1];
            boolean result = run();

            /* 출력 */
            bw.write(result ? "YES\n" : "NO\n");
        }
        br.close();
        bw.close();
    }

    private static boolean run() {
        for (int i = 1; i <= V; i++) {
            if (isColor[i] != 'R' && isColor[i] != 'B') {
                isColor[i] = 'R';
            }
            boolean isCheck = bfs(i);
            if (!isCheck) {
                return false;
            }
        }

        return true;
    }

    private static boolean bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        char nowColor = isColor[start];
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int now = q.poll();

                for (int i = 0; i < adj[now].size(); i++) {
                    int next = adj[now].get(i);
                    if (isColor[next] == 'R' || isColor[next] == 'B') {
                        if (isColor[next] == nowColor) {
                            return false;
                        }
                    } else { // 색깔 없음
                        isColor[next] = nowColor == 'R' ? 'B' : 'R';
                        q.offer(next);
                    }
                }
            }
            nowColor = nowColor == 'R' ? 'B' : 'R';
        }

        return true;
    }
}
