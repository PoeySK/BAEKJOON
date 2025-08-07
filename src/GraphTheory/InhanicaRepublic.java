package GraphTheory;

import java.io.*;
import java.util.*;

public class InhanicaRepublic {
    static int N, M;
    static boolean[] isVisit;
    static ArrayList<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            adj = new ArrayList[N + 1];
            for (int i = 1; i < N + 1; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                adj[u].add(new Node(v, d));
                adj[v].add(new Node(u, d));
            }

            int result = 0;
            isVisit = new boolean[N + 1];
            isVisit[1] = true;
            for (int i = 0; i < adj[1].size(); i++) {
                Node next = adj[1].get(i);
                isVisit[next.v] = true;
                result += run(next.v, next.d);
            }

            bw.write(result + "\n");
        }
        br.close();
        bw.close();
    }

    private static int run(int now, int pDynamite) {
        int dynamite = 0;
        for (int i = 0; i < adj[now].size(); i++) {
            Node next = adj[now].get(i);
            if (isVisit[next.v]) continue;
            isVisit[next.v] = true;

            if (adj[next.v].size() == 1) {
                dynamite += next.d;
            } else {
                dynamite += run(next.v, next.d);
            }
        }
        if (dynamite == 0) return pDynamite;
        return Math.min(dynamite, pDynamite);
    }

    private static class Node {
        int v, d;

        Node(int v, int d) {
            this.v = v;
            this.d = d;
        }
    }
}
