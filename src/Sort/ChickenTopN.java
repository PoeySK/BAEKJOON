package Sort;

import java.io.*;
import java.util.*;

public class ChickenTopN {
    static int[] list;
    static int N, p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        p = Integer.parseInt(br.readLine());

        list = new int[N];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(s[i]);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(list[i] + " ");
        }
        mergeSort(list, 0, N - 1);
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print(list[i] + " ");
        }
    }

    static void mergeSort(int[] array, int left, int right) {
        int count = N/ p;
        if(count==3){
            return;
        }
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
        count++;
    }

    static void merge(int[] array, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int index = left;

        int[] sort = new int[array.length];
        while (l <= mid && r <= right) {
            if (array[l] <= array[r]) {
                sort[index++] = array[l++];
            } else {
                sort[index++] = array[r++];
            }
        }

        while (r <= right) {
            sort[index++] = array[r++];
        }
        while (l <= mid) {
            sort[index++] = array[l++];
        }

        for (int i = left; i <= right; i++) {
            array[i] = sort[i];
        }

    }
}
