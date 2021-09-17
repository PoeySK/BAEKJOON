package DFSandBFS;

import java.io.*;
import java.util.*;

class SaveE implements Comparable<SaveE> {
    int village1;
    int village2;
    int value;

    public SaveE(int village1, int village2, int value) {
        this.village1 = village1;
        this.village2 = village2;
        this.value = value;
    }

    public SaveE(int village2, int value) {
        this.village2 = village2;
        this.value = value;
    }

    @Override
    // 가중치에 따른 오름차순 정렬
    public int compareTo(SaveE s) {
        return this.value - s.value;
    }

}

public class Evil {
    static ArrayList<SaveE> array; // 마을 정보
    static ArrayList<SaveE>[] maxArray; // 가장 큰 경로 파악
    static int[] p; // 부모 노드 배열
    static boolean[] check; // 방문 확인
    static int maxSum; // 최대 경로의 값을 구하기 위한 변수
    static int startPoint; // dfs의 시작점
    static int N, K; // 마을의 수, 교역로의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        K = Integer.parseInt(input1[1]);
        array = new ArrayList<>();
        maxArray = new ArrayList[N];
        p = new int[N];

        for (int i = 0; i < N; i++) {
            p[i] = i;
            maxArray[i] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            String[] input2 = br.readLine().split(" ");
            int v1 = Integer.parseInt(input2[0]);
            int v2 = Integer.parseInt(input2[1]);
            int value = Integer.parseInt(input2[2]);

            array.add(new SaveE(v1, v2, value));
        }

        Collections.sort(array);

        int minSum = 0;
        for (int i = 0; i < K; i++) {
            int v1 = array.get(i).village1;
            int v2 = array.get(i).village2;
            int v1P = FindParent(v1);
            int v2P = FindParent(v2);
            if (v1P != v2P) {
                minSum += array.get(i).value;
                Union(v1P, v2P);
                maxArray[v1].add(new SaveE(v2, array.get(i).value)); // 최단 경로로 설정 되어 있는 값들만 입력 받음.
                maxArray[v2].add(new SaveE(v1, array.get(i).value));
            }
        }
        for (int i = 0; i < maxArray.length - 1; i++) {
            if (!maxArray[i].isEmpty()) {
                Collections.sort(maxArray[i]);
            }
        }

        /*
         * 최단 경로 중 최대 비용을 구하기 위해 0부터 시작해서 가장 끝 지점을 찾고
         * 다시 dfs를 돌려서 최대 비용을 찾는 방법이다.
         */

        // 끝 지점 찾기
        maxSum = Integer.MIN_VALUE;
        check = new boolean[N];
        check[0] = true;
        dfs(0, 0);
        
        // 최대 비용 찾기
        maxSum = Integer.MIN_VALUE;
        check = new boolean[N];
        check[startPoint] = true;
        dfs(startPoint, 0);

        bw.write(minSum + "\n" + maxSum);
        bw.flush();
        br.close();
        bw.close();

    }

    // 부모 노드 찾기
    public static int FindParent(int x) {
        if (p[x] == x) { // 자기 자신이 부모 노드면 자기 자신을 반환
            return x;
        } else { // 재귀적으로 탐색하여 최종 부모 노드를 반환
            return FindParent(p[x]);
        }
    }

    // 노드를 합쳐줌.
    public static void Union(int x, int y) {
        /*
         *부모 노드를 찾고 그 둘의 값이 다르면 값을 바꾸어 노드를 합침.
         */
        x = FindParent(x);
        y = FindParent(y);

        if (x != y) {
            // 보다 작은 것을 부모 노드로 가짐.
            if (x > y) {
                p[x] = y;
            } else {
                p[y] = x;
            }
        }
    }

    public static void dfs(int start, int totalSum) {
        if (maxSum < totalSum) {
            maxSum = totalSum;
            startPoint = start; // 합이 가장 큰 지점이 가장 끝 지점이 됨. (최대 비용이 아닌 모든 경로를 파악했을 시)
        }
        for (int i = 0; i < maxArray[start].size(); i++) {
            SaveE nextV = maxArray[start].get(i);
            if (!check[nextV.village2]) {
                check[nextV.village2] = true;
                dfs(nextV.village2, totalSum + nextV.value); // 재귀적으로 돌리면서 totalSum에 값을 추가해 준다.
            }
        }
    }

}
