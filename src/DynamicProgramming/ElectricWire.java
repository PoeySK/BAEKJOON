package DynamicProgramming;

import java.io.*;
import java.util.*;

public class ElectricWire {
    static int N;
    static ArrayList<Save> array;
    static int[] sol;

    static class Save implements Comparable<Save> {
        int A;
        int B;

        public Save(int A, int B) {
            this.A = A;
            this.B = B;
        }


        @Override
        public int compareTo(Save o) {
            return this.A - o.A;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        array = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            array.add(new Save(A, B));
        }
        br.close();
        Collections.sort(array);
        sol = new int[N];
        LIS();
        int wire = 0; // 가장 길게 이어지는 전깃줄
        for (int i = 0; i < N; i++) {
            wire = Math.max(wire, sol[i]);
        }

        int result = N - wire; // 잘라야할 전깃줄(result) = 전체 전깃줄(N) - 가장 길게 이어지는 전깃줄(wire)
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    public static void LIS() {
        /*
        * 전깃줄이 교차하지 않는 조건
        * A 시작점 < B 시작점
        * A 도착점 < B 도착점
        *
        * A 시작점 > B 시작점
        * A 시작점 > B 시작점
        * -----------------------
        * 전깃줄이 교차하는 조건
        * A 시작점 < B 시작점
        * A 도착점 > B 도착점
        *
        * A 시작점 > B 시작점
        * A 도착점 < B 도착점
        * -----------------------
        * @등호가 동일하면 교차하지 않음.
        * 한 기둥 정렬 후 '계속 증가하는 수열 찾기' => LIS 알고리즘
        */

        sol[0] = 1;
        for (int i = 1; i < N; i++) {
            sol[i] = 1;
            for (int j = 0; j < i; j++) {
                if(array.get(j).B < array.get(i).B && sol[i] <= sol[j]) {
                    sol[i] = sol[j] + 1;
                }
            }
        }
    }
}
