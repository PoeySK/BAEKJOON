package SegmentTree;

import java.io.*;

public class PrefixSum {
    static int N, M, K;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");

        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        K = Integer.parseInt(s[2]);

        long[] array = new long[N + 1];
        int size = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[(1 << (size + 1)) + 1];
        for (int i = 1; i < N + 1; i++) {
            long var = Long.parseLong(br.readLine());
            array[i] = var;
        }
        build(array, 1, 1, N);

        int test = M + K;
        while (test-- > 0) {
            String[] input = br.readLine().split(" ");
            int flag = Integer.parseInt(input[0]);
            switch (flag) {
                case 1:
                    int index = Integer.parseInt(input[1]);
                    long var = Long.parseLong(input[2]);
                    long diff = var - array[index];
                    array[index] = var;
                    update(1, 1, N, index, diff);
                    break;
                case 2:
                    int left = Integer.parseInt(input[1]);
                    int right = Integer.parseInt(input[2]);
                    bw.write(prefix(1, 1, N, left, right) + "\n");
                    break;
            }
        }
        br.close();
        bw.close();
    }

    private static long build(long[] arr, int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;
        return tree[node] = build(arr, node * 2, start, mid) + build(arr, node * 2 + 1, mid + 1, end);
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
        long leftPrefix = prefix(node * 2, start, mid, left, right);
        long rightPrefix = prefix(node * 2 + 1, mid + 1, end, left, right);
        return leftPrefix + rightPrefix;
    }
}
