package AdHoc;

import java.io.*;
import java.util.*;

public class BinaryKingdom {
    static int N, M;
    static int[] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[N];
        st = new StringTokenizer(br.readLine());
        int count = 0;
        for (int i = 0; i < N; i++) {
            field[i] = Integer.parseInt(st.nextToken());
            if(field[i] == 1) {
                count++;

                if(i > 0 && field[i - 1] == 1) count--;
                if(i < N - 1 && field[i + 1] == 1) count--;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int binary = Integer.parseInt(st.nextToken());

            if (binary == 0) {
                bw.write(count + "\n");
            } else { // binary == 1
                int ordeal = Integer.parseInt(st.nextToken()) - 1;
                if(field[ordeal] == 1) continue;

                field[ordeal] = 1;
                count++;

                if(ordeal > 0 && field[ordeal - 1] == 1) count--;
                if(ordeal < N - 1 && field[ordeal + 1] == 1) count--;
            }
        }
        br.close();
        bw.close();
    }
}
