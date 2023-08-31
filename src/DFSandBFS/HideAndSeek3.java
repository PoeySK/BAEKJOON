package DFSandBFS;

import java.io.*;
import java.util.*;

public class HideAndSeek3 {
    static int N, K;
    static boolean[] check; // 해당 좌표 방문 체크

    static class Save {
        int idx;
        int count;

        public Save(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        check = new boolean[100001]; // 최대 범위 100,000

        int result = Move();

        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static int Move() {
        Queue<Save> q = new LinkedList<>();
        q.offer(new Save(N, 0));
        check[N] = true;
        int rlt = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Save now = q.poll();
            check[now.idx] = true;
            if (now.idx == K) { // K 좌표에 도착할 때 마다 최솟값 찾기
                rlt = Math.min(rlt, now.count);
            }
            if (now.idx < 100000 && !check[now.idx + 1]) { // x + 1
                q.offer(new Save(now.idx + 1, now.count + 1));
            }
            if (now.idx > 0 && !check[now.idx - 1]) { // x - 1
                q.offer(new Save(now.idx - 1, now.count + 1));
            }
            if (now.idx * 2 <= 100000 && !check[now.idx * 2]) { // x * 2
                q.offer(new Save(now.idx * 2, now.count));
            }
        }
        return rlt;
    }
}
