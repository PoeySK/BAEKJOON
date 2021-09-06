package DFSandBFS;

import java.io.*;
import java.util.*;

public class Basic {
    static ArrayList<Integer>[] array;
    static int N, M, V;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        V = Integer.parseInt(s[2]);

        array = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) { // 1 <= N <= 1000
            array[i] = new ArrayList<>();
        }

        for (int i = 1; i < M + 1; i++) { // 1 <= M <= 10000
            String[] num = br.readLine().split(" ");
            int parent = Integer.parseInt(num[0]);
            int child = Integer.parseInt(num[1]);
            array[parent].add(child);
            array[child].add(parent);
        }

        for (int i = 1; i < N + 1; i++) {
            Collections.sort(array[i]);
        }
        check = new boolean[N + 1]; // 초기값은 false
        dfs(V);
        System.out.println();

        check = new boolean[N + 1];
        bfs(V);
        System.out.println();

        br.close();
    }

    private static void dfs(int start) {
        if (check[start]) { // 값이 true면 함수를 끝냄.
            return;
        }
        check[start] = true;
        System.out.print(start + " ");
        for (int y : array[start]) {
            if (!check[y]) {
                dfs(y);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        check[start] = true;

        while(!queue.isEmpty()){
            int x = queue.poll();
            System.out.print(x + " ");

            for(int y : array[x]){
                if(!check[y]){
                    check[y] = true;
                    queue.add(y);
                }
            }
        }

    }
}
/*
5 5 3
5 4
5 2
1 2
3 4
3 1
ㅡㅡㅡ
Dfs 3 1 2 5 4
Bfs 3 1 4 2 5
ㅡㅡㅡ
5 2 4
4 . .
3 1 4
2 . .
1 2 .

 */