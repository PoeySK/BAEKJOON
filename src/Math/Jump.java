package Math;

import java.io.*;
import java.util.*;

public class Jump {
    static int N, M, result;
    static int[][] field;
    static boolean[] smallStones;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        smallStones = new boolean[N + 1];
        smallStones[1] = true;

        int jMax = (int) Math.sqrt(2 * N) + 2; // 최대 점프 가능
        field = new int[N + 1][jMax + 1];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(field[i], Integer.MAX_VALUE);
        }
        field[1][1] = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            smallStones[Integer.parseInt(st.nextToken())] = true;
        }
        br.close();

        run(jMax);

        bw.write(result + "\n");
        bw.close();
    }

    private static void run(int jMax) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        if (smallStones[2]) {
            result = -1;
            return;
        }
        field[2][1] = 1;
        q.offer(new Node(2, 1, 1));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.pos == N) {
                result = now.count;
                return;
            }

            for (int i = -1; i < 2; i++) {
                int next = now.jump + i;
                if (next < 1 || next > jMax || now.pos + next > N || smallStones[now.pos + next] || field[now.pos + next][next] <= now.count + 1)
                    continue;

                field[now.pos + next][next] = now.count + 1;
                q.offer(new Node(now.pos + next, next, now.count + 1));
            }
        }
        result = -1;


    }

    private static class Node {
        int pos, jump, count;

        Node(int pos, int jump, int count) {
            this.pos = pos;
            this.jump = jump;
            this.count = count;
        }
    }
}
