package PriorityQueue;

import java.io.*;
import java.util.*;

public class GardenOfPrincess {
    static int N;
    static Node[] flowers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        flowers = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m1 = Integer.parseInt(st.nextToken());
            int d1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int d2 = Integer.parseInt(st.nextToken());
            int start = m1 * 100 + d1;
            int end = m2 * 100 + d2;
            flowers[i] = new Node(start, end);
        }
        br.close();

        Arrays.sort(flowers);

        bw.write(run() + "\n");
        bw.close();
    }

    private static int run() {
        // 301 ~ 1130 (3.1 ~ 11.30)
        if (flowers[0].start > 301) return 0;
        if (flowers[0].end > 1130) return 1;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.end - o1.end);
        pq.offer(flowers[0]);
        int startIdx = 1;
        for (int i = 1; i < N; i++) {
            if (flowers[i].start > 301) {
                startIdx = i;
                break;
            }

            if (!pq.isEmpty() && pq.peek().end < flowers[i].end) {
                pq.poll();
                pq.offer(flowers[i]);
            }
        }

        for (int i = startIdx; i < N; i++) {
            if (flowers[i].start > 1130 || pq.isEmpty() || pq.peek().end > 1130) break;

            Node now = pq.peek();
            if (pq.size() > 1) {
                Node prev = pq.poll();
                if (!pq.isEmpty() && pq.peek().end >= flowers[i].start) {
                    if (prev.end < flowers[i].end) {
                        pq.offer(flowers[i]);
                    } else {
                        pq.offer(prev);
                    }
                } else {
                    if (prev.end >= flowers[i].start) {
                        pq.offer(prev);
                        if (prev.end < flowers[i].end) {
                            pq.offer(flowers[i]);
                        }
                    }
                }
            } else {
                if (now.end >= flowers[i].start) {
                    if (now.end < flowers[i].end) {
                        pq.offer(flowers[i]);
                    }
                } else {
                    return 0;
                }
            }
        }

        if (!pq.isEmpty() && pq.peek().end > 1130) {
            return pq.size();
        } else {
            return 0;
        }
    }

    private static class Node implements Comparable<Node> {
        int start, end;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if (this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }
}
