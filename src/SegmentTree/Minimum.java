package SegmentTree;

import java.io.*;

public class Minimum {
    static int N, M;
    static int[] array;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        array = new int[N];
        tree = new int[4 * N];
        for (int i = 0; i < N; i++) {
            int var = Integer.parseInt(br.readLine());
            array[i] = var;
        }

        Build(1, 0, N - 1);

        while (M-- > 0) {
            String[] input = br.readLine().split(" ");
            int left = Integer.parseInt(input[0]);
            int right = Integer.parseInt(input[1]);
            int min = Query(1, 0, N - 1, left - 1, right - 1);
            bw.write(min + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void Build(int node, int start, int end) {
        if (start == end) { // leaf node
            tree[node] = array[start];
            return;
        }

        int mid = (start + end) / 2;
        Build(2 * node, start, mid); // left
        Build(2 * node + 1, mid + 1, end); //right

        tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]); // leftChild와 rightChild 중 최솟값 저장
    }

    public static int Query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }
        if (left <= start && end <= right) { // [left, right]가 [start, end]를 모두 포함하는 경우
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftNode = Query(2 * node, start, mid, left, right);
        int rightNode = Query(2 * node + 1, mid + 1, end, left, right);
        return Math.min(leftNode, rightNode);
    }
}