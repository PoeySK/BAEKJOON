package PriorityQueue;

import java.io.*;
import java.util.*;

public class AbsHeap {
    public static class absheap {
        private final ArrayList<Long> array;

        public absheap() {
            array = new ArrayList<>();
            array.add(Long.MAX_VALUE);
        }

        public void insert(long element) {
            array.add(element);

            int nowIndex = array.size() - 1;
            while (nowIndex > 1 && Math.abs(array.get(nowIndex)) <= Math.abs(array.get(nowIndex / 2))) {
                long absnow = Math.abs(array.get(nowIndex));
                long abs2now = Math.abs(array.get(nowIndex / 2));

                if (absnow == abs2now) {
                    if (array.get(nowIndex) < array.get(nowIndex / 2)) {
                        long temp = array.get(nowIndex);
                        array.set(nowIndex, array.get(nowIndex / 2));
                        array.set(nowIndex / 2, temp);
                    }
                } else {
                    long temp = array.get(nowIndex);
                    array.set(nowIndex, array.get(nowIndex / 2));
                    array.set(nowIndex / 2, temp);
                }

                nowIndex = nowIndex / 2;
            }
        }

        public void delete() {
            if (array.size() < 2) {
                System.out.println("0");
            } else {
                System.out.println(array.get(1));
                array.set(1, array.get(array.size() - 1));
                array.remove(array.size() - 1);

                int nowIndex = 1;
                while (nowIndex * 2 < array.size()) {
                    int minIndex = nowIndex * 2;

                    if (minIndex + 1 < array.size() && Math.abs(array.get(minIndex)) > Math.abs(array.get(minIndex + 1))) {
                        minIndex = minIndex + 1;
                    }
                    if (Math.abs(array.get(nowIndex)) <= Math.abs(array.get(minIndex))) {
                        break;
                    }

                    long temp = array.get(nowIndex);
                    array.set(nowIndex, array.get(minIndex));
                    array.set(minIndex, temp);

                    nowIndex = minIndex;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        absheap ah = new absheap();

        while (N-- > 0) {
            int m = Integer.parseInt(br.readLine());

            if (m == 0) {
                ah.delete();
            } else {
                ah.insert(m);
            }
        }

    }
}