package SegmentTree;

import java.util.*;
import java.io.*;

public class HouseholdLedgerHard {
    static int N, Q;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int size = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[(1 << (size + 1)) + 1];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            switch (query) {
                case 1:
                    int idx = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    update(1, 1, N, idx, value);
                    break;
                case 2:
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());
                    bw.write(prefix(1, 1, N, left, right) + "\n");
                    break;
            }
        }
        bw.close();
    }

    private static void update(int node, int start, int end, int index, long diff) {
        if (index < start || index > end) return;

        tree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index, diff);
            update(node * 2 + 1, mid + 1, end, index, diff);
        }
    }

    private static long prefix(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return tree[node];

        int mid = (start + end) / 2;
        long leftSum = prefix(node * 2, start, mid, left, right);
        long rightSum = prefix(node * 2 + 1, mid + 1, end, left, right);

        return leftSum + rightSum;
    }
}
