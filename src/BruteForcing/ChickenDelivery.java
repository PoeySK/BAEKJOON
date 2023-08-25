package BruteForcing;

import java.io.*;
import java.util.*;

public class ChickenDelivery {
    static int N, M, result;
    static int[][] city;
    static boolean[] select;
    static ArrayList<Coordinate> house, chi;

    static class Coordinate {
        int i;
        int j;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);

        // Input
        city = new int[N][N];

        house = new ArrayList<>();
        chi = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] inf = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(inf[j]);
                if (city[i][j] == 1) {
                    house.add(new Coordinate(i, j));
                } else if (city[i][j] == 2) {
                    chi.add(new Coordinate(i, j));
                }
            }
        }

        select = new boolean[chi.size()];
        result = Integer.MAX_VALUE;
        solution(0, 0);
        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    public static void solution(int start, int count) {
        if (M == count) {
            int dis = 0;
            for (int i = 0; i < house.size(); i++) {
                int tmp = Integer.MAX_VALUE;

                for (int j = 0; j < chi.size(); j++) {
                    if (select[j]) {
                        // |r1 - r2| + |c1 - c2|
                        int distance = Math.abs(house.get(i).i - chi.get(j).i) + Math.abs(house.get(i).j - chi.get(j).j);
                        tmp = Math.min(distance, tmp);
                    }
                }
                dis += tmp;
            }
            result = Math.min(result, dis);
            return;
        }

        // Combination
        for (int i = start; i < chi.size(); i++) {
            select[i] = true;
            solution(i + 1, count + 1);
            select[i] = false;
        }
    }
}
