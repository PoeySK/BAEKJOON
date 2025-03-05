import java.io.*;
import java.util.*;

public class GoTravel {
    static int N, M;
    static int[][] city;
    static boolean[][] isAble;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        br.close();

        /* 작동 */
        isAble = new boolean[N][N];
        int from = Integer.parseInt(st.nextToken()), to;
        for (int i = 0; i < M - 1; i++) {
            to = Integer.parseInt(st.nextToken());
            if (isAble[from - 1][to - 1]) continue;
            if (!run(from - 1, to - 1)) {
                bw.write("NO");
                bw.close();
                return;
            }
            isAble[from - 1][to - 1] = true;
            from = to;
        }

        /* 출력 */
        bw.write("YES");
        bw.close();
    }

    private static boolean run(int start, int end) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        boolean[] isVisit = new boolean[N];
        isVisit[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == end) {
                return true;
            }

            for (int i = 0; i < city[now].length; i++) {
                if (city[now][i] == 1 && !isVisit[i]) {
                    isVisit[i] = true;
                    q.offer(i);
                }
            }
        }

        return false;
    }
}
