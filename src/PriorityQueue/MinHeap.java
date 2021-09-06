package PriorityQueue;

import java.io.*;
import java.util.*;

public class MinHeap {
    public static class minHeap {
        private final ArrayList<Integer> array;

        public minHeap() {
            array = new ArrayList<>();
            array.add(Integer.MIN_VALUE);
        }

        void insert(int element) {
            array.add(element);
            int nowIndex = array.size() - 1;
            while (nowIndex > 1 && array.get(nowIndex / 2) > array.get(nowIndex)) {
                int temp = array.get(nowIndex / 2);
                array.set(nowIndex / 2, array.get(nowIndex));
                array.set(nowIndex, temp);
                nowIndex = nowIndex / 2;
            }
        }

        void delete() {
            if (array.size() < 2) {
                System.out.println("0");
            } else {
                System.out.println(array.get(1));
                array.set(1, array.get(array.size() - 1));
                array.remove(array.size() - 1);

                int nowIndex = 1;
                while (nowIndex * 2 < array.size()) {
                    int minIndex = nowIndex * 2;
                    if (array.size() > (nowIndex * 2 + 1) && array.get(minIndex) > array.get(nowIndex * 2 + 1)) {
                        minIndex = nowIndex * 2 + 1;
                    }
                    if (array.get(nowIndex) <= array.get(minIndex)) {
                        break;
                    }
                    if (array.get(nowIndex) > array.get(minIndex)) {
                        int temp = array.get(nowIndex);
                        array.set(nowIndex, array.get(minIndex));
                        array.set(minIndex, temp);
                    }
                    nowIndex = minIndex;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        minHeap mh = new minHeap();
        while (N-- > 0) {
            int m = Integer.parseInt(br.readLine());

            if (m == 0) {
                mh.delete();
            } else {
                mh.insert(m);
            }
        }

    }
}