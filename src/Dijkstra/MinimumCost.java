package Dijkstra;

import java.io.*;
import java.util.*;

class NodeMC implements Comparable<NodeMC> {
    int end; // 도착지
    int value; // 비용

    public NodeMC(int end, int value) {
        this.end = end;
        this.value = value;
    }

    public int compareTo(NodeMC n) { // 비용 오름차순 정렬
        return this.value - n.value;
    }
}

public class MinimumCost {
    static ArrayList<NodeMC>[] list;
    static int[] dis;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 버스 개수

        list = new ArrayList[N + 1];
        dis = new int[N + 1]; // 건너간 숫자에 따른 거리

        Arrays.fill(dis, Integer.MAX_VALUE); // 최소거리를 찾기위해 모든 배열에 최댓값을 넣어줌.

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] s = br.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            int value = Integer.parseInt(s[2]);

            list[start].add(new NodeMC(end, value));
        }

        String[] st = br.readLine().split(" ");
        int startPoint = Integer.parseInt(st[0]);
        int endPoint = Integer.parseInt(st[1]);

        bw.write(Run(startPoint, endPoint) + "\n");
        bw.flush();

    }

    static int Run(int startPoint, int endPoint) {
        PriorityQueue<NodeMC> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1]; // 방문 처리

        pq.offer(new NodeMC(startPoint, 0));
        dis[startPoint] = 0;

        while (!pq.isEmpty()) {
            NodeMC now = pq.poll();

            if(check[now.end]){
                continue;
            }
            check[now.end] = true;

            for(NodeMC next : list[now.end]){
                if(dis[next.end] > dis[now.end] + next.value){
                    dis[next.end] = dis[now.end] + next.value;
                    pq.offer(new NodeMC(next.end, dis[next.end]));

                }
            }

        }

        return dis[endPoint];
    }
}