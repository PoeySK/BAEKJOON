package List;

import java.io.*;
import java.util.*;

public class ObtainSum {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(st.nextToken()) - 1;
            int J = Integer.parseInt(st.nextToken()) - 1;
            if (I == 0) bw.write(arr[J] + "\n");
            else {
                if (I == J) bw.write(arr[J] - arr[I - 1] + "\n");
                else bw.write(arr[J] - arr[I - 1] + "\n");
            }
        }
        br.close();
        bw.close();
    }
}
