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

        long[] array = new long[N];
        tree = new long[4 * N];
        for (int i = 0; i < N; i++) {
            long var = Long.parseLong(br.readLine());
            array[i] = var;
        }
        Build(array, 1, 0, N - 1);

        int test = M + K;
        while (test-- > 0) {
            String[] input = br.readLine().split(" ");
            int flag = Integer.parseInt(input[0]);
            switch (flag) { // 1: update, 2: query
                case 1:
                    int index = Integer.parseInt(input[1]);
                    long var = Long.parseLong(input[2]);
                    long diff = var - array[index - 1]; // 기존의 값과 변경 값의 차이
                    array[index - 1] = var; // 기존 입력 배열도 수정을 해주어야 다음 update에서도 적용 가능
                    Update(1, 0, N - 1, index - 1, diff);
                    break;
                case 2: // index는 long타입으로 하지 않아도 됨. (N의 크기는 1,000,000)
                    int left = Integer.parseInt(input[1]);
                    int right = Integer.parseInt(input[2]);
                    long sum = Query(1, 0, N - 1, left - 1, right - 1);
                    bw.write(sum + "\n");
                    break;
                default:
                    break;
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void Build(long[] array, int node, int start, int end) {
        if (start == end) { // leaf node 위치
            tree[node] = array[start];
        } else {
            // parameter node 위치에 값들이 저장됨.
            int mid = (start + end) / 2;
            Build(array, 2 * node, start, mid);
            Build(array, 2 * node + 1, mid + 1, end);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    public static long Query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) { // index 범위 밖
            return 0;
        }
        if (left <= start && end <= right) { // [left, right]가 [start, end]에 완전히 포함되는 경우
            return tree[node];
        }
        int mid = (start + end) / 2;
        long leftNode = Query(2 * node, start, mid, left, right);
        long rightNode = Query(2 * node + 1, mid + 1, end, left, right);
        return leftNode + rightNode;
    }

    public static void Update(int node, int start, int end, int index, long diff) {
        if (index < start || end < index) { // index 범위 밖
            return;
        }
        tree[node] += diff;
        if (start == end) { // leaf node 위치
            return;
        }
        int mid = (start + end) / 2;
        Update(2 * node, start, mid, index, diff);
        Update(2 * node + 1, mid + 1, end, index, diff);

    }
}