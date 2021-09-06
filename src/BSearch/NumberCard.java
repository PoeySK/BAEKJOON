package BSearch;

import java.io.*;
import java.util.*;

public class NumberCard {
    static int N, M;
    static ArrayList<Integer> a;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        a = new ArrayList<>();
        String[] input_a = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(input_a[i]));
        }

        Collections.sort(a);

        M = Integer.parseInt(br.readLine());
        b = new int[M];
        String[] input_b = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            b[i] = Integer.parseInt(input_b[i]);
            BSearch(a, b[i]);
        }
        br.close();

    }

    static void BSearch(ArrayList<Integer> array, int value) throws IOException {
        int low = 0;
        int high = N - 1;
        int mid;
        int check = 0;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (low <= high) {
            mid = (low + high) / 2;

            if (array.get(mid) < value) {
                low = mid + 1;
            } else if (array.get(mid) > value) {
                high = mid - 1;
            } else {
                check++;
                if (N / 2 > mid) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

        }
        bw.write(check + " ");
        bw.flush();

    }
}
