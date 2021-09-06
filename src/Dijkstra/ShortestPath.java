package Dijkstra;

import java.io.*;
import java.util.*;

class NodeSP implements Comparable<NodeSP> {
    int value, weight; // 정점, 가중치

    public NodeSP(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public int compareTo(NodeSP n) {
        return this.weight - n.weight; // 가중치 오름차순 정렬
    }
}

public class ShortestPath {
    static int V, E, K;
    static int[] distance;
    static LinkedList<NodeSP>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");

        V = Integer.parseInt(s[0]); // 정점의 개수
        E = Integer.parseInt(s[1]); // 간선의 개수
        K = Integer.parseInt(br.readLine()); // 시작 정점의 번호

        list = new LinkedList[V + 1];
        distance = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new LinkedList<>();
            distance[i] = -1; // 모든 값을 -1로 초기화
        }

        for (int i = 0; i < E; i++) {
            String[] st = br.readLine().split(" ");
            int u = Integer.parseInt(st[0]); // 출발
            int v = Integer.parseInt(st[1]); // 끝
            int w = Integer.parseInt(st[2]); // 출발에서 끝까지의 가중치

            list[u].add(new NodeSP(v, w));
        }

        Run(K);

        for (int i = 1; i <= V; i++) {
            if (distance[i] < 0) { // 초기화 값인 -1이 그대로 남아있으면 "INF" 출력
                bw.write("INF\n");
            } else {
                bw.write(distance[i] + "\n");
            }
        }
        bw.flush();
    }

    static void Run(int start) { // 거리 계산 실행 부
        PriorityQueue<NodeSP> pq = new PriorityQueue<>();
        boolean[] check = new boolean[V + 1]; // 방문 여부 확인
        pq.add(new NodeSP(start, 0)); // 시작 지점
        distance[start] = 0;

        while (!pq.isEmpty()) {
            NodeSP now = pq.poll();

            if (check[now.value]) { // 방문 했다면 아래의 코드를 실행하지 않고 처음으로 돌아감.
                continue;
            }

            check[now.value] = true; // 방문 하면 true로 값을 지정

            for (NodeSP nsp : list[now.value]) {
                // 아직 방문하지 않은곳이나 가중치와 거리를 비교했을 때 더 큰 곳이 있으면 실행함.
                if (distance[nsp.value] == -1 || distance[nsp.value] > distance[now.value] + nsp.weight) {
                    distance[nsp.value] = distance[now.value] + nsp.weight;
                    pq.add(new NodeSP(nsp.value, distance[nsp.value]));
                }
            }

        }
    }
}