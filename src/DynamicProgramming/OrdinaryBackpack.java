package DynamicProgramming;

import java.io.*;
import java.util.*;

class SaveOB implements Comparable<SaveOB> {
    int weight;
    int value;

    public SaveOB(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int compareTo(SaveOB s) {
        return this.weight - s.weight;
    }
}

public class OrdinaryBackpack {
    static ArrayList<SaveOB> weight_list;
    static int K, index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]); // 물품의 개수
        K = Integer.parseInt(s[1]); // 총 무게
        weight_list = new ArrayList<>();
        weight_list.add(new SaveOB(0, 0));

        for (int i = 0; i < N; i++) {
            String[] st = br.readLine().split(" ");
            int W = Integer.parseInt(st[0]); // 물건의 무게
            int V = Integer.parseInt(st[1]); // 물건의 가치
            weight_list.add(new SaveOB(W, V));
        }



        bw.flush();
        br.close();
        bw.close();
    }

    static int dp(int index, int weight) {
        return 0;
    }

}