package DisjointSet;

import java.io.*;
import java.util.*;

public class Lie {
    static int N, M, K, result;
    static int[] parents;
    static boolean[] isKnows;
    static ArrayList<Integer> knows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 출력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) parents[i] = i;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        isKnows = new boolean[N + 1];
        knows = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int know = Integer.parseInt(st.nextToken());
            knows.add(know);
            isKnows[know] = true;
        }

        int[][] arr = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            arr[i] = new int[cnt];
            for (int j = 0; j < cnt; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        /* 작동 */
        result = M;
        run(arr);

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int[][] arr) {
        for (int i = 0; i < M; i++) {
            if (arr[i].length > 1) {
                for (int j = 1; j < arr[i].length; j++) {
                    if (isKnows[arr[i][j - 1]] && isKnows[arr[i][j]]) continue;
                    union(arr[i][j - 1], arr[i][j]);
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (isKnows[find(arr[i][j])]) {
                    result--;
                    break;
                }
            }
        }
    }

    private static void union(int a, int b) {
        int pa = find(parents[a]);
        int pb = find(parents[b]);

        if (pa < pb) parents[pb] = pa;
        else if (pa > pb) parents[pa] = pb;

        if (isKnows[pa]) isKnows[pb] = true;
        if (isKnows[pb]) isKnows[pa] = true;
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
}
