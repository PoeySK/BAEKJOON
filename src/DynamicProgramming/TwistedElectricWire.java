package DynamicProgramming;

import java.io.*;
import java.util.*;

public class TwistedElectricWire {
    static int N;
    static int[] wires;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        wires = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            wires[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int result = run();

        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int[] sol = new int[N];
        ArrayList<Node> save = new ArrayList<>();
        save.add(new Node(wires[0]));
        sol[0] = wires[0];
        int size = 0;
        for (int i = 1; i < N; i++) {
            int wire = wires[i];
            if (sol[size] <= wire) {
                sol[++size] = wire;
                save.add(new Node(wire));
            } else {
                int idx = bs(size, wire, sol);
                sol[idx] = wire;
                save.get(idx).value = wire;
            }
        }

        return N - save.size();
    }

    private static int bs(int size, int target, int[] sol) {
        int high = size;
        int low = 0;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            if (sol[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return high;
    }

    static class Node {
        int value;

        Node(int value) {
            this.value = value;
        }

        public String toString() {
            return "value: " + value;
        }
    }
}
