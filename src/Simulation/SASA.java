package Simulation;

import java.io.*;
import java.util.*;

public class SASA {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] info = new int[N + 1];
        boolean[] isSelected = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            info[a] = b;
            isSelected[b] = true;
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        br.close();

        int result = run(info, s, isSelected);

        bw.write((result == 1 ? "NOJAM" : result) + "\n");
        bw.close();
    }

    private static int run(int[] info, int s, boolean[] isSelected) {
        int result = 0;
        if (info[s] > 0) result = 1;
        else {
            int leftCandidate = 0;
            int leftIdx = -1;
            for (int i = 1; i < N + 1; i++) {
                if (info[i] == 0) {
                    leftCandidate++;
                    if (i != s) leftIdx = i; // s를 제외한 남은 후보
                }

                if (i != s && !isSelected[i]) {
                    result++;
                }
            }

            if (leftCandidate == 2) {
                result = (!isSelected[leftIdx] ? 1 : result);
            }
        }
        return result;
    }
}
