package GraphTheory;

import java.io.*;
import java.util.*;

public class BeerWalk {
    static int N;
    static Node sang, festival;
    static Node[] stores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            sang = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            stores = new Node[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                stores[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            festival = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            bw.write((run() ? "happy" : "sad") + "\n");
        }
        br.close();
        bw.close();
    }

    private static boolean run() {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(sang.x, sang.y));
        boolean[] isVisit = new boolean[N];

        while (!q.isEmpty()) {
            Node now = q.poll();

            int nowToFestival = mLen(now.x, now.y, festival.x, festival.y);
            if (nowToFestival / 50 == 20 && nowToFestival % 50 == 0) {
                return true;
            }
            if (nowToFestival / 50 < 20) {
                return true;
            }

            for (int i = 0; i < N; i++) {
                if (isVisit[i]) continue;

                Node store = stores[i];
                int nowToStore = mLen(now.x, now.y, store.x, store.y);

                if (nowToStore / 50 == 20 && nowToStore % 50 == 0) {
                    isVisit[i] = true;
                    q.offer(new Node(store.x, store.y));
                }
                if (nowToStore / 50 < 20) {
                    isVisit[i] = true;
                    q.offer(new Node(store.x, store.y));
                }
            }
        }
        return false;
    }

    private static int mLen(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
