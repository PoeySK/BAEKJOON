package GraphTheory;

import java.io.*;
import java.util.*;

public class Transfer {
    static int N, K, M, result = Integer.MAX_VALUE;
    static ArrayList<Integer>[] tubes;
    static ArrayList<Integer>[] stations;
    static boolean[] isVisitTube;
    static boolean[] isVisitStation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tubes = new ArrayList[M];
        stations = new ArrayList[N + 1];
        for (int i = 0; i < M; i++) {
            tubes[i] = new ArrayList<>();
        }
        for (int i = 0; i < N + 1; i++) {
            stations[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int station = Integer.parseInt(st.nextToken());
                tubes[i].add(station);
                stations[station].add(i);
            }
        }
        br.close();

        if (N == 1) {
            bw.write(1 + "\n");
            bw.close();
            return;
        }
        run();

        bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n");
        bw.close();
    }

    private static void run() {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(1, 1));

        isVisitTube = new boolean[M];
        isVisitStation = new boolean[N + 1];
        isVisitStation[1] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int tube : stations[now.s]) {
                if (isVisitTube[tube]) continue;
                isVisitTube[tube] = true;
                for (int station : tubes[tube]) {
                    if (isVisitStation[station]) continue;
                    isVisitStation[station] = true;
                    if (station == N) {
                        result = Math.min(result, now.count + 1);
                        return;
                    }
                    q.offer(new Node(station, now.count + 1));
                }
            }
        }
    }

    private static class Node {
        int s, count;

        Node(int s, int count) {
            this.s = s;
            this.count = count;
        }
    }
}
