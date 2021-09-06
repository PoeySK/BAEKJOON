package DFSandBFS;

import java.io.*;
import java.util.*;

public class Virus {
    static ArrayList<Integer>[] array;
    static boolean[] check;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int com = Integer.parseInt(br.readLine());
        int net = Integer.parseInt(br.readLine());

        array = new ArrayList[com + 1];

        for (int i = 0; i < com + 1; i++) {
            array[i] = new ArrayList<>();
        }

        for (int i = 0; i < net; i++) {
            String[] v = br.readLine().split(" ");
            int par = Integer.parseInt(v[0]);
            int chi = Integer.parseInt(v[1]);
            array[par].add(chi);
            array[chi].add(par);
        }
        for (int i = 0; i < com + 1; i++) {
            Collections.sort(array[i]);
        }

        check = new boolean[com + 1];
        dfs(1);
        System.out.println((count - 1));
    }

    static void dfs(int start){
        if(check[start]){
            return;
        }
        check[start] = true;

        for(int x : array[start]){
            if(!check[x]){
                dfs(x);
            }
        }
        count++;
    }
}