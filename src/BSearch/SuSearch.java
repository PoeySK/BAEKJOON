package BSearch;

import java.io.*;
import java.util.*;

public class SuSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] strN = br.readLine().split(" ");
        int M = Integer.parseInt(br.readLine());
        String[] strM = br.readLine().split(" ");

        int[] narray = new int[N];
        int[] marray = new int[M];

        for (int i = 0; i < N; i++) {
            narray[i] = Integer.parseInt(strN[i]);
        }
        for (int i = 0; i < M; i++) {
            marray[i] = Integer.parseInt(strM[i]);
        }
        Arrays.sort(narray);

        for (int i = 0; i < M; i++) {
            System.out.println(BinarySearch(narray, marray[i]));
        }

    }

    static int BinarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (array[mid] > target) {
                high = mid - 1;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}