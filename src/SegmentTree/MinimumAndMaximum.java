package SegmentTree;

import java.io.*;

public class MinimumAndMaximum {
    static int N, M;
    static int[] array;
    static int[] minTree;
    static int[] maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        array = new int[N];
        minTree = new int[4 * N];
        maxTree = new int[4 * N];

        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            array[i] = val;
        }

        minBuild(1, 0, N - 1);
        maxBuild(1, 0, N - 1);

        int min = 0;
        int max = 0;
        while (M-- > 0) {
            String[] input = br.readLine().split(" ");
            int l = Integer.parseInt(input[0]);
            int r = Integer.parseInt(input[1]);

            // index는 0부터 시작이므로 left, right에 1을 빼줌.
            min = minQuery(minTree, 1, 0, N - 1, l - 1, r - 1);
            max = maxQuery(maxTree, 1, 0, N - 1, l - 1, r - 1);

            bw.write(min + " " + max + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static int minBuild(int node, int start, int end) {
        if (start == end) { // single element
            return minTree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            int leftChild = minBuild(2 * node, start, mid);
            int rightChild = minBuild(2 * node + 1, mid + 1, end);
            return minTree[node] = Math.min(leftChild, rightChild);
        }

    }

    public static int maxBuild(int node, int start, int end) {
        if (start == end) { // single element
            return maxTree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            int leftChild = maxBuild(2 * node, start, mid);
            int rightChild = maxBuild(2 * node + 1, mid + 1, end);
            return maxTree[node] = Math.max(leftChild, rightChild);
        }
    }

    public static int minQuery(int[] nodeList, int node, int start, int end, int left, int right) {
        if (right < start || end < left) { // [left, right] 범위에 [start, end]가 포함되지 않을 때
            return Integer.MAX_VALUE;
        }

        if (start >= left && end <= right) { // 범위에 완전히 포함되는 경우 tree에 저장해둔 node 반환
            return nodeList[node];
        }

        // node 자식 노드를 찾아서 비교
        int mid = (start + end) / 2;
        int leftMin = minQuery(nodeList, 2 * node, start, mid, left, right);
        int rightMin = minQuery(nodeList, 2 * node + 1, mid + 1, end, left, right);

        return Math.min(leftMin, rightMin);
    }

    public static int maxQuery(int[] nodeList, int node, int start, int end, int left, int right) { // minQuery와 같은 구조
        if (right < start || end < left) { // [left, right] 범위에 [start, end]가 포함되지 않을 때
            return 0;
        }

        if (start >= left && end <= right) { // 범위에 완전히 포함되는 경우 tree에 저장해둔 node 반환
            return nodeList[node];
        }

        // node 자식 노드를 찾아서 비교
        int mid = (start + end) / 2;
        int leftMax = maxQuery(nodeList, 2 * node, start, mid, left, right);
        int rightMax = maxQuery(nodeList, 2 * node + 1, mid + 1, end, left, right);

        return Math.max(leftMax, rightMax);
    }

}
