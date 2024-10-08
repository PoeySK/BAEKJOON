package Mst;

import java.io.*;
import java.util.*;

public class PlanetaryTunnel {
    static int N, E, result;
    static Node[] planetaryByX;
    static Node[] planetaryByY;
    static Node[] planetaryByZ;
    static PriorityQueue<Edge> edges;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        planetaryByX = new Node[N];
        planetaryByY = new Node[N];
        planetaryByZ = new Node[N];
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            planetaryByX[i] = new Node(x, i);
            planetaryByY[i] = new Node(y, i);
            planetaryByZ[i] = new Node(z, i);

            parents[i] = i;
        }
        br.close();
        /* 작동 */
        // x, y, z 별 정렬
        Arrays.sort(planetaryByX);
        Arrays.sort(planetaryByY);
        Arrays.sort(planetaryByZ);
        edges = new PriorityQueue<>();
        run();
        Arrays.sort(planetaryByX);
        Arrays.sort(planetaryByY);
        Arrays.sort(planetaryByZ);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        findTunnel();
        while(E < N - 1) {
            Edge edge = edges.poll();
            union(edge.u, edge.v, edge.w);
        }
    }

    private static void union(int a, int b, int w) {
        int pa = find(a);
        int pb = find(b);
        if(pa != pb) {
            parents[pb] = pa;
            result += w;
            E++;
        }
    }

    private static int find(int a) {
        if(parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    private static void findTunnel() {
        int idx = 0;
        for (int i = 1; i < N; i++) {
            int xLen = planetaryByX[i].a - planetaryByX[i - 1].a;
            int yLen = planetaryByY[i].a - planetaryByY[i - 1].a;
            int zLen = planetaryByZ[i].a - planetaryByZ[i - 1].a;

            edges.offer(new Edge(planetaryByX[i].number, planetaryByX[i - 1].number, xLen));
            edges.offer(new Edge(planetaryByY[i].number, planetaryByY[i - 1].number, yLen));
            edges.offer(new Edge(planetaryByZ[i].number, planetaryByZ[i - 1].number, zLen));
        }
    }

    static class Node implements Comparable<Node> {
        int a, number;

        Node(int a, int number) {
            this.a = a;
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            return this.a - o.a;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}
