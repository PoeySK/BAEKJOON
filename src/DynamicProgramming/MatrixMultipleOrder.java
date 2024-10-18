package DynamicProgramming;

import java.io.*;
import java.util.*;

public class MatrixMultipleOrder {
    static int N;
    static Node[] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        matrix = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[i] = new Node(r, c);
        }

        br.close();
        /* 작동 */
        run();

        /* 출력 */
        bw.write("\n");
        bw.close();
    }

    private static void run() {

    }

    static class Node {
        int r,c ;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
