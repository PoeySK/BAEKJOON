package Simulation;

import java.io.*;
import java.util.*;

public class Truck {
    static int N, W, L, result;
    static int[] trucks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        trucks = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        /* 작동 */
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        int time = 1;
        int idx = 0;
        Queue<Integer> bridge = new ArrayDeque<>();
        for (int i = 0; i < W - 1; i++) { // 트럭 하나 들어갈 공간
            bridge.offer(0);
        }
        int now = trucks[idx]; // 다리 무게
        bridge.offer(trucks[idx++]); // 다리에 올라간 트럭
        while (!bridge.isEmpty()) {
            time++;

            now -= bridge.poll();
            if (idx == N) continue; // 마지막 트럭

            if (now + trucks[idx] <= L) {
                now += trucks[idx];
                bridge.offer(trucks[idx++]);
            } else {
                bridge.offer(0);
            }
        }

        result = time;
    }
}
