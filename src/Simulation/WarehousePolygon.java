package Simulation;

import java.io.*;
import java.util.*;

public class WarehousePolygon {
    static int N, result;
    static Node[] warehouses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        warehouses = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            warehouses[i] = new Node(L, H);
        }
        br.close();

        Arrays.sort(warehouses);
        run();

        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int length = warehouses[0].l;
        int height = warehouses[0].h;
        int highIdx = 0;
        for (int i = 1; i < N; i++) {
            Node warehouse = warehouses[i];
            if (height <= warehouse.h) {
                result += (warehouse.l - length) * height;

                length = warehouse.l;
                height = warehouse.h;
                highIdx = i;
            }
        }

        length = warehouses[N - 1].l;
        height = warehouses[N - 1].h;
        for (int i = N - 2; i > highIdx; i--) {
            Node warehouse = warehouses[i];
            if (height < warehouse.h) {
                result += (length - warehouse.l) * height;

                length = warehouse.l;
                height = warehouse.h;
            }
        }
        result += warehouses[highIdx].h;
        result += (length - warehouses[highIdx].l) * height;
    }

    private static class Node implements Comparable<Node> {
        int l, h;

        Node(int l, int h) {
            this.l = l;
            this.h = h;
        }

        @Override
        public int compareTo(Node o) {
            return this.l - o.l;
        }
    }
}
