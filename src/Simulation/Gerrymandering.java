package Simulation;

import java.io.*;
import java.util.*;

public class Gerrymandering {
    static int N, result = Integer.MAX_VALUE;
    static ArrayList<Node> field = new ArrayList<>();
    static boolean[] isBfs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        field.add(new Node(0, new int[] {0})); // 0번 구역, thrash
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] people = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            field.add(new Node(people[i], arr));
        }
        br.close();

        /* 작동 */
        run(1, new boolean[N + 1]);

        /* 출력 */
        bw.write(result == Integer.MAX_VALUE ? "-1" : result + "\n"); // 불가능 -1 출력
        bw.close();
    }

    private static void run(int idx, boolean[] isVisit) {
        if (idx == N) {
            isBfs = new boolean[N + 1];
            int count = 0; // 구역 개수
            for (int i = 1; i <= N; i++) {
                if (!isBfs[i]) {
                    isBfs[i] = true;
                    bfs(isVisit, i, isVisit[i]);
                    count++;
                }
            }

            if (count == 2) { // 구역이 2개 나뉜 경우
                result = Math.min(result, cha(isVisit));
            }

            return;
        }

        // 부분 집합
        isVisit[idx] = true;
        run(idx + 1, isVisit);
        isVisit[idx] = false;
        run(idx + 1, isVisit);

    }

    private static void bfs(boolean[] isSame, int start, boolean area) { // true 영역, false 영역 판별
        Queue<Integer> q = new ArrayDeque<>();

        // 같은 지역끼리 bfs 탐색
        boolean[] isCheck = new boolean[N + 1]; // 탐색 저장
        q.add(start);
        isCheck[start] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            int len = field.get(now).adj.length;
            for (int i = 0; i < len; i++) {
                int next = field.get(now).adj[i];
                if (!isCheck[next] && area == isSame[next]) { // 같은 지역만 탐색
                    q.offer(next);
                    isCheck[next] = true;
                    isBfs[next] = true;
                }
            }
        }
    }

    private static int cha(boolean[] isVisit) { // 인구수 차이
        int trueArea = 0;
        int falseArea = 0;
        for (int i = 1; i <= N; i++) {
            if (isVisit[i]) {
                trueArea += field.get(i).people;
            } else {
                falseArea += field.get(i).people;
            }
        }

        return Math.abs(trueArea - falseArea);
    }

    static class Node {
        int people; // 인구 수
        int[] adj; // 인접 리스트

        Node(int people, int[] adj) {
            this.people = people;
            this.adj = adj;
        }
    }
}
