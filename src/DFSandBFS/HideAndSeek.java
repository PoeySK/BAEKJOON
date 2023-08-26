package DFSandBFS;

import java.io.*;
import java.util.*;

public class HideAndSeek {
    static int N, K;
    static boolean[] check; // 해당 좌표 방문 체크
    static int[] distance; // 시작부터 해당 좌표까지의 최소거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        check = new boolean[100001]; // 최대 범위 100,000
        distance = new int[100001];

        Move();

        bw.write(distance[K] + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static void Move() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        check[N] = true;
        while (!q.isEmpty()) {
            int now = q.poll();

            ArrayList<Integer> test = new ArrayList<>();
            if (now < 100000 && !check[now + 1]) { // x + 1
                test.add(now + 1);
            }
            if (now > 0 && !check[now - 1]) { // x - 1
                test.add(now - 1);
            }
            if (now * 2 <= 100000 && !check[now * 2]) { // x * 2
                test.add(now * 2);
            }

            for (int i = 0; i < test.size(); i++) {
                // 해당 좌표에 도달한 최소 거리 구하기
                int idx = test.get(i);
                if (!check[idx]) {
                    check[idx] = true;
                    distance[idx] = distance[now] + 1;
                    q.offer(idx);
                }
            }
        }
    }
}
